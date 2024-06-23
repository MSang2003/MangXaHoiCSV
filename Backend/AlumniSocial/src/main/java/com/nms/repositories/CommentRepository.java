/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories;


import com.nms.pojo.Comments;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface CommentRepository {
        List<Map<String, Object>> getCommentsByPostId(int id);
        void deleteCommentById(int id);
        Comments getCommentById(int id);
        void deleteComment(Comments comment);
        void createOrUpdateComment(Comments comment);
}
