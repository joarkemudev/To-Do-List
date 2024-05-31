/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.To_Do_List.repository;

import com.To_Do_List.model.Task;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SISTEMAS
 */

@Repository
public class TaskRepository {
    /**
     * llaves primarias de tareas en la tabla tasks.
     * @return 
    */
    @Autowired
    private TaskCrudRepository taskCrudRepository;
    
    /**
     * Obtiene todos las tareas en la tabla tasks.
     * @return
    */
    public List<Task> getAll(){
        return taskCrudRepository.findAll();
    }

    /**
     * Metodo que obtiene las tareas por Id de la tabla tasks
     * @param id
     * @return
     * */
    public Optional<Task> getTask(int id) {
        return taskCrudRepository.findById(id);
    }
    
    /**
     * Crea tarea en la tabla tasks
     * @param user
     * @return 
    */
    public Task create(Task task) {
        return taskCrudRepository.save(task);
    }
    
    /**
     * Actualiza tarea en la tabla tasks.  
     * @param user
     * @return 
    */
    public Task update(Task task) {
        return taskCrudRepository.save(task);
    }
    
    /**
     * Elimina tarea en la tabla tasks. 
     * @param user
    */
    public void delete(Task task) {
    	taskCrudRepository.delete(task);
        /*
        Boolean aBoolean = getUser(userId).map(user -> {
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;*/
    }
    
    /**
     * Metodo consulta si un titulo existe en la tabla tasks
     * @param title
     * @return 
    */
    public boolean titleExist(String title) {
        Optional <Task> task = taskCrudRepository.findByTitle(title);
        return !task.isEmpty();
    }
    
    /**
     * Consulta la ultima tarea en la tabla tasks. 
     * @return 
    */
    public Optional<Task> lastTaskId(){
        return taskCrudRepository.findTopByOrderByIdDesc();
    }
}
