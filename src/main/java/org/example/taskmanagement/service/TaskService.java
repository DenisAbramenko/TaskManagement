package org.example.taskmanagement.service;

import org.example.taskmanagement.dto.TaskDTO;
import org.example.taskmanagement.entity.Task;
import org.example.taskmanagement.repository.TaskRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Optional<Task> getTaskById(Long id) {
        return repository.findById(id);
    }

    public void deleteTask(Long id) {
    }

    public Task updateTask(Long id, TaskDTO taskDto) {
        return null;
    }

    public Task createTask(TaskDTO taskDto) {
        return null;
    }

    public List<Task> getFilteredTasks(String status, String priority, Pageable pageable) {
        return null;
    }
}
