package com.sneakerhead.backend.controller;

import com.sneakerhead.backend.dto.request.ProjectRequest;
import com.sneakerhead.backend.dto.response.ProjectResponse;
import com.sneakerhead.backend.service.ProjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    /**
     * Create a new project
     * 
     * @param request Project details
     * @return Created project
     */
    @PostMapping
    public ResponseEntity<ProjectResponse> createProject(@Valid @RequestBody ProjectRequest request) {
        ProjectResponse project = projectService.createProject(request);
        return new ResponseEntity<>(project, HttpStatus.CREATED);
    }

    /**
     * Get project by ID
     * 
     * @param id Project ID
     * @return Project details
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProjectById(@PathVariable Long id) {
        ProjectResponse project = projectService.getProjectById(id);
        return ResponseEntity.ok(project);
    }

    /**
     * Get all projects or filter by team
     * 
     * @param teamId Optional team ID filter
     * @return List of projects
     */
    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects(
            @RequestParam(required = false) Long teamId) {
        if (teamId != null) {
            return ResponseEntity.ok(projectService.getProjectsByTeam(teamId));
        } else {
            return ResponseEntity.ok(projectService.getAllProjects());
        }
    }

    /**
     * Update project
     * 
     * @param id      Project ID
     * @param request Updated project details
     * @return Updated project
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProjectResponse> updateProject(
            @PathVariable Long id,
            @Valid @RequestBody ProjectRequest request) {
        ProjectResponse project = projectService.updateProject(id, request);
        return ResponseEntity.ok(project);
    }

    /**
     * Delete project
     * 
     * @param id Project ID
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
