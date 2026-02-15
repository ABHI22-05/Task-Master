package com.sneakerhead.backend.controller;

import com.sneakerhead.backend.dto.request.TaskRequest;
import com.sneakerhead.backend.dto.response.TaskResponse;
import com.sneakerhead.backend.entity.Task;
import com.sneakerhead.backend.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    /**
     * Create a new task
     * 
     * @param request Task details
     * @return Created task
     */
    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody TaskRequest request) {
        TaskResponse task = taskService.createTask(request);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    /**
     * Get task by ID
     * 
     * @param id Task ID
     * @return Task details
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        TaskResponse task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    /**
     * Get all tasks
     * 
     * @return List of all tasks
     */
    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @RequestParam(required = false) Task.Status status,
            @RequestParam(required = false) String search) {
        if (status != null) {
            return ResponseEntity.ok(taskService.getTasksByStatus(status));
        } else if (search != null && !search.isEmpty()) {
            return ResponseEntity.ok(taskService.searchTasks(search));
        } else {
            return ResponseEntity.ok(taskService.getAllTasks());
        }
    }

    /**
     * Get tasks assigned to or created by current user
     * 
     * @return List of user's tasks
     */
    @GetMapping("/my-tasks")
    public ResponseEntity<List<TaskResponse>> getMyTasks() {
        List<TaskResponse> tasks = taskService.getMyTasks();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Update task
     * 
     * @param id      Task ID
     * @param request Updated task details
     * @return Updated task
     */
    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskRequest request) {
        TaskResponse task = taskService.updateTask(id, request);
        return ResponseEntity.ok(task);
    }

    /**
     * Delete task
     * 
     * @param id Task ID
     * @return No content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Assign task to a user
     * 
     * @param id         Task ID
     * @param assigneeId User ID to assign
     * @return Updated task
     */
    @PatchMapping("/{id}/assign/{assigneeId}")
    public ResponseEntity<TaskResponse> assignTask(
            @PathVariable Long id,
            @PathVariable Long assigneeId) {
        TaskResponse task = taskService.assignTask(id, assigneeId);
        return ResponseEntity.ok(task);
    }

    /**
     * Mark task as completed
     * 
     * @param id Task ID
     * @return Updated task
     */
    @PatchMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> markAsCompleted(@PathVariable Long id) {
        TaskResponse task = taskService.markAsCompleted(id);
        return ResponseEntity.ok(task);
    }
}
