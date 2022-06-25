package com.evilcorp.orisnull.model;

import com.evilcorp.orisnull.annotation.OrIsNullQuery;

import javax.lang.model.element.Element;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;

public class AnnotatedOrIsNullSearchMethod implements OrIsNullSearchMethod {
    private final Element queryMethod;
    private final Types typeUtils;

    public AnnotatedOrIsNullSearchMethod(Element queryMethod, final Types typeUtils) {
        this.queryMethod = queryMethod;
        this.typeUtils = typeUtils;
    }

    public String name() {
        return queryMethod.getSimpleName().toString();
    }

    public OrIsNullClass entity() {
        return new AnnotatedEntity(queryMethod);
    }

    public OrIsNullClass filter() {
        final var queryMethod = (ExecutableType) this.queryMethod.asType();
        final TypeMirror queryMethodParameter = queryMethod.getParameterTypes().iterator().next();
        return new AnnotatedOrIsNullClass(typeUtils.asElement(queryMethodParameter));
    }

    public String query() {
        return queryMethod.getAnnotation(OrIsNullQuery.class).value();
    }
}
