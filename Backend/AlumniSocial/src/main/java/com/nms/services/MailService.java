/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Reactions;
import com.nms.pojo.ReactionsPK;
import com.nms.pojo.Users;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author Admin
 */
public interface MailService {

    public void sendMail(String to, String subject, String content);
}
