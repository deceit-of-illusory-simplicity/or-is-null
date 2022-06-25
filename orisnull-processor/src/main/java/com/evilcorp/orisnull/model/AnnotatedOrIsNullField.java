package com.evilcorp.orisnull.model;

import javax.lang.model.element.Element;

public class AnnotatedOrIsNullField implements OrIsNullField {
    private final Element element;

    public AnnotatedOrIsNullField(Element element) {
        this.element = element;
    }

    @Override
    public String name() {
        return element.getSimpleName().toString();
    }

    @Override
    public String type() {
        return element.asType().toString();
    }

    public OrIsNullClass betterClass() {
        if (element.asType().getKind().isPrimitive()) {
            return null;
        }
        return new AnnotatedOrIsNullClass(element);
    }

    public OrIsNullClass getKlassType() {
        return betterClass();
    }
}
