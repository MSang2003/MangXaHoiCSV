package com.nms.pojo;

import com.nms.pojo.Invitationrecipients;
import com.nms.pojo.Posts;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-11T09:11:12")
@StaticMetamodel(Invitations.class)
public class Invitations_ { 

    public static volatile SingularAttribute<Invitations, Date> createdAt;
    public static volatile SingularAttribute<Invitations, String> eventDetails;
    public static volatile SetAttribute<Invitations, Invitationrecipients> invitationrecipientsSet;
    public static volatile SingularAttribute<Invitations, Integer> invitationID;
    public static volatile SingularAttribute<Invitations, Posts> postID;
    public static volatile SingularAttribute<Invitations, Date> updatedAt;

}