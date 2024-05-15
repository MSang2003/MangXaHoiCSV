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
import javax.persistence.ManyToMany;
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
@Table(name = "users")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findByUserID", query = "SELECT u FROM Users u WHERE u.userID = :userID"),
    @NamedQuery(name = "Users.findByRole", query = "SELECT u FROM Users u WHERE u.role = :role"),
    @NamedQuery(name = "Users.findByEmail", query = "SELECT u FROM Users u WHERE u.email = :email"),
    @NamedQuery(name = "Users.findByPassword", query = "SELECT u FROM Users u WHERE u.password = :password"),
    @NamedQuery(name = "Users.findByAvatar", query = "SELECT u FROM Users u WHERE u.avatar = :avatar"),
    @NamedQuery(name = "Users.findByCoverImage", query = "SELECT u FROM Users u WHERE u.coverImage = :coverImage"),
    @NamedQuery(name = "Users.findByStudentID", query = "SELECT u FROM Users u WHERE u.studentID = :studentID"),
    @NamedQuery(name = "Users.findByIsVerified", query = "SELECT u FROM Users u WHERE u.isVerified = :isVerified"),
    @NamedQuery(name = "Users.findByIsLocked", query = "SELECT u FROM Users u WHERE u.isLocked = :isLocked")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "UserID")
    private Integer userID;
    @Size(max = 7)
    @Column(name = "Role")
    private String role;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "Email")
    private String email;
    @Size(max = 255)
    @Column(name = "Password")
    private String password;
    @Size(max = 255)
    @Column(name = "Avatar")
    private String avatar;
    @Size(max = 255)
    @Column(name = "CoverImage")
    private String coverImage;
    @Size(max = 255)
    @Column(name = "StudentID")
    private String studentID;
    @Column(name = "IsVerified")
    private Boolean isVerified;
    @Column(name = "IsLocked")
    private Boolean isLocked;
    @ManyToMany(mappedBy = "usersSet")
    private Set<Invitations> invitationsSet;
    @ManyToMany(mappedBy = "usersSet")
    private Set<Usergroups> usergroupsSet;
    @OneToMany(mappedBy = "userID")
    private Set<Comments> commentsSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private Set<Reactions> reactionsSet;
    @OneToMany(mappedBy = "userID")
    private Set<Posts> postsSet;
    @OneToMany(mappedBy = "userID")
    private Set<Surveyresponses> surveyresponsesSet;

    public Users() {
    }

    public Users(Integer userID) {
        this.userID = userID;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(Boolean isLocked) {
        this.isLocked = isLocked;
    }

    @XmlTransient
    public Set<Invitations> getInvitationsSet() {
        return invitationsSet;
    }

    public void setInvitationsSet(Set<Invitations> invitationsSet) {
        this.invitationsSet = invitationsSet;
    }

    @XmlTransient
    public Set<Usergroups> getUsergroupsSet() {
        return usergroupsSet;
    }

    public void setUsergroupsSet(Set<Usergroups> usergroupsSet) {
        this.usergroupsSet = usergroupsSet;
    }

    @XmlTransient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    @XmlTransient
    public Set<Reactions> getReactionsSet() {
        return reactionsSet;
    }

    public void setReactionsSet(Set<Reactions> reactionsSet) {
        this.reactionsSet = reactionsSet;
    }

    @XmlTransient
    public Set<Posts> getPostsSet() {
        return postsSet;
    }

    public void setPostsSet(Set<Posts> postsSet) {
        this.postsSet = postsSet;
    }

    @XmlTransient
    public Set<Surveyresponses> getSurveyresponsesSet() {
        return surveyresponsesSet;
    }

    public void setSurveyresponsesSet(Set<Surveyresponses> surveyresponsesSet) {
        this.surveyresponsesSet = surveyresponsesSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userID != null ? userID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.userID == null && other.userID != null) || (this.userID != null && !this.userID.equals(other.userID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Users[ userID=" + userID + " ]";
    }
    
}
