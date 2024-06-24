package com.mruruc.controller;

import com.mruruc.model.Tasks;
import com.mruruc.service.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
@CrossOrigin(origins = {"http://localhost:5173/"})
public class TasksController {
    private TasksService service;

    @Autowired
    public TasksController(TasksService service) {
        this.service = service;
    }

    @GetMapping(path = "/tasksList"
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Tasks>> tasks(){

        return new ResponseEntity<>(service.getAllTasks(), HttpStatus.OK);
    }


    @PostMapping(path = "/tasksList",
            consumes =MediaType.APPLICATION_JSON_VALUE)
    public HttpStatus addTasks(@RequestBody Tasks tasks){
        return service.create(tasks) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
