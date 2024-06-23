package com.nms.pojo;

import com.nms.pojo.Posts;
import com.nms.pojo.Surveyoptions;
import com.nms.pojo.Surveyresponses;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T16:04:27")
@StaticMetamodel(Surveys.class)
public class Surveys_ { 

    public static volatile SingularAttribute<Surveys, Date> createdAt;
    public static volatile SingularAttribute<Surveys, Integer> surveyID;
    public static volatile SetAttribute<Surveys, Surveyresponses> surveyresponsesSet;
    public static volatile SingularAttribute<Surveys, String> question;
    public static volatile SingularAttribute<Surveys, Posts> postID;
    public static volatile SetAttribute<Surveys, Surveyoptions> surveyoptionsSet;
    public static volatile SingularAttribute<Surveys, Date> updatedAt;

}