package com.nms.pojo;

import com.nms.pojo.Posts;
import com.nms.pojo.Users;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-05-13T09:08:29")
@StaticMetamodel(Invitations.class)
public class Invitations_ { 

    public static volatile SetAttribute<Invitations, Users> usersSet;
    public static volatile SingularAttribute<Invitations, String> eventDetails;
    public static volatile SingularAttribute<Invitations, Integer> invitationID;
    public static volatile SingularAttribute<Invitations, Posts> postID;

}