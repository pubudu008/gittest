package com.hrandika.polymer.model.core;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QSubModule is a Querydsl query type for SubModule
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QSubModule extends EntityPathBase<SubModule> {

    private static final long serialVersionUID = -1130847279L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QSubModule subModule = new QSubModule("subModule");

    public final com.hrandika.polymer.model.QBaseModelObject _super = new com.hrandika.polymer.model.QBaseModelObject(this);

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QModule module;

    public final StringPath name = createString("name");

    public final SetPath<Role, QRole> roles = this.<Role, QRole>createSet("roles", Role.class, QRole.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public final BooleanPath visible = createBoolean("visible");

    public QSubModule(String variable) {
        this(SubModule.class, forVariable(variable), INITS);
    }

    public QSubModule(Path<? extends SubModule> path) {
        this(path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSubModule(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QSubModule(PathMetadata<?> metadata, PathInits inits) {
        this(SubModule.class, metadata, inits);
    }

    public QSubModule(Class<? extends SubModule> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.module = inits.isInitialized("module") ? new QModule(forProperty("module")) : null;
    }

}

