package com.nms.pojo;

import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-05-13T09:08:29")
@StaticMetamodel(Comments.class)
public class Comments_ { 

    public static volatile SingularAttribute<Comments, Integer> commentID;
    public static volatile SingularAttribute<Comments, Posts> postID;
    public static volatile SingularAttribute<Comments, Users> userID;
    public static volatile SingularAttribute<Comments, String> content;

}