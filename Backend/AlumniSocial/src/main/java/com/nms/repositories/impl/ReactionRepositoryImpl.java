/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories.impl;

import com.nms.pojo.Reactions;
import com.nms.pojo.ReactionsPK;
import com.nms.pojo.Users;
import com.nms.repositories.CommentRepository;
import com.nms.repositories.PostRepository;
import com.nms.repositories.ReactionRepository;
import com.nms.repositories.StatsRepository;
import com.nms.repositories.UsersRepository;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
public class ReactionRepositoryImpl implements ReactionRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public void createOrUpdateReaction(Reactions reaction) {
        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(reaction);
    }

    @Override
    public List<Object[]> getReactionByPostId(int id) {
        Session s = this.factory.getObject().getCurrentSession();

        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);
        Root<Reactions> root = q.from(Reactions.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("posts"), id));

        q.multiselect(
                root.get("reactionType"),
                root.get("createdAt")
        )
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(b.desc(root.get("createdAt")));

        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public Reactions getReactionByUserIDPostID(int userID, int postID) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Reactions.findByUserIDAndPostID");
        q.setParameter("userID", userID);
        q.setParameter("postID", postID);

        return (Reactions) q.getSingleResult();
    }

    @Override
    public void deleteReaction(Reactions reaction) {
        Session s = this.factory.getObject().getCurrentSession();
        s.delete(reaction);
    }

}
