/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.To_Do_List.repository;

import com.To_Do_List.model.Task;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author SISTEMAS
 */
public interface TaskCrudRepository extends JpaRepository<Task, Integer>{
	
	// Selecionar titulo de la tarea
    public Optional<Task> findByTitle(String title);
    
  //Para seleccionar la tarea con el id maximo
    Optional<Task> findTopByOrderByIdDesc();
    
}
