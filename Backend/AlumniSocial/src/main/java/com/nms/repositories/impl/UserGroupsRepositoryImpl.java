/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories.impl;

import com.nms.pojo.Comments;
import com.nms.pojo.Usergroups;
import com.nms.pojo.Users;
import com.nms.repositories.CommentRepository;
import com.nms.repositories.UserGroupsRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UserGroupsRepositoryImpl implements UserGroupsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void deleteGroupById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Usergroups group = s.get(Usergroups.class, id);
        s.delete(group);
    }

    @Override
    public Usergroups getGroupById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Usergroups.class, id);
    }

    @Override
    public void deleteGroup(Usergroups group) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(group);
    }

    @Override
    public void createOrUpdateGroup(Usergroups group) {
        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(group);
    }

    @Override
    public List<Usergroups> getGroups() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Usergroups.findAll");
        
        return q.getResultList();
    }

}
