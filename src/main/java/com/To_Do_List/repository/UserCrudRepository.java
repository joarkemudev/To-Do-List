/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.To_Do_List.repository;

import com.To_Do_List.model.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author SISTEMAS
 */
public interface UserCrudRepository extends JpaRepository<User, Integer>{
    
    //Selecionar la combinacion correo / contraseña  
    public Optional<User> findByEmailAndPassword(String email, String password);

    // Selecionar el cooreo por usuario
    public Optional<User> findByEmail(String email);
    
    //Para seleccionar el usuario con el id maximo
    Optional<User> findTopByOrderByIdDesc();
    

}
