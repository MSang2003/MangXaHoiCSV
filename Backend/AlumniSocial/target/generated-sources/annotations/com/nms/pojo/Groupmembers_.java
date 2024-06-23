package com.nms.pojo;

import com.nms.pojo.GroupmembersPK;
import com.nms.pojo.Usergroups;
import com.nms.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T16:04:27")
@StaticMetamodel(Groupmembers.class)
public class Groupmembers_ { 

    public static volatile SingularAttribute<Groupmembers, Date> createdAt;
    public static volatile SingularAttribute<Groupmembers, GroupmembersPK> groupmembersPK;
    public static volatile SingularAttribute<Groupmembers, Usergroups> usergroups;
    public static volatile SingularAttribute<Groupmembers, Users> users;

}