/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.To_Do_List.service;

import com.To_Do_List.model.Task;
import com.To_Do_List.repository.TaskRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    /**
     * Obtiene todas las tareas de la tabla tasks.
     * @return 
    */
    public List<Task> getAll() {
        return taskRepository.getAll();
    }

    /**
     * Metodo que obtiene las tareas  por Id de la tabla Tasks.
     * @param id
     * @return 
    */
    public Optional<Task> getTask(int id) {
        return taskRepository.getTask(id);
    }
    
    /**
     * Metodo consulta si un titulo existe en la tabla Tasks.
     * @param email
     * @return 
    */
    public boolean titleExist(String title) {
        return taskRepository.titleExist(title);
    }
    
    /**
	 * Metodo que crea tareas en la tabla Tasks.
	 * @param tasks
	 * @return 
	*/
	public Task create(Task task){ 
	    //obtiene el maximo id existente de la tabla
	    Optional<Task> taskIdMaximo = taskRepository.lastTaskId();
	    // Si el id del tarea que se recibe como parametro es nulo, entonces valida el maximo id
	    if (task.getId() == null) {
	        //Valida el maximo Id generado, si no hay ninguno aun el primer Id sera 1
	        if (taskIdMaximo.isEmpty()) 
	        	task.setId(1);
	        else 
	        	task.setId(taskIdMaximo.get().getId()+ 1);
	
	    } 
        Optional<Task> e = taskRepository.getTask(task.getId());
        if (e.isEmpty()) {
            if (titleExist(task.getTitle()) == false) {
                return taskRepository.create(task);
            } else {
                return task;
            }
        } else {
            return task;
        }
	    
	}
	
	/**
	 * Metodo que actualiza tareas en la tabla Tasks.
	 * @param user
	 * @return 
	*/
	public Task update(Task task) {
	
	    if (task.getId() != null) {
	        Optional<Task> taskDb = taskRepository.getTask(task.getId());
	        if (!taskDb.isEmpty()) {
	            if (task.getTitle()!= null) {
	            	taskDb.get().setTitle(task.getTitle());
	            }
	            if (task.getDescription()!= null) {
	            	taskDb.get().setDescription(task.getDescription());
	            }
	            taskRepository.update(taskDb.get());
	            return taskDb.get();
	        } else {
	            return task;
	        }
	    } else {
	        return task;
	    }
	}
	
	/**
	 * Metodo que elimina tarea en la tabla Tasks.
	 * @param taskId
	 * @return 
	*/
	public boolean delete(int taskId) {
	    /*Optional<Task> task = getTask(taskId);
	    
	    if (task.isEmpty()){
	        return false;
	    }else{
	        taskRepositorio.delete(task.get());
	        return true;
	    }
	    */
	    Boolean aBoolean = getTask(taskId).map(task -> {
	        taskRepository.delete(task);
	        return true;
	    }).orElse(false);
	    return aBoolean;
	}
}
