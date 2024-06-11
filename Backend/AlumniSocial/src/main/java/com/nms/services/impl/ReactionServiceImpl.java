/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.nms.pojo.Comments;
import com.nms.pojo.Posts;
import com.nms.pojo.Reactions;
import com.nms.pojo.ReactionsPK;
import com.nms.repositories.CommentRepository;
import com.nms.repositories.PostRepository;
import com.nms.repositories.ReactionRepository;
import com.nms.repositories.StatsRepository;
import com.nms.services.CommentService;
import com.nms.services.PostService;
import com.nms.services.ReactionService;
import com.nms.services.StatsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class ReactionServiceImpl implements ReactionService {

    @Autowired
    private ReactionRepository reactionRepo;

 

    @Override
    public void createOrUpdateReaction(Reactions reaction) {
        this.reactionRepo.createOrUpdateReaction(reaction);
    }

    @Override
    public List<Object[]> getReactionByPostId(int id) {

        return this.reactionRepo.getReactionByPostId(id);
    }

    @Override
    public Reactions getReactionByUserIDPostID(int userID, int postID) {
        return this.reactionRepo.getReactionByUserIDPostID(userID, postID);
    }

    @Override
    public void deleteReaction(Reactions reaction) {
        this.reactionRepo.deleteReaction(reaction);
    }

}
