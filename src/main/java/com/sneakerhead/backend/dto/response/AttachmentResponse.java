package com.sneakerhead.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentResponse {

    private Long id;
    private String filename;
    private String filePath;
    private String fileType;
    private Long fileSize;
    private Long taskId;
    private UserResponse uploadedBy;
    private LocalDateTime uploadedAt;
}
