/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories.impl;

import com.nms.pojo.Posts;
import com.nms.pojo.Surveyoptions;
import com.nms.pojo.Surveyresponses;
import com.nms.pojo.Surveys;
import com.nms.pojo.Users;
import com.nms.repositories.StatsRepository;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
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
public class StatsRepositoryImpl implements StatsRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public List<Object[]> statsAlumniByYear() {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Users> root = q.from(Users.class);

        int currentYear = Year.now().getValue();
        int endYear = (currentYear % 100) - 4;
        Expression<String> khoa = b.substring(root.get("studentID"), 1, 2);

        List<String> validYears = IntStream.rangeClosed(0, endYear)
                .mapToObj(i -> String.format("%02d", i))
                .collect(Collectors.toList());
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("role"), "Alumni"));          // Điều kiện role là Alumni
        predicates.add(khoa.in(validYears));                          // Điều kiện studentID nằm trong validYears

        q.multiselect(khoa, b.count(root.get("userID")))
                .where(predicates.toArray(Predicate[]::new))
                .groupBy(khoa)
                .orderBy(b.asc(khoa));

        Query query = s.createQuery(q);
        return query.getResultList();
    }

    @Override
    public List<Object[]> statsAmountUser(String role, int year, String period) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Users> root = q.from(Users.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(root.get("role"), role));
        predicates.add(b.equal(b.function("YEAR", Integer.class, root.get("createdAt")), year));

        if ("MONTH".equalsIgnoreCase(period)) {
            q.multiselect(b.count(root), b.function("MONTH", Integer.class, root.get("createdAt")))
                    .groupBy(b.function("MONTH", Integer.class, root.get("createdAt")))
                    .where(predicates.toArray(Predicate[]::new));

        } else if ("QUATER".equalsIgnoreCase(period)) {
            Expression<Integer> quarterExpr = b.<Integer>selectCase()
                    .when(b.between(b.function("MONTH", Integer.class, root.get("createdAt")), 1, 3), 1)
                    .when(b.between(b.function("MONTH", Integer.class, root.get("createdAt")), 4, 6), 2)
                    .when(b.between(b.function("MONTH", Integer.class, root.get("createdAt")), 7, 9), 3)
                    .otherwise(4);

            q.multiselect(quarterExpr, b.count(root))
                    .groupBy(quarterExpr)
                    .where(predicates.toArray(Predicate[]::new));
        }
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Object[]> statsPostByUser(String name, int year, String period) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Posts> root = q.from(Posts.class);

        List<Predicate> predicates = new ArrayList<>();
        predicates.add(b.equal(b.function("YEAR", Integer.class, root.get("createdAt")), year));

        if ("MONTH".equalsIgnoreCase(period)) {
            q.multiselect(b.count(root), b.function("MONTH", Integer.class, root.get("createdAt")))
                    .groupBy(b.function("MONTH", Integer.class, root.get("createdAt")))
                    .where(predicates.toArray(Predicate[]::new));

        } else if ("QUATER".equalsIgnoreCase(period)) {
            Expression<Integer> quarterExpr = b.<Integer>selectCase()
                    .when(b.between(b.function("MONTH", Integer.class, root.get("createdAt")), 1, 3), 1)
                    .when(b.between(b.function("MONTH", Integer.class, root.get("createdAt")), 4, 6), 2)
                    .when(b.between(b.function("MONTH", Integer.class, root.get("createdAt")), 7, 9), 3)
                    .otherwise(4);

            q.multiselect(quarterExpr, b.count(root))
                    .groupBy(quarterExpr)
                    .where(predicates.toArray(Predicate[]::new));
        }
        Query query = s.createQuery(q);

        return query.getResultList();
    }

    @Override
    public List<Map<String, Object>> statsSurveyResponses(Integer surveyID) {

        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> q = b.createQuery(Object[].class);

        Root<Surveyresponses> root = q.from(Surveyresponses.class);
        Join<Surveyresponses, Surveyoptions> joinOption = root.join("optionID");
        Join<Surveyresponses, Surveys> joinSurvey = root.join("surveyID");

        q.multiselect(joinSurvey.get("surveyID"), joinSurvey.get("question"), joinOption.get("optionText"), b.count(root.get("responseID")))
                .groupBy(joinSurvey.get("surveyID"), joinSurvey.get("question"), joinOption.get("optionID"), joinOption.get("optionText"))
                .orderBy(b.asc(joinSurvey.get("surveyID")), b.asc(joinOption.get("optionID")));

        // Thêm điều kiện where để lọc theo surveyID
        q.where(b.equal(joinSurvey.get("surveyID"), surveyID));
        Query query = s.createQuery(q);
        
        
        List<Object[]> result = query.getResultList();
        Map<Integer, List<String>> surveyOptionsMap = new HashMap<>();
        List<Map<String, Object>> mappedResults = new ArrayList<>();

        for (Object[] row : result) {

            Map<String, Object> map = new HashMap<>();
            map.put("surveyID", row[0]);
            map.put("question", row[1]);
            map.put("optionText", row[2]);
            map.put("amount", row[3]);

            mappedResults.add(map);
        }

        return mappedResults;
    }

}
