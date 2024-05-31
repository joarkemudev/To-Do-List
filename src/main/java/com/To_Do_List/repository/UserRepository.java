/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.To_Do_List.repository;

import com.To_Do_List.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SISTEMAS
 */

@Repository
public class UserRepository {
    /**
     * llaves primarias de usuarios de la Tabla Users.
     * @return 
    */
    @Autowired
    private UserCrudRepository userCrudRepository;
    
    /**
     * Obtiene todos los usuarios de la Tabla Users.
     * @return
    */
    public List<User> getAll() {
        return userCrudRepository.findAll();
    }

    /**
     * Metodo que obtiene los usuarios  por Id de la Tabla Users.
     * @param id
     * @return
     * */
    public Optional<User> getUser(int id) {
        return userCrudRepository.findById(id);
    }
    
    /**
     * Crea usuarios en la Tabla Users. 
     * @param user
     * @return 
    */
    public User create(User user) {
        return userCrudRepository.save(user);
    }
    
    /**
     * Actualiza usuarios en la Tabla Users.  
     * @param user
     * @return 
    */
    public User update(User user) {
        return userCrudRepository.save(user);
    }
    
    /**
     * Elimina usuarios en la coleccion Users. 
     * @param user
    */
    public void delete(User user) {
        userCrudRepository.delete(user);
        /*
        Boolean aBoolean = getUser(userId).map(user -> {
            repositorio.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;*/
    }
    
    /**
     * Metodo consulta si un correo existe en la Tabla Users.
     * @param correo
     * @return 
    */
    public boolean emailExist(String email) {
        Optional <User> user = userCrudRepository.findByEmail(email);
        return !user.isEmpty();
    }
    
    /**
     * Autenticar los usuarios en la Tabla Users.
     * @param email
     * @param password
     * @return 
    */
    public Optional <User> autenticateUser(String email, String password) {
        return userCrudRepository.findByEmailAndPassword(email, password);
    }
    
    /**
     * Consulta el ulrimo  usuario en la Tabla Users. 
     * @return 
    */
    public Optional<User> lastUserId(){
        return userCrudRepository.findTopByOrderByIdDesc();
    }
   
}
