/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findByPostID", query = "SELECT p FROM Posts p WHERE p.postID = :postID"),
    @NamedQuery(name = "Posts.findByPostType", query = "SELECT p FROM Posts p WHERE p.postType = :postType"),
    @NamedQuery(name = "Posts.findByIsCommentLocked", query = "SELECT p FROM Posts p WHERE p.isCommentLocked = :isCommentLocked")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PostID")
    private Integer postID;
    @Lob
    @Size(max = 65535)
    @Column(name = "Content")
    private String content;
    @Size(max = 10)
    @Column(name = "PostType")
    private String postType;
    @Column(name = "IsCommentLocked")
    private Boolean isCommentLocked;
    @OneToMany(mappedBy = "postID")
    private Set<Comments> commentsSet;
    @OneToMany(mappedBy = "postID")
    private Set<Invitations> invitationsSet;
    @OneToMany(mappedBy = "postID")
    private Set<Surveys> surveysSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posts")
    private Set<Reactions> reactionsSet;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne
    private Users userID;

    public Posts() {
    }

    public Posts(Integer postID) {
        this.postID = postID;
    }

    public Integer getPostID() {
        return postID;
    }

    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Boolean getIsCommentLocked() {
        return isCommentLocked;
    }

    public void setIsCommentLocked(Boolean isCommentLocked) {
        this.isCommentLocked = isCommentLocked;
    }

    @XmlTransient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    @XmlTransient
    public Set<Invitations> getInvitationsSet() {
        return invitationsSet;
    }

    public void setInvitationsSet(Set<Invitations> invitationsSet) {
        this.invitationsSet = invitationsSet;
    }

    @XmlTransient
    public Set<Surveys> getSurveysSet() {
        return surveysSet;
    }

    public void setSurveysSet(Set<Surveys> surveysSet) {
        this.surveysSet = surveysSet;
    }

    @XmlTransient
    public Set<Reactions> getReactionsSet() {
        return reactionsSet;
    }

    public void setReactionsSet(Set<Reactions> reactionsSet) {
        this.reactionsSet = reactionsSet;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postID != null ? postID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postID == null && other.postID != null) || (this.postID != null && !this.postID.equals(other.postID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Posts[ postID=" + postID + " ]";
    }
    
}