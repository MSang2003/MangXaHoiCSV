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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "invitations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitations.findAll", query = "SELECT i FROM Invitations i"),
    @NamedQuery(name = "Invitations.findByInvitationID", query = "SELECT i FROM Invitations i WHERE i.invitationID = :invitationID")})
public class Invitations implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "InvitationID")
    private Integer invitationID;
    @Lob
    @Size(max = 65535)
    @Column(name = "EventDetails")
    private String eventDetails;
    @JoinTable(name = "invitationrecipients", joinColumns = {
        @JoinColumn(name = "InvitationID", referencedColumnName = "InvitationID")}, inverseJoinColumns = {
        @JoinColumn(name = "RecipientID", referencedColumnName = "UserID")})
    @ManyToMany
    private Set<Users> usersSet;
    @JoinColumn(name = "PostID", referencedColumnName = "PostID")
    @ManyToOne
    private Posts postID;

    public Invitations() {
    }

    public Invitations(Integer invitationID) {
        this.invitationID = invitationID;
    }

    public Integer getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(Integer invitationID) {
        this.invitationID = invitationID;
    }

    public String getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(String eventDetails) {
        this.eventDetails = eventDetails;
    }

    @XmlTransient
    public Set<Users> getUsersSet() {
        return usersSet;
    }

    public void setUsersSet(Set<Users> usersSet) {
        this.usersSet = usersSet;
    }

    public Posts getPostID() {
        return postID;
    }

    public void setPostID(Posts postID) {
        this.postID = postID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invitationID != null ? invitationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitations)) {
            return false;
        }
        Invitations other = (Invitations) object;
        if ((this.invitationID == null && other.invitationID != null) || (this.invitationID != null && !this.invitationID.equals(other.invitationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Invitations[ invitationID=" + invitationID + " ]";
    }
    
}
