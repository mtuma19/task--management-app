package com.example.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.taskmanager.model.Task;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // You can define custom queries here if needed
    List<Task> findByUserId(Long userId);
}
