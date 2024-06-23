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
    @NamedQuery(name = "Usergroups.findByGroupName", query = "SELECT u FROM Usergroups u WHERE u.groupName = :groupName"),
    @NamedQuery(name = "Usergroups.findByCreatedAt", query = "SELECT u FROM Usergroups u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Usergroups.findByUpdatedAt", query = "SELECT u FROM Usergroups u WHERE u.updatedAt = :updatedAt")})
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
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "usergroups", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Groupmembers> groupmembersSet;

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
    public Set<Groupmembers> getGroupmembersSet() {
        return groupmembersSet;
    }

    public void setGroupmembersSet(Set<Groupmembers> groupmembersSet) {
        this.groupmembersSet = groupmembersSet;
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
