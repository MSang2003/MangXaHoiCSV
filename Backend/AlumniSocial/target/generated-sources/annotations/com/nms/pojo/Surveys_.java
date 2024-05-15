package com.nms.pojo;

import com.nms.pojo.Posts;
import com.nms.pojo.Surveyresponses;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-05-13T09:08:29")
@StaticMetamodel(Surveys.class)
public class Surveys_ { 

    public static volatile SingularAttribute<Surveys, Integer> surveyID;
    public static volatile SetAttribute<Surveys, Surveyresponses> surveyresponsesSet;
    public static volatile SingularAttribute<Surveys, String> question;
    public static volatile SingularAttribute<Surveys, Posts> postID;

}