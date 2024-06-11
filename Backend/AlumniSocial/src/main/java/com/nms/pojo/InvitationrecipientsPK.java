/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Admin
 */
@Embeddable
public class InvitationrecipientsPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "InvitationID")
    private int invitationID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "RecipientID")
    private int recipientID;

    public InvitationrecipientsPK() {
    }

    public InvitationrecipientsPK(int invitationID, int recipientID) {
        this.invitationID = invitationID;
        this.recipientID = recipientID;
    }

    public int getInvitationID() {
        return invitationID;
    }

    public void setInvitationID(int invitationID) {
        this.invitationID = invitationID;
    }

    public int getRecipientID() {
        return recipientID;
    }

    public void setRecipientID(int recipientID) {
        this.recipientID = recipientID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) invitationID;
        hash += (int) recipientID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InvitationrecipientsPK)) {
            return false;
        }
        InvitationrecipientsPK other = (InvitationrecipientsPK) object;
        if (this.invitationID != other.invitationID) {
            return false;
        }
        if (this.recipientID != other.recipientID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.InvitationrecipientsPK[ invitationID=" + invitationID + ", recipientID=" + recipientID + " ]";
    }
    
}
