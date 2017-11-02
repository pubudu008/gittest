package com.hrandika.polymer.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QBaseModelObject is a Querydsl query type for BaseModelObject
 */
@Generated("com.mysema.query.codegen.SupertypeSerializer")
public class QBaseModelObject extends EntityPathBase<BaseModelObject> {

    private static final long serialVersionUID = 629940195L;

    public static final QBaseModelObject baseModelObject = new QBaseModelObject("baseModelObject");

    public final DateTimePath<java.util.Date> createdDate = createDateTime("createdDate", java.util.Date.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.util.Date> updatedDate = createDateTime("updatedDate", java.util.Date.class);

    public final NumberPath<Integer> version = createNumber("version", Integer.class);

    public QBaseModelObject(String variable) {
        super(BaseModelObject.class, forVariable(variable));
    }

    public QBaseModelObject(Path<? extends BaseModelObject> path) {
        super(path.getType(), path.getMetadata());
    }

    public QBaseModelObject(PathMetadata<?> metadata) {
        super(BaseModelObject.class, metadata);
    }

}

