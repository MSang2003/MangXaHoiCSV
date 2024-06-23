/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface CommentService {

    List<Map<String, Object>> getCommentsByPostId(int id);

    public void deleteCommentById(int id);

    public Comments getCommentById(int id);

    public void deleteComment(Comments comment);

    void createOrUpdateComment(Comments comment);
}
