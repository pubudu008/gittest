package com.hrandika.polymer.model.core;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QModule is a Querydsl query type for Module
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QModule extends EntityPathBase<Module> {

    private static final long serialVersionUID = 282790279L;

    public static final QModule module = new QModule("module");

    public final com.hrandika.polymer.model.QBaseModelObject _super = new com.hrandika.polymer.model.QBaseModelObject(this);

    //inherited
    public final DateTimePath<java.util.Date> createdDate = _super.createdDate;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath name = createString("name");

    //inherited
    public final DateTimePath<java.util.Date> updatedDate = _super.updatedDate;

    //inherited
    public final NumberPath<Integer> version = _super.version;

    public QModule(String variable) {
        super(Module.class, forVariable(variable));
    }

    public QModule(Path<? extends Module> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModule(PathMetadata<?> metadata) {
        super(Module.class, metadata);
    }

}

