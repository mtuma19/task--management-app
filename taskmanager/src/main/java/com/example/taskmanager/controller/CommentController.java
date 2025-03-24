package com.example.taskmanager.controller;

import com.example.taskmanager.model.Comment;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.repository.CommentRepository;
import com.example.taskmanager.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    public CommentController(CommentRepository commentRepository, TaskRepository taskRepository) {
        this.commentRepository = commentRepository;
        this.taskRepository = taskRepository;
    }

    // POST endpoint to create a new comment
    @PostMapping
    public Comment createComment(@RequestBody Comment comment) {
        // Set the current time for the comment
        comment.setCreatedAt(java.time.LocalDateTime.now());
        return commentRepository.save(comment);
    }

    // GET endpoint to get all comments for a specific task
    @GetMapping("/task/{taskId}")
    public List<Comment> getCommentsByTaskId(@PathVariable Long taskId) {
        return commentRepository.findByTaskId(taskId);
    }

    // POST endpoint to create a reply to a comment (no nesting replies)
    @PostMapping("/reply/{parentCommentId}")
    public Comment replyToComment(@PathVariable Long parentCommentId, @RequestBody Comment reply) {
        Comment parentComment = commentRepository.findById(parentCommentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        reply.setParentComment(parentComment); // Set the parent comment for the reply
        reply.setCreatedAt(java.time.LocalDateTime.now());
        return commentRepository.save(reply);
    }
}
