/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services;

import com.nms.pojo.Users;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface UsersSevice extends UserDetailsService{

    List<Users> getUsers();

    void addUser(Users user);

    Users getUserById(int id);

    void deleteUserById(int id);
    
    void updateUser(Users user);
    
    Users getUserByUsername(String username);
    
    public boolean authUser(String username, String password);
    
      void checkChangePassWord();
    
}





