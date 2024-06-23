/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories.impl;

import com.nms.pojo.Comments;
import com.nms.pojo.Users;
import com.nms.repositories.CommentRepository;
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
public class CommentRepositoryImpl implements CommentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Map<String, Object>> getCommentsByPostId(int postId) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Comments> root = q.from(Comments.class);

        Join<Comments, Users> userJoin = root.join("userID", JoinType.LEFT);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("postID"), postId));

        // Lựa chọn các trường cần thiết
        q.multiselect(
                root.get("commentID"),
                root.get("content"),
                root.get("createdAt"),
                userJoin.get("userName"), // Tên người dùng
                userJoin.get("avatar") // Avatar của người dùng
        )
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(b.desc(root.get("createdAt")));

        Query query = s.createQuery(q);

        List<Object[]> result = query.getResultList();
        List<Map<String, Object>> mappedResults = new ArrayList<>();

        for (Object[] row : result) {

            Map<String, Object> map = new HashMap<>();
            map.put("commentID", row[0]);
            map.put("content", row[1]);
            map.put("createdAt", row[2]);
            map.put("userName", row[3]);
            map.put("avatar", row[4]);

            mappedResults.add(map);
        }

        return mappedResults;

    }

    @Override
    public void deleteCommentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Comments comment = s.get(Comments.class, id);
        s.delete(comment);
    }

    @Override
    public Comments getCommentById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Comments.class, id);
    }

    @Override
    public void deleteComment(Comments comment) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(comment);
    }

    @Override
    public void createOrUpdateComment(Comments comment) {
        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(comment);
    }

}
