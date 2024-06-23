package com.nms.pojo;

import com.nms.pojo.Comments;
import com.nms.pojo.Groupmembers;
import com.nms.pojo.Invitationrecipients;
import com.nms.pojo.Posts;
import com.nms.pojo.Reactions;
import com.nms.pojo.Surveyresponses;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T16:04:27")
@StaticMetamodel(Users.class)
public class Users_ { 

    public static volatile SetAttribute<Users, Comments> commentsSet;
    public static volatile SetAttribute<Users, Surveyresponses> surveyresponsesSet;
    public static volatile SingularAttribute<Users, String> role;
    public static volatile SetAttribute<Users, Posts> postsSet;
    public static volatile SingularAttribute<Users, Boolean> isVerified;
    public static volatile SingularAttribute<Users, String> sex;
    public static volatile SingularAttribute<Users, String> avatar;
    public static volatile SingularAttribute<Users, String> userName;
    public static volatile SingularAttribute<Users, Integer> userID;
    public static volatile SingularAttribute<Users, String> studentID;
    public static volatile SingularAttribute<Users, Date> createdAt;
    public static volatile SingularAttribute<Users, String> password;
    public static volatile SingularAttribute<Users, String> phone;
    public static volatile SingularAttribute<Users, Date> doB;
    public static volatile SingularAttribute<Users, String> coverImage;
    public static volatile SingularAttribute<Users, Boolean> isLocked;
    public static volatile SingularAttribute<Users, String> name;
    public static volatile SetAttribute<Users, Invitationrecipients> invitationrecipientsSet;
    public static volatile SetAttribute<Users, Reactions> reactionsSet;
    public static volatile SingularAttribute<Users, String> email;
    public static volatile SingularAttribute<Users, Date> updatedAt;
    public static volatile SetAttribute<Users, Groupmembers> groupmembersSet;

}