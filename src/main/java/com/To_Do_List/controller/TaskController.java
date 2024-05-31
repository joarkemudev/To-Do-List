package com.To_Do_List.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.To_Do_List.model.Task;
import com.To_Do_List.service.TaskService;

@RestController
@RequestMapping("/api/task")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})


public class TaskController {
	
	@Autowired
    private TaskService taskService;
	
	/**
     * Metodo Get que retorna todas las tareas.
     * @return 
    */
    @GetMapping("/all")
    public List <Task> getAll(){
        return taskService.getAll();
    }
    
    /**
     * Metodo Get que retorna la tarea por id de la tabla tasks. 
     * @param id
     * @return 
    */
    @GetMapping("/{id}")
    public Optional <Task> getTask(@PathVariable("id") int id) {
        return taskService.getTask(id);
    }
    
     /**
     * Metodo Post que agrega una nueva tarea en la tabla tasks.
     * @param task
     * @return 
    */
    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@RequestBody Task task){
        return taskService.create(task);
    }
    
    /**
     * Metodo Put que actualiza una tarea en la tabla tasks.
     * @param task
     * @return 
    */
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public Task update(@RequestBody Task task){
        return taskService.update(task);
    }
    
    /**
     * Metodo Delete que elimina una tarea en la tabla tasks.
     * @param id
     * @return 
    */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean delete(@PathVariable("id") int id){
        return taskService.delete(id);
    }
}
