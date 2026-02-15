package com.sneakerhead.backend.util;

import com.sneakerhead.backend.dto.response.*;
import com.sneakerhead.backend.entity.*;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class EntityMapper {

    public UserResponse toUserResponse(User user) {
        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .phone(user.getPhone())
                .profilePicture(user.getProfilePicture())
                .role(user.getRole())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public TaskResponse toTaskResponse(Task task) {
        if (task == null) {
            return null;
        }

        return TaskResponse.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .projectId(task.getProject() != null ? task.getProject().getId() : null)
                .projectName(task.getProject() != null ? task.getProject().getName() : null)
                .creator(toUserResponse(task.getCreator()))
                .assignee(toUserResponse(task.getAssignee()))
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .completedAt(task.getCompletedAt())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .commentsCount(task.getComments() != null ? task.getComments().size() : 0)
                .attachmentsCount(task.getAttachments() != null ? task.getAttachments().size() : 0)
                .build();
    }

    public TeamResponse toTeamResponse(Team team) {
        if (team == null) {
            return null;
        }

        return TeamResponse.builder()
                .id(team.getId())
                .name(team.getName())
                .description(team.getDescription())
                .owner(toUserResponse(team.getOwner()))
                .members(team.getMembers() != null ? team.getMembers().stream()
                        .map(this::toUserResponse)
                        .collect(Collectors.toSet()) : null)
                .projectsCount(team.getProjects() != null ? team.getProjects().size() : 0)
                .active(team.getActive())
                .createdAt(team.getCreatedAt())
                .updatedAt(team.getUpdatedAt())
                .build();
    }

    public ProjectResponse toProjectResponse(Project project) {
        if (project == null) {
            return null;
        }

        return ProjectResponse.builder()
                .id(project.getId())
                .name(project.getName())
                .description(project.getDescription())
                .teamId(project.getTeam() != null ? project.getTeam().getId() : null)
                .teamName(project.getTeam() != null ? project.getTeam().getName() : null)
                .status(project.getStatus())
                .tasksCount(project.getTasks() != null ? project.getTasks().size() : 0)
                .startDate(project.getStartDate())
                .endDate(project.getEndDate())
                .createdAt(project.getCreatedAt())
                .updatedAt(project.getUpdatedAt())
                .build();
    }

    public CommentResponse toCommentResponse(Comment comment) {
        if (comment == null) {
            return null;
        }

        return CommentResponse.builder()
                .id(comment.getId())
                .content(comment.getContent())
                .taskId(comment.getTask() != null ? comment.getTask().getId() : null)
                .user(toUserResponse(comment.getUser()))
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    public AttachmentResponse toAttachmentResponse(Attachment attachment) {
        if (attachment == null) {
            return null;
        }

        return AttachmentResponse.builder()
                .id(attachment.getId())
                .filename(attachment.getFilename())
                .filePath(attachment.getFilePath())
                .fileType(attachment.getFileType())
                .fileSize(attachment.getFileSize())
                .taskId(attachment.getTask() != null ? attachment.getTask().getId() : null)
                .uploadedBy(toUserResponse(attachment.getUploadedBy()))
                .uploadedAt(attachment.getUploadedAt())
                .build();
    }
}
