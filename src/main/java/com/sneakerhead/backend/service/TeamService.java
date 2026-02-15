package com.sneakerhead.backend.service;

import com.sneakerhead.backend.dto.request.TeamRequest;
import com.sneakerhead.backend.dto.response.TeamResponse;
import com.sneakerhead.backend.entity.Team;
import com.sneakerhead.backend.entity.User;
import com.sneakerhead.backend.exception.BadRequestException;
import com.sneakerhead.backend.exception.ResourceNotFoundException;
import com.sneakerhead.backend.repository.TeamRepository;
import com.sneakerhead.backend.repository.UserRepository;
import com.sneakerhead.backend.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    private final EntityMapper entityMapper;

    @Transactional
    public TeamResponse createTeam(TeamRequest request) {
        User currentUser = userService.getCurrentUserEntity();

        Team team = Team.builder()
                .name(request.getName())
                .description(request.getDescription())
                .owner(currentUser)
                .members(new HashSet<>())
                .active(true)
                .build();

        // Add owner as a member
        team.getMembers().add(currentUser);

        Team savedTeam = teamRepository.save(team);
        return entityMapper.toTeamResponse(savedTeam);
    }

    @Transactional(readOnly = true)
    public TeamResponse getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));

        return entityMapper.toTeamResponse(team);
    }

    @Transactional(readOnly = true)
    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll().stream()
                .map(entityMapper::toTeamResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TeamResponse> getMyTeams() {
        User currentUser = userService.getCurrentUserEntity();
        return teamRepository.findTeamsByMemberId(currentUser.getId()).stream()
                .map(entityMapper::toTeamResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TeamResponse updateTeam(Long id, TeamRequest request) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));

        User currentUser = userService.getCurrentUserEntity();

        // Check if current user is the owner
        if (!team.getOwner().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Only team owner can update team details");
        }

        if (request.getName() != null) {
            team.setName(request.getName());
        }
        if (request.getDescription() != null) {
            team.setDescription(request.getDescription());
        }

        Team updatedTeam = teamRepository.save(team);
        return entityMapper.toTeamResponse(updatedTeam);
    }

    @Transactional
    public void deleteTeam(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", id));

        User currentUser = userService.getCurrentUserEntity();

        // Check if current user is the owner
        if (!team.getOwner().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Only team owner can delete the team");
        }

        teamRepository.delete(team);
    }

    @Transactional
    public TeamResponse addMember(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        User currentUser = userService.getCurrentUserEntity();

        // Check if current user is the owner
        if (!team.getOwner().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Only team owner can add members");
        }

        team.getMembers().add(user);
        Team updatedTeam = teamRepository.save(team);

        return entityMapper.toTeamResponse(updatedTeam);
    }

    @Transactional
    public TeamResponse removeMember(Long teamId, Long userId) {
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", teamId));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        User currentUser = userService.getCurrentUserEntity();

        // Check if current user is the owner
        if (!team.getOwner().getId().equals(currentUser.getId())) {
            throw new BadRequestException("Only team owner can remove members");
        }

        // Prevent removing the owner
        if (user.getId().equals(team.getOwner().getId())) {
            throw new BadRequestException("Cannot remove team owner from members");
        }

        team.getMembers().remove(user);
        Team updatedTeam = teamRepository.save(team);

        return entityMapper.toTeamResponse(updatedTeam);
    }
}
