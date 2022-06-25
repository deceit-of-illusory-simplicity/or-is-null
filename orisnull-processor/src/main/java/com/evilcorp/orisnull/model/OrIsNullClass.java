package com.evilcorp.orisnull.model;

import java.util.List;

public interface OrIsNullClass {
    String name();

    default String shortName() {
        final var dotIdx = name().lastIndexOf('.');
        return name().substring(dotIdx + 1);
    }

    default String packageName() {
        final var dotIdx = name().lastIndexOf('.');
        return name().substring(0, dotIdx);

    }

    List<OrIsNullField> fields();
}
