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
public class GroupmembersPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "GroupID")
    private int groupID;
    @Basic(optional = false)
    @NotNull
    @Column(name = "UserID")
    private int userID;

    public GroupmembersPK() {
    }

    public GroupmembersPK(int groupID, int userID) {
        this.groupID = groupID;
        this.userID = userID;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) groupID;
        hash += (int) userID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GroupmembersPK)) {
            return false;
        }
        GroupmembersPK other = (GroupmembersPK) object;
        if (this.groupID != other.groupID) {
            return false;
        }
        if (this.userID != other.userID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.GroupmembersPK[ groupID=" + groupID + ", userID=" + userID + " ]";
    }
    
}
