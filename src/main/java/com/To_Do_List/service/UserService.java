/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.To_Do_List.service;

import com.To_Do_List.model.User;
import com.To_Do_List.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author SISTEMAS
 */
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    /**
     * Obtiene todos los usuarios de la Tabla Users.
     * @return 
    */
    public List<User> getAll() {
        return userRepository.getAll();
    }
    
    /**
     * Metodo que obtiene los usuarios  por Id de la Tabla Users.
     * @param id
     * @return 
    */
    public Optional<User> getUser(int id) {
        return userRepository.getUser(id);
    }
    
    /**
     * Metodo consulta si un correo existe en la Tabla Users.
     * @param email
     * @return 
    */
    public boolean emailExist(String email) {
        return userRepository.emailExist(email);
    }
    
    /**
     * Metodo que retorna email y pasword de la Tabla Users.
     * @param email
     * @param password
     * @return 
    */
    public User autenticateUser(String email, String password) {
        Optional<User> user = userRepository.autenticateUser(email, password);

        if (user.isEmpty()) {
            return new User();
        } else {
            return user.get();
        }
    }
    
        /**
     * Metodo que crea usuarios en la Tabla Users.
     * @param user
     * @return 
    */
    public User create(User user){ 
        //obtiene el maximo id existente en la Tabla
        Optional<User> userIdMaximo = userRepository.lastUserId();
        // Si el id del Usuario que se recibe como parametro es nulo, entonces valida el maximo id
        if (user.getId() == null) {
            //Valida el maximo Id generado, si no hay ninguno aun el primer Id sera 1
            if (userIdMaximo.isEmpty()) 
                user.setId(1);
            else 
                user.setId(userIdMaximo.get().getId()+ 1);
            

        } 
            Optional<User> e = userRepository.getUser(user.getId());
            if (e.isEmpty()) {
                if (emailExist(user.getEmail()) == false) {
                    return userRepository.create(user);
                } else {
                    return user;
                }
            } else {
                return user;
            }
        
    }

    /**
     * Metodo que actualiza usuarios en la Tabla Users.
     * @param user
     * @return 
    */
    public User update(User user) {

        if (user.getId() != null) {
            Optional<User> userDb = userRepository.getUser(user.getId());
            if (!userDb.isEmpty()) {
                if (user.getIdentification()!= null) {
                    userDb.get().setIdentification(user.getIdentification());
                }
                if (user.getName()!= null) {
                    userDb.get().setName(user.getName());
                }
                if (user.getLastName()!= null) {
                    userDb.get().setLastName(user.getLastName());
                }
                if (user.getEmail()!= null) {
                    userDb.get().setEmail(user.getEmail());
                }
                if (user.getPassword()!= null) {
                    userDb.get().setPassword(user.getPassword());
                }
                userRepository.update(userDb.get());
                return userDb.get();
            } else {
                return user;
            }
        } else {
            return user;
        }
    }
    
    /**
     * Metodo que elimina usuarios en la Tabla Users.
     * @param userId
     * @return 
    */
    public boolean delete(int userId) {
        /*Optional<User> usuario = getUser(userId);
        
        if (usuario.isEmpty()){
            return false;
        }else{
            userRepositorio.delete(usuario.get());
            return true;
        }
        */
        Boolean aBoolean = getUser(userId).map(user -> {
            userRepository.delete(user);
            return true;
        }).orElse(false);
        return aBoolean;
    }
 
}
