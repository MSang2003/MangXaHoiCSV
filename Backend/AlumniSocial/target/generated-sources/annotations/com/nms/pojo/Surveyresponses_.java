package com.nms.pojo;

import com.nms.pojo.Surveys;
import com.nms.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-11T09:11:12")
@StaticMetamodel(Surveyresponses.class)
public class Surveyresponses_ { 

    public static volatile SingularAttribute<Surveyresponses, Date> createdAt;
    public static volatile SingularAttribute<Surveyresponses, Surveys> surveyID;
    public static volatile SingularAttribute<Surveyresponses, String> answer;
    public static volatile SingularAttribute<Surveyresponses, Users> userID;
    public static volatile SingularAttribute<Surveyresponses, Integer> responseID;

}