package com.mruruc.controller;

import com.mruruc.model.Task;
import com.mruruc.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class TaskController {
    private TaskService service;

    @Autowired
    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping(path = "/tasks"
              ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>>
    tasks(){
        return new ResponseEntity<>(service.getAllTask(), HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
   @GetMapping(path = "/{id}",
               produces = MediaType.APPLICATION_JSON_VALUE)
   public List<Task> getTaskByTasksId(@PathVariable Long id){

       return service.getByTasksId(id);
   }
    @PostMapping(path = "/task",
            consumes =MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus addTask(@RequestBody Task task){
        return service.createTask(task) ?
                HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @PatchMapping(path = "/task",
                  consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus updateTask(@RequestBody Task task){
        return service.update(task) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
    }
    @DeleteMapping(path ="/task/{id}")
    public HttpStatus removeTask(@PathVariable Long id){
        return service.delete(id) ? HttpStatus.OK : HttpStatus.NOT_ACCEPTABLE;
    }

}
