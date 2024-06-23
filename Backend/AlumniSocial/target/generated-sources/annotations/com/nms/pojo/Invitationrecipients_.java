package com.nms.pojo;

import com.nms.pojo.InvitationrecipientsPK;
import com.nms.pojo.Invitations;
import com.nms.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T16:04:27")
@StaticMetamodel(Invitationrecipients.class)
public class Invitationrecipients_ { 

    public static volatile SingularAttribute<Invitationrecipients, Date> createdAt;
    public static volatile SingularAttribute<Invitationrecipients, Invitations> invitations;
    public static volatile SingularAttribute<Invitationrecipients, InvitationrecipientsPK> invitationrecipientsPK;
    public static volatile SingularAttribute<Invitationrecipients, Users> users;

}