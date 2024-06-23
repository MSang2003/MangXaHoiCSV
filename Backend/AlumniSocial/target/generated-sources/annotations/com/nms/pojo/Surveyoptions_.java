package com.nms.pojo;

import com.nms.pojo.Surveyresponses;
import com.nms.pojo.Surveys;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T16:04:27")
@StaticMetamodel(Surveyoptions.class)
public class Surveyoptions_ { 

    public static volatile SingularAttribute<Surveyoptions, Surveys> surveyID;
    public static volatile SetAttribute<Surveyoptions, Surveyresponses> surveyresponsesSet;
    public static volatile SingularAttribute<Surveyoptions, Integer> optionID;
    public static volatile SingularAttribute<Surveyoptions, String> optionText;

}