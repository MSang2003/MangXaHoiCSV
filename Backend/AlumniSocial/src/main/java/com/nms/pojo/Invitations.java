/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
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
@Table(name = "invitations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitations.findAll", query = "SELECT i FROM Invitations i"),
    @NamedQuery(name = "Invitations.findByInvitationID", query = "SELECT i FROM Invitations i WHERE i.invitationID = :invitationID"),
    @NamedQuery(name = "Invitations.findByCreatedAt", query = "SELECT i FROM Invitations i WHERE i.createdAt = :createdAt"),
    @NamedQuery(name = "Invitations.findByUpdatedAt", query = "SELECT i FROM Invitations i WHERE i.updatedAt = :updatedAt")})
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
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "invitations")
    private Set<Invitationrecipients> invitationrecipientsSet;
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
