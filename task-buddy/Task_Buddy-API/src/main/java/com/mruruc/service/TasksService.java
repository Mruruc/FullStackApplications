package com.mruruc.service;

import com.mruruc.model.Tasks;
import com.mruruc.repository.TaskListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

@Service
public class TasksService {
    private TaskListRepo repo;
    @Autowired
    public TasksService(TaskListRepo repo) {
        this.repo = repo;
    }


    public List<Tasks> getAllTasks(){
        return repo.findAll();
    }

    public boolean create(Tasks tasks){
        repo.save(tasks);
        return repo.existsById(tasks.getId());
    }
}
