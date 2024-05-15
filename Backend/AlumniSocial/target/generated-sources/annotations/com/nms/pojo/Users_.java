package com.nms.pojo;

import com.nms.pojo.Comments;
import com.nms.pojo.Invitations;
import com.nms.pojo.Posts;
import com.nms.pojo.Reactions;
import com.nms.pojo.Surveyresponses;
import com.nms.pojo.Usergroups;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-05-13T09:08:29")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SetAttribute<Users, Comments> commentsSet;
    public static volatile SetAttribute<Users, Surveyresponses> surveyresponsesSet;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile SetAttribute<Users, Posts> postsSet;
    public static volatile SetAttribute<Users, Usergroups> usergroupsSet;
    public static volatile SingularAttribute<Users, Boolean> isVerified;
    public static volatile SetAttribute<Users, Invitations> invitationsSet;
    public static volatile SingularAttribute<Users, String> avatar;
    public static volatile SingularAttribute<Users, Integer> userID;
    public static volatile SingularAttribute<Users, String> studentID;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> coverImage;
    public static volatile SingularAttribute<Users, Boolean> isLocked;
    public static volatile SetAttribute<Users, Reactions> reactionsSet;
    public static volatile SingularAttribute<Users, String> email;

}