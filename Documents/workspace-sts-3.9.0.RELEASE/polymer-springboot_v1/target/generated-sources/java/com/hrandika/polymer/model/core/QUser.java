package com.hrandika.polymer.model.core;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = 1877629350L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUser user = new QUser("user");

    public final com.hrandika.polymer.model.QBaseModelObject _super = new com.hrandika.polymer.model.QBaseModelObject(this);

    public final BooleanPath activated = createBoolean("activated");

    public final StringPath activationKey = createString("activationKey");

    public final QCompany company;

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    public final StringPath email = createString("email");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final SetPath<Module, QModule> modules = this.<Module, QModule>createSet("modules", Module.class, QModule.class, PathInits.DIRECT2);

    public final StringPath password = createString("password");

    public final DateTimePath<java.util.Date> resetDate = createDateTime("resetDate", java.util.Date.class);

    public final StringPath resetKey = createString("resetKey");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    public final SetPath<SubModule, QSubModule> subModules = this.<SubModule, QSubModule>createSet("subModules", SubModule.class, QSubModule.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    public final StringPath userName = createString("userName");

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QUser(String variable) {
        this(User.class, forVariable(variable), INITS);
    }

    public QUser(Path<? extends User> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QUser(PathMetadata<?> metadata, PathInits inits) {
        this(User.class, metadata, inits);
    }

    public QUser(Class<? extends User> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
    }

}

