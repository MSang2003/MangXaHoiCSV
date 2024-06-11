/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.nms.pojo.Posts;
import com.nms.repositories.PostRepository;
import com.nms.repositories.StatsRepository;
import com.nms.services.PostService;
import com.nms.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepo;

    @Override
    public List<Object[]> getPosts() {
        return this.postRepo.getPosts();
    }

    @Override
    public void deletePostById(int id) {
        this.postRepo.deletePostById(id);
    }

    @Override
    public void createOrUpdatePost(Posts post) {
        this.postRepo.createOrUpdatePost(post);
    }

    @Override
    public Posts getPostById(int id) {
        return this.postRepo.getPostById(id);
    }
}
