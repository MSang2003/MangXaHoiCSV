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
@Table(name = "invitationrecipients")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Invitationrecipients.findAll", query = "SELECT i FROM Invitationrecipients i"),
    @NamedQuery(name = "Invitationrecipients.findByInvitationID", query = "SELECT i FROM Invitationrecipients i WHERE i.invitationrecipientsPK.invitationID = :invitationID"),
    @NamedQuery(name = "Invitationrecipients.findByRecipientID", query = "SELECT i FROM Invitationrecipients i WHERE i.invitationrecipientsPK.recipientID = :recipientID"),
    @NamedQuery(name = "Invitationrecipients.findByCreatedAt", query = "SELECT i FROM Invitationrecipients i WHERE i.createdAt = :createdAt")})
public class Invitationrecipients implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InvitationrecipientsPK invitationrecipientsPK;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "InvitationID", referencedColumnName = "InvitationID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Invitations invitations;
    @JoinColumn(name = "RecipientID", referencedColumnName = "UserID", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Users users;

    public Invitationrecipients() {
    }

    public Invitationrecipients(InvitationrecipientsPK invitationrecipientsPK) {
        this.invitationrecipientsPK = invitationrecipientsPK;
    }

    public Invitationrecipients(int invitationID, int recipientID) {
        this.invitationrecipientsPK = new InvitationrecipientsPK(invitationID, recipientID);
    }

    public InvitationrecipientsPK getInvitationrecipientsPK() {
        return invitationrecipientsPK;
    }

    public void setInvitationrecipientsPK(InvitationrecipientsPK invitationrecipientsPK) {
        this.invitationrecipientsPK = invitationrecipientsPK;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Invitations getInvitations() {
        return invitations;
    }

    public void setInvitations(Invitations invitations) {
        this.invitations = invitations;
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
        hash += (invitationrecipientsPK != null ? invitationrecipientsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Invitationrecipients)) {
            return false;
        }
        Invitationrecipients other = (Invitationrecipients) object;
        if ((this.invitationrecipientsPK == null && other.invitationrecipientsPK != null) || (this.invitationrecipientsPK != null && !this.invitationrecipientsPK.equals(other.invitationrecipientsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Invitationrecipients[ invitationrecipientsPK=" + invitationrecipientsPK + " ]";
    }
    
}
