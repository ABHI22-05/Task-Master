package com.sneakerhead.backend.service;

import com.sneakerhead.backend.dto.request.TaskRequest;
import com.sneakerhead.backend.dto.response.TaskResponse;
import com.sneakerhead.backend.entity.Project;
import com.sneakerhead.backend.entity.Task;
import com.sneakerhead.backend.entity.User;
import com.sneakerhead.backend.exception.BadRequestException;
import com.sneakerhead.backend.exception.ResourceNotFoundException;
import com.sneakerhead.backend.repository.ProjectRepository;
import com.sneakerhead.backend.repository.TaskRepository;
import com.sneakerhead.backend.repository.UserRepository;
import com.sneakerhead.backend.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final UserService userService;
    private final EntityMapper entityMapper;

    @Transactional
    public TaskResponse createTask(TaskRequest request) {
        User currentUser = userService.getCurrentUserEntity();

        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .creator(currentUser)
                .status(request.getStatus() != null ? request.getStatus() : Task.Status.OPEN)
                .priority(request.getPriority() != null ? request.getPriority() : Task.Priority.MEDIUM)
                .dueDate(request.getDueDate())
                .build();

        // Set project if provided
        if (request.getProjectId() != null) {
            Project project = projectRepository.findById(request.getProjectId())
                    .orElseThrow(() -> new ResourceNotFoundException("Project", "id", request.getProjectId()));
            task.setProject(project);
        }

        // Set assignee if provided
        if (request.getAssigneeId() != null) {
            User assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getAssigneeId()));
            task.setAssignee(assignee);
        }

        Task savedTask = taskRepository.save(task);
        return entityMapper.toTaskResponse(savedTask);
    }

    @Transactional(readOnly = true)
    public TaskResponse getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        return entityMapper.toTaskResponse(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(entityMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getMyTasks() {
        User currentUser = userService.getCurrentUserEntity();
        return taskRepository.findTasksByUserId(currentUser.getId()).stream()
                .map(entityMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByStatus(Task.Status status) {
        return taskRepository.findByStatus(status).stream()
                .map(entityMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> searchTasks(String keyword) {
        return taskRepository.searchTasks(keyword).stream()
                .map(entityMapper::toTaskResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public TaskResponse updateTask(Long id, TaskRequest request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }
        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }
        if (request.getStatus() != null) {
            task.setStatus(request.getStatus());
            if (request.getStatus() == Task.Status.COMPLETED) {
                task.setCompletedAt(LocalDateTime.now());
            }
        }
        if (request.getPriority() != null) {
            task.setPriority(request.getPriority());
        }
        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }
        if (request.getAssigneeId() != null) {
            User assignee = userRepository.findById(request.getAssigneeId())
                    .orElseThrow(() -> new ResourceNotFoundException("User", "id", request.getAssigneeId()));
            task.setAssignee(assignee);
        }

        Task updatedTask = taskRepository.save(task);
        return entityMapper.toTaskResponse(updatedTask);
    }

    @Transactional
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        taskRepository.delete(task);
    }

    @Transactional
    public TaskResponse assignTask(Long taskId, Long assigneeId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));

        User assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", assigneeId));

        task.setAssignee(assignee);
        Task updatedTask = taskRepository.save(task);

        return entityMapper.toTaskResponse(updatedTask);
    }

    @Transactional
    public TaskResponse markAsCompleted(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id));

        task.setStatus(Task.Status.COMPLETED);
        task.setCompletedAt(LocalDateTime.now());

        Task updatedTask = taskRepository.save(task);
        return entityMapper.toTaskResponse(updatedTask);
    }
}
