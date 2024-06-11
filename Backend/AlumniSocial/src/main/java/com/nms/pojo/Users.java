/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

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
    @NamedQuery(name = "Users.findByIsLocked", query = "SELECT u FROM Users u WHERE u.isLocked = :isLocked"),
    @NamedQuery(name = "Users.findByName", query = "SELECT u FROM Users u WHERE u.name = :name"),
    @NamedQuery(name = "Users.findByUserName", query = "SELECT u FROM Users u WHERE u.userName = :userName"),
    @NamedQuery(name = "Users.findByDoB", query = "SELECT u FROM Users u WHERE u.doB = :doB"),
    @NamedQuery(name = "Users.findBySex", query = "SELECT u FROM Users u WHERE u.sex = :sex"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt")})
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
    @Size(max = 20)
    @Column(name = "Phone")
    private String phone;
    @Column(name = "IsVerified")
    private Boolean isVerified;
    @Column(name = "IsLocked")
    private Boolean isLocked;
    @Size(max = 255)
    @NotNull(message = "{user.name.nullErr}")
    @Column(name = "Name")
    private String name;
    @Size(max = 100)
    @Column(name = "Username")
    private String userName;
    @Column(name = "DoB")
    @Temporal(TemporalType.DATE)
    private Date doB;
    @Size(max = 6)
    @Column(name = "Sex")
    private String sex;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Invitationrecipients> invitationrecipientsSet;
    @OneToMany(mappedBy = "userID", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Comments> commentsSet;
    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Groupmembers> groupmembersSet;
    @JsonIgnore
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reactions> reactionsSet;
    @OneToMany(mappedBy = "userID", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Posts> postsSet;
    @JsonIgnore

    @OneToMany(mappedBy = "userID", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Surveyresponses> surveyresponsesSet;

    @Transient
    private MultipartFile avatarFile;

    @Transient
    private MultipartFile coverImageFile;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDoB() {
        return doB;
    }

    public void setDoB(Date doB) {
        this.doB = doB;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlTransient
    public Set<Invitationrecipients> getInvitationrecipientsSet() {
        return invitationrecipientsSet;
    }

    public void setInvitationrecipientsSet(Set<Invitationrecipients> invitationrecipientsSet) {
        this.invitationrecipientsSet = invitationrecipientsSet;
    }

    @XmlTransient
    public Set<Comments> getCommentsSet() {
        return commentsSet;
    }

    public void setCommentsSet(Set<Comments> commentsSet) {
        this.commentsSet = commentsSet;
    }

    @XmlTransient
    public Set<Groupmembers> getGroupmembersSet() {
        return groupmembersSet;
    }

    public void setGroupmembersSet(Set<Groupmembers> groupmembersSet) {
        this.groupmembersSet = groupmembersSet;
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

    /**
     * @return the avatarFile
     */
    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    /**
     * @param avatarFile the avatarFile to set
     */
    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the coverImageFile
     */
    public MultipartFile getCoverImageFile() {
        return coverImageFile;
    }

    /**
     * @param coverImageFile the coverImageFile to set
     */
    public void setCoverImageFile(MultipartFile coverImageFile) {
        this.coverImageFile = coverImageFile;
    }

}
