/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "surveyresponses")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surveyresponses.findAll", query = "SELECT s FROM Surveyresponses s"),
    @NamedQuery(name = "Surveyresponses.findByResponseID", query = "SELECT s FROM Surveyresponses s WHERE s.responseID = :responseID"),
    @NamedQuery(name = "Surveyresponses.findByCreatedAt", query = "SELECT s FROM Surveyresponses s WHERE s.createdAt = :createdAt")})
public class Surveyresponses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ResponseID")
    private Integer responseID;
    @Lob
    @Size(max = 65535)
    @Column(name = "Answer")
    private String answer;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @JoinColumn(name = "OptionID", referencedColumnName = "OptionID")
    @ManyToOne
    private Surveyoptions optionID;
    @JoinColumn(name = "SurveyID", referencedColumnName = "SurveyID")
    @ManyToOne
    private Surveys surveyID;
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
    @ManyToOne
    private Users userID;

    public Surveyresponses() {
    }

    public Surveyresponses(Integer responseID) {
        this.responseID = responseID;
    }

    public Integer getResponseID() {
        return responseID;
    }

    public void setResponseID(Integer responseID) {
        this.responseID = responseID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Surveyoptions getOptionID() {
        return optionID;
    }

    public void setOptionID(Surveyoptions optionID) {
        this.optionID = optionID;
    }

    public Surveys getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Surveys surveyID) {
        this.surveyID = surveyID;
    }

    public Users getUserID() {
        return userID;
    }

    public void setUserID(Users userID) {
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (responseID != null ? responseID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Surveyresponses)) {
            return false;
        }
        Surveyresponses other = (Surveyresponses) object;
        if ((this.responseID == null && other.responseID != null) || (this.responseID != null && !this.responseID.equals(other.responseID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Surveyresponses[ responseID=" + responseID + " ]";
    }
    
}
