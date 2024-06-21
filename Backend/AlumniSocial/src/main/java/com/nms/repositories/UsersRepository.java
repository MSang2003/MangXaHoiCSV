/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories;

import com.nms.pojo.Users;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface UsersRepository {

    List<Users> getUsers();

    void addUser(Users user);

    void updateUser(Users user);

    Users getUserById(int id);

    void deleteUserById(int id);

    Users getUserByUsername(String username);

    boolean authUser(String username, String password);
    
    void checkChangePassWord();

}
