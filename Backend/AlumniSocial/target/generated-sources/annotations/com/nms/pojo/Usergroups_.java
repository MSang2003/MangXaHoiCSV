package com.nms.pojo;

import com.nms.pojo.Groupmembers;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T16:04:27")
@StaticMetamodel(Usergroups.class)
public class Usergroups_ { 

    public static volatile SingularAttribute<Usergroups, Date> createdAt;
    public static volatile SingularAttribute<Usergroups, String> groupName;
    public static volatile SingularAttribute<Usergroups, Integer> groupID;
    public static volatile SingularAttribute<Usergroups, Date> updatedAt;
    public static volatile SetAttribute<Usergroups, Groupmembers> groupmembersSet;

}