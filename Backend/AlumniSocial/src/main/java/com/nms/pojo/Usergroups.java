/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "usergroups")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usergroups.findAll", query = "SELECT u FROM Usergroups u"),
    @NamedQuery(name = "Usergroups.findByGroupID", query = "SELECT u FROM Usergroups u WHERE u.groupID = :groupID"),
    @NamedQuery(name = "Usergroups.findByGroupName", query = "SELECT u FROM Usergroups u WHERE u.groupName = :groupName")})
public class Usergroups implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "GroupID")
    private Integer groupID;
    @Size(max = 255)
    @Column(name = "GroupName")
    private String groupName;
    @JoinTable(name = "groupmembers", joinColumns = {
        @JoinColumn(name = "GroupID", referencedColumnName = "GroupID")}, inverseJoinColumns = {
        @JoinColumn(name = "UserID", referencedColumnName = "UserID")})
    @ManyToMany
    private Set<Users> usersSet;

    public Usergroups() {
    }

    public Usergroups(Integer groupID) {
        this.groupID = groupID;
    }

    public Integer getGroupID() {
        return groupID;
    }

    public void setGroupID(Integer groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @XmlTransient
    public Set<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupID != null ? groupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usergroups)) {
            return false;
        }
        Usergroups other = (Usergroups) object;
        if ((this.groupID == null && other.groupID != null) || (this.groupID != null && !this.groupID.equals(other.groupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Usergroups[ groupID=" + groupID + " ]";
    }
    
}
