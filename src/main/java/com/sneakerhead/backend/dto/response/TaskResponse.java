package com.sneakerhead.backend.dto.response;

import com.sneakerhead.backend.entity.Task;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private Long projectId;
    private String projectName;
    private UserResponse creator;
    private UserResponse assignee;
    private Task.Status status;
    private Task.Priority priority;
    private LocalDateTime dueDate;
    private LocalDateTime completedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer commentsCount;
    private Integer attachmentsCount;
}
