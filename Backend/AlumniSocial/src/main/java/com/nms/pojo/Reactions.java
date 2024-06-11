/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "reactions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reactions.findAll", query = "SELECT r FROM Reactions r"),
    @NamedQuery(name = "Reactions.findByUserID", query = "SELECT r FROM Reactions r WHERE r.reactionsPK.userID = :userID"),
    @NamedQuery(name = "Reactions.findByPostID", query = "SELECT r FROM Reactions r WHERE r.reactionsPK.postID = :postID"),
    @NamedQuery(name = "Reactions.findByUserIDAndPostID", query = "SELECT r FROM Reactions r WHERE r.reactionsPK.userID = :userID AND r.reactionsPK.postID = :postID"),
    @NamedQuery(name = "Reactions.findByReactionType", query = "SELECT r FROM Reactions r WHERE r.reactionType = :reactionType"),
    @NamedQuery(name = "Reactions.findByCreatedAt", query = "SELECT r FROM Reactions r WHERE r.createdAt = :createdAt")

})
public class Reactions implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReactionsPK reactionsPK;
    @Size(max = 4)
    @Column(name = "ReactionType")
    private String reactionType;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "PostID", referencedColumnName = "PostID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private Posts posts;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    @JsonIgnore
    private Users users;

    public Reactions() {
    }

    public Reactions(ReactionsPK reactionsPK) {
        this.reactionsPK = reactionsPK;
    }

    public Reactions(int userID, int postID) {
        this.reactionsPK = new ReactionsPK(userID, postID);
    }

    public ReactionsPK getReactionsPK() {
        return reactionsPK;
    }

    public void setReactionsPK(ReactionsPK reactionsPK) {
        this.reactionsPK = reactionsPK;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Posts getPosts() {
        return posts;
    }

    public void setPosts(Posts posts) {
        this.posts = posts;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reactionsPK != null ? reactionsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reactions)) {
            return false;
        }
        Reactions other = (Reactions) object;
        if ((this.reactionsPK == null && other.reactionsPK != null) || (this.reactionsPK != null && !this.reactionsPK.equals(other.reactionsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Reactions[ reactionsPK=" + reactionsPK + " ]";
    }

}
