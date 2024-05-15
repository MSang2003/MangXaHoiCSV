/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Embeddable
public class ReactionsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "UserID")
    private int userID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PostID")
    private int postID;

    public ReactionsPK() {
    }

    public ReactionsPK(int userID, int postID) {
        this.userID = userID;
        this.postID = postID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) userID;
        hash += (int) postID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReactionsPK)) {
            return false;
        }
        ReactionsPK other = (ReactionsPK) object;
        if (this.userID != other.userID) {
            return false;
        }
        if (this.postID != other.postID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.ReactionsPK[ userID=" + userID + ", postID=" + postID + " ]";
    }
    
}
