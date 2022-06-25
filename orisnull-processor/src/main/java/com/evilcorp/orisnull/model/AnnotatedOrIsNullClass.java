package com.evilcorp.orisnull.model;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.stream.Collectors;

public class AnnotatedOrIsNullClass implements OrIsNullClass {
    private final Element element;

    public AnnotatedOrIsNullClass(Element element) {
        this.element = element;
    }

    @Override
    public String name() {
        return element.asType().toString();
    }

    @Override
    public List<OrIsNullField> fields() {
        return element.getEnclosedElements().stream()
                .filter(e -> e.getKind().isField())
                .map(AnnotatedOrIsNullField::new)
                .collect(Collectors.toList());
    }
}
