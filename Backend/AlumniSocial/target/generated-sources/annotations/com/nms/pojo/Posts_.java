package com.nms.pojo;

import com.nms.pojo.Comments;
import com.nms.pojo.Invitations;
import com.nms.pojo.Reactions;
import com.nms.pojo.Surveys;
import com.nms.pojo.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-05-13T09:08:29")
@StaticMetamodel(Posts.class)
public class Posts_ { 

    public static volatile SetAttribute<Posts, Comments> commentsSet;
    public static volatile SingularAttribute<Posts, String> postType;
    public static volatile SetAttribute<Posts, Invitations> invitationsSet;
    public static volatile SetAttribute<Posts, Surveys> surveysSet;
    public static volatile SingularAttribute<Posts, Boolean> isCommentLocked;
    public static volatile SingularAttribute<Posts, Integer> postID;
    public static volatile SetAttribute<Posts, Reactions> reactionsSet;
    public static volatile SingularAttribute<Posts, Users> userID;
    public static volatile SingularAttribute<Posts, String> content;

}