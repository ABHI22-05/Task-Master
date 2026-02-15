package com.sneakerhead.backend.controller;

import com.sneakerhead.backend.dto.request.TeamRequest;
import com.sneakerhead.backend.dto.response.TeamResponse;
import com.sneakerhead.backend.service.TeamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService;

    /**
     * Create a new team
     * 
     * @param request Team details
     * @return Created team
     */
    @PostMapping
    public ResponseEntity<TeamResponse> createTeam(@Valid @RequestBody TeamRequest request) {
        TeamResponse team = teamService.createTeam(request);
        return new ResponseEntity<>(team, HttpStatus.CREATED);
    }

    /**
     * Get team by ID
     * 
     * @param id Team ID
     * @return Team details
     */
    @GetMapping("/{id}")
    public ResponseEntity<TeamResponse> getTeamById(@PathVariable Long id) {
        TeamResponse team = teamService.getTeamById(id);
        return ResponseEntity.ok(team);
    }

    /**
     * Get all teams
     * 
     * @return List of all teams
     */
    @GetMapping
    public ResponseEntity<List<TeamResponse>> getAllTeams() {
        List<TeamResponse> teams = teamService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    /**
     * Get teams where current user is a member
     * 
     * @return List of user's teams
     */
    @GetMapping("/my-teams")
    public ResponseEntity<List<TeamResponse>> getMyTeams() {
        List<TeamResponse> teams = teamService.getMyTeams();
        return ResponseEntity.ok(teams);
    }

    /**
     * Update team
     * 
     * @param id      Team ID
     * @param request Updated team details
     * @return Updated team
     */
    @PutMapping("/{id}")
    public ResponseEntity<TeamResponse> updateTeam(
            @PathVariable Long id,
            @Valid @RequestBody TeamRequest request) {
        TeamResponse team = teamService.updateTeam(id, request);
        return ResponseEntity.ok(team);
    }

    /**
     * Delete team
     * 
     * @param id Team ID
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Add member to team
     * 
     * @param id     Team ID
     * @param userId User ID to add
     * @return Updated team
     */
    @PostMapping("/{id}/members/{userId}")
    public ResponseEntity<TeamResponse> addMember(
            @PathVariable Long id,
            @PathVariable Long userId) {
        TeamResponse team = teamService.addMember(id, userId);
        return ResponseEntity.ok(team);
    }

    /**
     * Remove member from team
     * 
     * @param id     Team ID
     * @param userId User ID to remove
     * @return Updated team
     */
    @DeleteMapping("/{id}/members/{userId}")
    public ResponseEntity<TeamResponse> removeMember(
            @PathVariable Long id,
            @PathVariable Long userId) {
        TeamResponse team = teamService.removeMember(id, userId);
        return ResponseEntity.ok(team);
    }
}
