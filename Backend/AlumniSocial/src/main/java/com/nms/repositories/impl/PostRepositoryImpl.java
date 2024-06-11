/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories.impl;

import com.nms.pojo.Posts;
import com.nms.pojo.Reactions;
import com.nms.pojo.Users;
import com.nms.repositories.PostRepository;
import com.nms.repositories.StatsRepository;
import com.nms.repositories.UsersRepository;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
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
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> getPosts() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Posts> postRoot = q.from(Posts.class);

        Join<Posts, Users> userJoin = postRoot.join("userID");

        q.multiselect(postRoot.get("postID"), postRoot.get("content"), postRoot.get("createdAt"), postRoot.get("postType"), userJoin.get("name"))
                .orderBy(b.asc(userJoin.get("name")));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public void deletePostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Posts p = s.get(Posts.class, id);
        s.delete(p);
    }

    @Override
    public void createOrUpdatePost(Posts post) {
        
        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(post);
    }

    @Override
    public Posts getPostById(int id) {
                Session s = this.factory.getObject().getCurrentSession();
                return s.get(Posts.class, id);
    }

}
