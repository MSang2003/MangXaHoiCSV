/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.nms.pojo.Users;
import com.nms.repositories.UsersRepository;
import com.nms.services.UsersSevice;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class UsersServiceImpl implements UsersSevice{

    @Autowired
    private UsersRepository userRepo;
    
    @Override
    public List<Users> getUsers() {
        return this.userRepo.getUsers();
    }
    
}
