/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nms.pojo.Users;
import com.nms.repositories.UsersRepository;
import com.nms.services.UsersSevice;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class UsersServiceImpl implements UsersSevice {

    @Autowired
    private UsersRepository userRepo;
    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Users> getUsers() {
        return this.userRepo.getUsers();
    }

    @Override
    public void addUser(Users user) {
        if (user.getAvatarFile() != null && !user.getAvatarFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getAvatarFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }

        if (user.getCoverImageFile() != null && !user.getCoverImageFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getCoverImageFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setCoverImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        this.userRepo.addUser(user);
    }

    @Override
    public Users getUserById(int id) {
        return this.userRepo.getUserById(id);
    }

    @Override
    public void deleteUserById(int id) {
        this.userRepo.deleteUserById(id);
    }

    @Override
    public void updateUser(Users user) {
        if (!user.getAvatarFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(user.getAvatarFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                user.setAvatar(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        this.userRepo.updateUser(user);
    }

    @Override
    public Users getUserByUsername(String username) {
        return this.userRepo.getUserByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users u = this.userRepo.getUserByUsername(username);
        if (u == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(u.getRole()));

        return new org.springframework.security.core.userdetails.User(
                u.getName(), u.getPassword(), authorities);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.userRepo.authUser(username, password);
    }



    @Scheduled(fixedRate = 360000)
    @Override
    public void checkChangePassWord() {
        this.userRepo.checkChangePassWord();
    }
}
