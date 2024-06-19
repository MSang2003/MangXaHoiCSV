/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services;

import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface PostService {

   List<Map<String, Object>> getPosts();
    void deletePostById(int id);
    void createOrUpdatePost(Posts post);
     public Posts getPostById(int id);
}
