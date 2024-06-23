/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services;

import com.nms.pojo.Invitations;
import com.nms.pojo.Posts;
import com.nms.pojo.Surveyoptions;
import com.nms.pojo.Surveys;
import com.nms.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface PostService {

    Map<String, Object> getPosts(Integer userID, Integer pageNumber,Boolean isProfile);

    void deletePostById(int id);

    void createOrUpdateStatusPost(Posts post);

    public Posts getPostById(int id);
    
     public void createInvitationPost(Posts post, Invitations invitation);
     public void createInvitationPost(Posts post, Surveys survey, List<Surveyoptions> surveyOptions);
}
