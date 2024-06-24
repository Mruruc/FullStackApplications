package com.mruruc.service;

import com.mruruc.model.Task;
import com.mruruc.repository.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.mruruc.exception.NotFoundException;
@Service
public class TaskService {
    private TaskRepo repo;
    @Autowired
    public TaskService(TaskRepo taskRepo){
        this.repo=taskRepo;
    }

    public List<Task> getAllTask(){
        return repo.findAll();
    }

    public Optional<Task> getTaskById(Long id){
        if(repo.existsById(id)){
            return repo.findById(id);
        }
        throw new NotFoundException("Specified Id Does Not Exist!");
    }

    public List<Task> getByTasksId(Long id){
        return repo.getTaskByTasks_Id(id);
    }

    public Boolean createTask(Task task){
        repo.save(task);
        if(repo.existsById(task.getId())){
            return true;
        }
        return false;
    }
    public boolean update(Task task) {
        Optional<Task> existTask = repo.findById(task.getId());
        if (existTask.isPresent()) {
            existTask.get().setCompleted(task.getCompleted());
            repo.save(existTask.get());
            return true;
        }
        throw new NotFoundException("Specified Id Does Not Exist!");
    }


    public boolean delete(Long id){
        repo.deleteById(id);
        return repo.existsById(id) ;

    }
}
