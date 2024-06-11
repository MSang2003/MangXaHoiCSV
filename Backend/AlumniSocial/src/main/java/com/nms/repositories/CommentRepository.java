/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories;


import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface CommentRepository {
        List<Object[]> getCommentsByPostId(int id);
        void deleteCommentById(int id);
        Comments getCommentById(int id);
        void deleteComment(Comments comment);
        void createOrUpdateComment(Comments comment);
}
