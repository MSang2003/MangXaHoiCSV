/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "groupmembers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Groupmembers.findAll", query = "SELECT g FROM Groupmembers g"),
    @NamedQuery(name = "Groupmembers.findByGroupID", query = "SELECT g FROM Groupmembers g WHERE g.groupmembersPK.groupID = :groupID"),
    @NamedQuery(name = "Groupmembers.findByUserID", query = "SELECT g FROM Groupmembers g WHERE g.groupmembersPK.userID = :userID"),
    @NamedQuery(name = "Groupmembers.findByCreatedAt", query = "SELECT g FROM Groupmembers g WHERE g.createdAt = :createdAt")})
public class Groupmembers implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected GroupmembersPK groupmembersPK;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "GroupID", referencedColumnName = "GroupID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Usergroups usergroups;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Groupmembers() {
    }

    public Groupmembers(GroupmembersPK groupmembersPK) {
        this.groupmembersPK = groupmembersPK;
    }

    public Groupmembers(int groupID, int userID) {
        this.groupmembersPK = new GroupmembersPK(groupID, userID);
    }

    public GroupmembersPK getGroupmembersPK() {
        return groupmembersPK;
    }

    public void setGroupmembersPK(GroupmembersPK groupmembersPK) {
        this.groupmembersPK = groupmembersPK;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Usergroups getUsergroups() {
        return usergroups;
    }

    public void setUsergroups(Usergroups usergroups) {
        this.usergroups = usergroups;
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
        hash += (groupmembersPK != null ? groupmembersPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupmembers)) {
            return false;
        }
        Groupmembers other = (Groupmembers) object;
        if ((this.groupmembersPK == null && other.groupmembersPK != null) || (this.groupmembersPK != null && !this.groupmembersPK.equals(other.groupmembersPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Groupmembers[ groupmembersPK=" + groupmembersPK + " ]";
    }
    
}
