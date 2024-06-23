/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories;

import com.nms.pojo.Comments;
import com.nms.pojo.Usergroups;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface UserGroupsRepository {

    List<Usergroups> getGroups();

    void deleteGroupById(int id);

    Usergroups getGroupById(int id);

    void deleteGroup(Usergroups group);

    void createOrUpdateGroup(Usergroups group);
}
