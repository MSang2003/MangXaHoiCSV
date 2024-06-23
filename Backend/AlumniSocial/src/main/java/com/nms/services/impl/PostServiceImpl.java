/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.nms.pojo.Invitations;
import com.nms.pojo.Posts;
import com.nms.pojo.Surveyoptions;
import com.nms.pojo.Surveys;
import com.nms.repositories.PostRepository;
import com.nms.repositories.StatsRepository;
import com.nms.services.PostService;
import com.nms.services.StatsService;
import java.io.IOException;
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
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public Map<String, Object> getPosts(Integer userID, Integer pageNumber,Boolean isProfile) {
        return this.postRepo.getPosts(userID, pageNumber,isProfile);
    }

    @Override
    public void deletePostById(int id) {
        this.postRepo.deletePostById(id);
    }

    @Override
    public void createOrUpdateStatusPost(Posts post) {
        if (post.getImageFile() != null) {
            try {
                Map res = this.cloudinary.uploader().upload(post.getImageFile().getBytes(),
                        ObjectUtils.asMap("resource_type", "auto"));
                post.setImage(res.get("secure_url").toString());
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
        this.postRepo.createOrUpdateStatusPost(post);

    }

    @Override
    public Posts getPostById(int id) {
        return this.postRepo.getPostById(id);
    }

    @Override
    public void createInvitationPost(Posts post, Invitations invitation) {
        this.postRepo.createInvitationPost(post, invitation);
    }

    @Override
    public void createInvitationPost(Posts post, Surveys survey, List<Surveyoptions> surveyOptions) {
        this.postRepo.createInvitationPost(post, survey, surveyOptions);
    }
}
