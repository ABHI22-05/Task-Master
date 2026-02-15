package com.sneakerhead.backend.service;

import com.sneakerhead.backend.dto.request.ProjectRequest;
import com.sneakerhead.backend.dto.response.ProjectResponse;
import com.sneakerhead.backend.entity.Project;
import com.sneakerhead.backend.entity.Team;
import com.sneakerhead.backend.exception.ResourceNotFoundException;
import com.sneakerhead.backend.repository.ProjectRepository;
import com.sneakerhead.backend.repository.TeamRepository;
import com.sneakerhead.backend.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TeamRepository teamRepository;
    private final EntityMapper entityMapper;

    @Transactional
    public ProjectResponse createProject(ProjectRequest request) {
        Team team = teamRepository.findById(request.getTeamId())
                .orElseThrow(() -> new ResourceNotFoundException("Team", "id", request.getTeamId()));

        Project project = Project.builder()
                .name(request.getName())
                .description(request.getDescription())
                .team(team)
                .status(request.getStatus() != null ? request.getStatus() : Project.Status.ACTIVE)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        Project savedProject = projectRepository.save(project);
        return entityMapper.toProjectResponse(savedProject);
    }

    @Transactional(readOnly = true)
    public ProjectResponse getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

        return entityMapper.toProjectResponse(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(entityMapper::toProjectResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsByTeam(Long teamId) {
        return projectRepository.findByTeamId(teamId).stream()
                .map(entityMapper::toProjectResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public ProjectResponse updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

        if (request.getName() != null) {
            project.setName(request.getName());
        }
        if (request.getDescription() != null) {
            project.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            project.setStatus(request.getStatus());
        }
        if (request.getStartDate() != null) {
            project.setStartDate(request.getStartDate());
        }
        if (request.getEndDate() != null) {
            project.setEndDate(request.getEndDate());
        }

        Project updatedProject = projectRepository.save(project);
        return entityMapper.toProjectResponse(updatedProject);
    }

    @Transactional
    public void deleteProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id));

        projectRepository.delete(project);
    }
}
