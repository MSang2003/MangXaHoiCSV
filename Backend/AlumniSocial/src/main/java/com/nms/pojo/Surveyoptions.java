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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admin
 */
@Entity
@Table(name = "surveyoptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Surveyoptions.findAll", query = "SELECT s FROM Surveyoptions s"),
    @NamedQuery(name = "Surveyoptions.findByOptionID", query = "SELECT s FROM Surveyoptions s WHERE s.optionID = :optionID")})
public class Surveyoptions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OptionID")
    private Integer optionID;
    @Lob
    @Size(max = 65535)
    @Column(name = "OptionText")
    private String optionText;
    @JoinColumn(name = "SurveyID", referencedColumnName = "SurveyID")
    @ManyToOne
    private Surveys surveyID;
    @OneToMany(mappedBy = "optionID")
    private Set<Surveyresponses> surveyresponsesSet;

    public Surveyoptions() {
    }

    public Surveyoptions(Integer optionID) {
        this.optionID = optionID;
    }

    public Integer getOptionID() {
        return optionID;
    }

    public void setOptionID(Integer optionID) {
        this.optionID = optionID;
    }

    public String getOptionText() {
        return optionText;
    }

    public void setOptionText(String optionText) {
        this.optionText = optionText;
    }

    public Surveys getSurveyID() {
        return surveyID;
    }

    public void setSurveyID(Surveys surveyID) {
        this.surveyID = surveyID;
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
        hash += (optionID != null ? optionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Surveyoptions)) {
            return false;
        }
        Surveyoptions other = (Surveyoptions) object;
        if ((this.optionID == null && other.optionID != null) || (this.optionID != null && !this.optionID.equals(other.optionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nms.pojo.Surveyoptions[ optionID=" + optionID + " ]";
    }
    
}
