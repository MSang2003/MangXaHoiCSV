/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Usergroups;
import com.nms.pojo.Users;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface UserGroupsService {

    public List<Usergroups> getGroups();

    public void deleteGroupById(int id);

    public Usergroups getGroupById(int id);

    public void deleteGroup(Usergroups group);

    public void createOrUpdateGroup(Usergroups group);
}
