package com.nms.pojo;

import com.nms.pojo.Posts;
import com.nms.pojo.ReactionsPK;
import com.nms.pojo.Users;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2024-06-23T16:04:27")
@StaticMetamodel(Reactions.class)
public class Reactions_ { 

    public static volatile SingularAttribute<Reactions, Date> createdAt;
    public static volatile SingularAttribute<Reactions, String> reactionType;
    public static volatile SingularAttribute<Reactions, ReactionsPK> reactionsPK;
    public static volatile SingularAttribute<Reactions, Posts> posts;
    public static volatile SingularAttribute<Reactions, Users> users;

}