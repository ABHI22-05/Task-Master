package com.sneakerhead.backend.controller;

import com.sneakerhead.backend.dto.request.CommentRequest;
import com.sneakerhead.backend.dto.response.CommentResponse;
import com.sneakerhead.backend.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks/{taskId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * Add comment to a task
     * 
     * @param taskId  Task ID
     * @param request Comment content
     * @return Created comment
     */
    @PostMapping
    public ResponseEntity<CommentResponse> addComment(
            @PathVariable Long taskId,
            @Valid @RequestBody CommentRequest request) {
        CommentResponse comment = commentService.addComment(taskId, request);
        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    /**
     * Get all comments for a task
     * 
     * @param taskId Task ID
     * @return List of comments
     */
    @GetMapping
    public ResponseEntity<List<CommentResponse>> getCommentsByTask(@PathVariable Long taskId) {
        List<CommentResponse> comments = commentService.getCommentsByTask(taskId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Delete comment
     * 
     * @param taskId    Task ID
     * @param commentId Comment ID
     * @return No content
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long taskId,
            @PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }
}
