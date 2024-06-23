/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.repositories.CommentRepository;
import com.nms.repositories.PostRepository;
import com.nms.repositories.StatsRepository;
import com.nms.services.CommentService;
import com.nms.services.PostService;
import com.nms.services.StatsService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepo;

    @Override
    public List<Map<String, Object>> getCommentsByPostId(int id) {
        return this.commentRepo.getCommentsByPostId(id);
    }

    @Override
    public void deleteCommentById(int id) {
        this.commentRepo.deleteCommentById(id);
    }

    @Override
    public Comments getCommentById(int id) {
        return this.commentRepo.getCommentById(id);
    }

    @Override
    public void deleteComment(Comments comment) {
        this.commentRepo.deleteComment(comment);
    }

    @Override
    public void createOrUpdateComment(Comments comment) {
        this.commentRepo.createOrUpdateComment(comment);
    }
}
