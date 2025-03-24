package com.example.taskmanager.repository;

import com.example.taskmanager.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // Find comments for a specific task
    List<Comment> findByTaskId(Long taskId);

    // Find replies to a specific comment (if needed)
    List<Comment> findByParentCommentId(Long parentCommentId);
}
