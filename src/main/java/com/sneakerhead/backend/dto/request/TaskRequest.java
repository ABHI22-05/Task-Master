package com.sneakerhead.backend.dto.request;

import com.sneakerhead.backend.entity.Task;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequest {

    @NotBlank(message = "Title is required")
    private String title;

    private String description;

    private Long projectId;

    private Long assigneeId;

    @NotNull(message = "Status is required")
    private Task.Status status;

    @NotNull(message = "Priority is required")
    private Task.Priority priority;

    private LocalDateTime dueDate;
}
