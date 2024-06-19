/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories.impl;

import com.nms.pojo.Comments;
import com.nms.pojo.Invitations;
import com.nms.pojo.Posts;
import com.nms.pojo.Reactions;
import com.nms.pojo.Surveyoptions;
import com.nms.pojo.Surveys;
import com.nms.pojo.Users;
import com.nms.repositories.PostRepository;
import com.nms.repositories.StatsRepository;
import com.nms.repositories.UsersRepository;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
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
import javax.persistence.criteria.Subquery;
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

    SimpleDateFormat dateFormatter = new SimpleDateFormat("MM-dd-yyyy", Locale.US);

    @Override
    public List<Map<String, Object>> getPosts() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Posts> postRoot = q.from(Posts.class);
        Join<Posts, Users> userJoin = postRoot.join("userID");
        Join<Posts, Reactions> reactionJoin = postRoot.join("reactionsSet", JoinType.LEFT);
        Join<Posts, Comments> commentJoin = postRoot.join("commentsSet", JoinType.LEFT);

        // Left joins for Invitations and Surveys
        Join<Posts, Invitations> invitationJoin = postRoot.join("invitationsSet", JoinType.LEFT);
        Join<Posts, Surveys> surveyJoin = postRoot.join("surveysSet", JoinType.LEFT);
        Join<Surveys, Surveyoptions> surveyOptionsJoin = surveyJoin.join("surveyoptionsSet", JoinType.LEFT);

        q.multiselect(
                postRoot.get("postID"),
                postRoot.get("content"),
                postRoot.get("createdAt"),
                postRoot.get("postType"),
                userJoin.get("name"),
                b.count(reactionJoin), // Count of reactions
                b.count(commentJoin), // Count of comments
                invitationJoin.get("eventDetails"),
                surveyOptionsJoin.get("optionText"),
                postRoot.get("Image") // Add Image field here
        )
                .groupBy(
                        postRoot.get("postID"),
                        invitationJoin.get("eventDetails"),
                        surveyOptionsJoin.get("optionText"),
                        postRoot.get("Image") // Add Image field here
                )
                .orderBy(b.asc(userJoin.get("name")));

        Query query = s.createQuery(q);
        List<Object[]> result = query.getResultList();
        Map<Integer, List<String>> surveyOptionsMap = new HashMap<>();
        List<Map<String, Object>> mappedResults = new ArrayList<>();

        for (Object[] row : result) {
            Integer postID = (Integer) row[0];
            String postType = (String) row[3];
            Date createdAtDate = (Date) row[2];
            String formattedDate = dateFormatter.format(createdAtDate);

            Map<String, Object> map = new HashMap<>();
            map.put("postID", row[0]);
            map.put("content", row[1]);
            map.put("createdAt", formattedDate);
            map.put("postType", row[3]);
            map.put("userName", row[4]);
            map.put("reactionCount", row[5]);
            map.put("commentCount", row[6]);
            map.put("image", row[9]);

            if ("Invitation".equals(postType)) {
                map.put("eventDetails", row[7]);
            } else if ("Survey".equals(postType)) {
                if (!surveyOptionsMap.containsKey(postID)) {
                    surveyOptionsMap.put(postID, new ArrayList<>());
                }
                if (row[8] != null) {
                    surveyOptionsMap.get(postID).add((String) row[8]);
                }
            }

            mappedResults.add(map);
        }

        for (Map<String, Object> postMap : mappedResults) {
            Integer postID = (Integer) postMap.get("postID");
            if ("Survey".equals(postMap.get("postType"))) {
                postMap.put("surveyOptions", surveyOptionsMap.get(postID));
            }
        }

        return mappedResults;
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
