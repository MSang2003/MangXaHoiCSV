/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories;

import com.nms.pojo.Invitations;
import com.nms.pojo.Posts;
import com.nms.pojo.Surveyoptions;
import com.nms.pojo.Surveys;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface PostRepository {

    Map<String, Object> getPosts(Integer userID, Integer pageNumber,Boolean isProfile);

    void deletePostById(int id);

    void createOrUpdateStatusPost(Posts post);

    Posts getPostById(int id);

    void createInvitationPost(Posts post, Invitations invitation);

    void createInvitationPost(Posts post, Surveys survey, List<Surveyoptions> surveyOptions);

}
