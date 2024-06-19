/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
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
@Table(name = "surveys")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surveys.findAll", query = "SELECT s FROM Surveys s"),
    @NamedQuery(name = "Surveys.findBySurveyID", query = "SELECT s FROM Surveys s WHERE s.surveyID = :surveyID"),
    @NamedQuery(name = "Surveys.findByCreatedAt", query = "SELECT s FROM Surveys s WHERE s.createdAt = :createdAt"),
    @NamedQuery(name = "Surveys.findByUpdatedAt", query = "SELECT s FROM Surveys s WHERE s.updatedAt = :updatedAt")})
public class Surveys implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SurveyID")
    private Integer surveyID;
    @Lob
    @Size(max = 65535)
    @Column(name = "Question")
    private String question;
    @Column(name = "CreatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "UpdatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @OneToMany(mappedBy = "surveyID")
    private Set<Surveyoptions> surveyoptionsSet;
    @JoinColumn(name = "PostID", referencedColumnName = "PostID")
    @ManyToOne
    private Posts postID;
    @OneToMany(mappedBy = "surveyID")
    private Set<Surveyresponses> surveyresponsesSet;

    public Surveys() {
    }

    public Surveys(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public Integer getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Integer surveyID) {
        this.surveyID = surveyID;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
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
    public Set<Surveyoptions> getSurveyoptionsSet() {
        return surveyoptionsSet;
    }

    public void setSurveyoptionsSet(Set<Surveyoptions> surveyoptionsSet) {
        this.surveyoptionsSet = surveyoptionsSet;
    }

    public Posts getPostID() {
        return postID;
    }

    public void setPostID(Posts postID) {
        this.postID = postID;
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
        hash += (surveyID != null ? surveyID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Surveys)) {
            return false;
        }
        Surveys other = (Surveys) object;
        if ((this.surveyID == null && other.surveyID != null) || (this.surveyID != null && !this.surveyID.equals(other.surveyID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Surveys[ surveyID=" + surveyID + " ]";
    }
    
}
