/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories.impl;

import com.nms.pojo.Users;
import com.nms.repositories.UsersRepository;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Repository
@Transactional
public class UsersRepositoryImpl implements UsersRepository {

    @Autowired
    private LocalSessionFactoryBean factory;

    @Autowired
    private BCryptPasswordEncoder passswordEncoder;

    @Override
    public List<Users> getUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createNamedQuery("Users.findAll");
        return q.getResultList();
    }

    @Override
    public void addUser(Users user) {
        Session s = this.factory.getObject().getCurrentSession();
        user.setPassword(this.passswordEncoder.encode(user.getPassword()));
        s.save(user);
    }

    @Override
    public Users getUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Users.class, id);
    }

    @Override
    public void deleteUserById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        Users u = this.getUserById(id);
        s.delete(u);
    }

    @Override
    public void updateUser(Users user) {
        Session s = this.factory.getObject().getCurrentSession();
        user.setPassword(this.passswordEncoder.encode(user.getPassword()));
        s.update(user);
    }

    @Override
    public Users getUserByUsername(String userName) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM Users WHERE userName = :userName");
        q.setParameter("userName", userName);
        return (Users) q.getSingleResult();
    }

    @Override
    public boolean authUser(String username, String password) {
        Users u = this.getUserByUsername(username);

        return this.passswordEncoder.matches(password, u.getPassword());
    }

    @Override
    public void checkChangePassWord() {
        Session session = this.factory.getObject().getCurrentSession();

        Query query = session.createNamedQuery("Users.findByRole");
        query.setParameter("role", "Teacher");
        List<Users> userList = query.getResultList();
        LocalDateTime now = LocalDateTime.now();

        for (Users user : userList) {
            Date createdAtDate = user.getCreatedAt(); 

            Instant instant = createdAtDate.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            LocalDateTime createdAt = instant.atZone(zoneId).toLocalDateTime();

            Duration duration = Duration.between(createdAt, now);

            if (duration.toHours() >= 24 && this.passswordEncoder.matches("ou@123", user.getPassword())) {
                user.setIsLocked(true);
                session.update(user);
            }
        }
    }

}
