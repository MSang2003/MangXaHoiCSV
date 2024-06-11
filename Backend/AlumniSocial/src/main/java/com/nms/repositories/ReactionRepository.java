/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories;

import com.nms.pojo.Reactions;
import com.nms.pojo.ReactionsPK;
import java.util.List;

/**
 *
 * @author Admin
 */
public interface ReactionRepository {

    Reactions getReactionByUserIDPostID(int userID, int postID);

    void deleteReaction(Reactions reaction);

    void createOrUpdateReaction(Reactions reaction);

    List<Object[]> getReactionByPostId(int id);
}
