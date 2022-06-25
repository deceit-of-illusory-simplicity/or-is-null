package com.evilcorp.orisnull.model;

import javax.lang.model.element.Element;
import javax.lang.model.type.ExecutableType;
import java.util.Collections;
import java.util.List;

public class AnnotatedEntity implements OrIsNullClass {
    private final Element queryMethod;

    public AnnotatedEntity(Element queryMethod) {
        this.queryMethod = queryMethod;
    }

    @Override
    public String name() {
        final var method = (ExecutableType) queryMethod.asType();
        final var returnTypeName = method.getReturnType().toString();
        return returnTypeName.replaceAll("^.*<(.*)>", "$1");
    }

    @Override
    public List<OrIsNullField> fields() {
        return Collections.emptyList();
    }
}
