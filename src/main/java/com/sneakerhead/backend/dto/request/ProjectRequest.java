package com.sneakerhead.backend.dto.request;

import com.sneakerhead.backend.entity.Project;
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
public class ProjectRequest {

    @NotBlank(message = "Project name is required")
    private String name;

    private String description;

    @NotNull(message = "Team ID is required")
    private Long teamId;

    private Project.Status status;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}
