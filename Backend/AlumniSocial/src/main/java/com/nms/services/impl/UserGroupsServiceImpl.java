/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Usergroups;
import com.nms.repositories.CommentRepository;
import com.nms.repositories.PostRepository;
import com.nms.repositories.StatsRepository;
import com.nms.repositories.UserGroupsRepository;
import com.nms.services.CommentService;
import com.nms.services.PostService;
import com.nms.services.StatsService;
import com.nms.services.UserGroupsService;
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
public class UserGroupsServiceImpl implements UserGroupsService{

    @Autowired
    private UserGroupsRepository groupRepo;


    @Override
    public void deleteGroupById(int id) {
        this.groupRepo.deleteGroupById(id);
    }

    @Override
    public Usergroups getGroupById(int id) {
        return this.groupRepo.getGroupById(id);
    }

    @Override
    public void deleteGroup(Usergroups group) {
        this.groupRepo.deleteGroup(group);
    }

    @Override
    public void createOrUpdateGroup(Usergroups group) {
        this.groupRepo.createOrUpdateGroup(group);
    }

    @Override
    public List<Usergroups> getGroups() {
        return this.groupRepo.getGroups();
    }
}
