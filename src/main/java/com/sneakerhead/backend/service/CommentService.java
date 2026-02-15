package com.sneakerhead.backend.service;

import com.sneakerhead.backend.dto.request.CommentRequest;
import com.sneakerhead.backend.dto.response.CommentResponse;
import com.sneakerhead.backend.entity.Comment;
import com.sneakerhead.backend.entity.Task;
import com.sneakerhead.backend.entity.User;
import com.sneakerhead.backend.exception.ResourceNotFoundException;
import com.sneakerhead.backend.repository.CommentRepository;
import com.sneakerhead.backend.repository.TaskRepository;
import com.sneakerhead.backend.util.EntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;
    private final UserService userService;
    private final EntityMapper entityMapper;

    @Transactional
    public CommentResponse addComment(Long taskId, CommentRequest request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "id", taskId));

        User currentUser = userService.getCurrentUserEntity();

        Comment comment = Comment.builder()
                .content(request.getContent())
                .task(task)
                .user(currentUser)
                .build();

        Comment savedComment = commentRepository.save(comment);
        return entityMapper.toCommentResponse(savedComment);
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsByTask(Long taskId) {
        return commentRepository.findByTaskIdOrderByCreatedAtDesc(taskId).stream()
                .map(entityMapper::toCommentResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteComment(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));

        commentRepository.delete(comment);
    }
}
