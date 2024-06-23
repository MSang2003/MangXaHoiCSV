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
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
@PropertySource("classpath:configs.properties")
public class PostRepositoryImpl implements PostRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private Environment env;

    @Autowired
    private SimpleDateFormat dateFormatter;

    @Override
    public Map<String, Object> getPosts(Integer userID, Integer pageNumber, Boolean isProfile) {
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

        // Subqueries to count reactions of each type
        Subquery<Long> countHahaSubquery = q.subquery(Long.class);
        Root<Reactions> hahaReactionRoot = countHahaSubquery.from(Reactions.class);
        countHahaSubquery.select(b.count(hahaReactionRoot))
                .where(
                        b.equal(hahaReactionRoot.get("reactionsPK").get("postID"), postRoot.get("postID")),
                        b.equal(hahaReactionRoot.get("reactionType"), "Haha")
                );

        Subquery<Long> countLikeSubquery = q.subquery(Long.class);
        Root<Reactions> likeReactionRoot = countLikeSubquery.from(Reactions.class);
        countLikeSubquery.select(b.count(likeReactionRoot))
                .where(
                        b.equal(likeReactionRoot.get("reactionsPK").get("postID"), postRoot.get("postID")),
                        b.equal(likeReactionRoot.get("reactionType"), "Like")
                );

        Subquery<Long> countLoveSubquery = q.subquery(Long.class);
        Root<Reactions> loveReactionRoot = countLoveSubquery.from(Reactions.class);
        countLoveSubquery.select(b.count(loveReactionRoot))
                .where(
                        b.equal(loveReactionRoot.get("reactionsPK").get("postID"), postRoot.get("postID")),
                        b.equal(loveReactionRoot.get("reactionType"), "Love")
                );

        q.multiselect(
                postRoot.get("postID"),
                postRoot.get("content"),
                postRoot.get("createdAt"),
                postRoot.get("postType"),
                userJoin.get("name"),
                userJoin.get("avatar"),
                countHahaSubquery.getSelection(),
                countLikeSubquery.getSelection(),
                countLoveSubquery.getSelection(),
                b.count(commentJoin),
                invitationJoin.get("eventDetails"),
                surveyOptionsJoin.get("optionText"),
                postRoot.get("Image"),
                postRoot.get("isCommentLocked") // Add isCommentLocked field here
        )
                .groupBy(
                        postRoot.get("postID"),
                        userJoin.get("name"),
                        userJoin.get("avatar"),
                        invitationJoin.get("eventDetails"),
                        surveyOptionsJoin.get("optionText"),
                        postRoot.get("Image"),
                        postRoot.get("isCommentLocked") // Group by isCommentLocked field here
                )
                .orderBy(b.asc(userJoin.get("name")));

        if (isProfile == true) {
            q.where(b.equal(userJoin.get("userID"), userID));
        }

        Query query = s.createQuery(q);
        int pageSize = Integer.parseInt(env.getProperty("post.pageSize").toString());

        if (pageNumber != null) {
            int start = (pageNumber - 1) * pageSize;
            query.setFirstResult(start);
            query.setMaxResults(pageSize + 1); // Fetch one extra record to determine if there is a next page
        }

        List<Object[]> result = query.getResultList();
        boolean hasNext = result.size() > pageSize;

        if (hasNext) {
            result.remove(result.size() - 1); // Remove the extra record fetched for the next page check
        }

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
            map.put("avatar", row[5]);
            map.put("hahaCount", row[6]);
            map.put("likeCount", row[7]);
            map.put("loveCount", row[8]);
            map.put("commentCount", row[9]);
            map.put("eventDetails", row[10]);
            map.put("image", row[12]);
            map.put("isCommentLocked", row[13]); // Add isCommentLocked to the map

            if ("Invitation".equals(postType)) {
                map.put("eventDetails", row[10]);
            } else if ("Survey".equals(postType)) {
                if (!surveyOptionsMap.containsKey(postID)) {
                    surveyOptionsMap.put(postID, new ArrayList<>());
                }
                if (row[11] != null) {
                    surveyOptionsMap.get(postID).add((String) row[11]);
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

        Map<String, Object> response = new HashMap<>();
        response.put("posts", mappedResults);
        response.put("previous", pageNumber != null && pageNumber > 1);
        response.put("next", hasNext);

        return response;
    }

    @Override
    public void deletePostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Posts p = s.get(Posts.class, id);
        s.delete(p);
    }

    @Override
    public void createOrUpdateStatusPost(Posts post) {

        Session s = this.factory.getObject().getCurrentSession();
        s.saveOrUpdate(post);
    }

    @Override
    public Posts getPostById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Posts.class, id);
    }

    @Override
    public void createInvitationPost(Posts post, Invitations invitation) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(post);
        s.save(invitation);
    }

    @Override
    public void createInvitationPost(Posts post, Surveys survey, List<Surveyoptions> surveyOptions) {
        Session s = this.factory.getObject().getCurrentSession();

        s.save(post);
        s.save(survey);
        for (Surveyoptions option : surveyOptions) {
            s.save(option);
        }
    }

}
