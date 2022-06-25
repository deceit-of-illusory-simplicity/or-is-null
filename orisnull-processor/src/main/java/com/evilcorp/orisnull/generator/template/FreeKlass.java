package com.evilcorp.orisnull.generator.template;

import com.evilcorp.orisnull.model.OrIsNullClass;

import java.util.List;
import java.util.stream.Collectors;

public class FreeKlass {
    private final OrIsNullClass orIsNullSearchInterface;

    public FreeKlass(OrIsNullClass orIsNullSearchInterface) {
        this.orIsNullSearchInterface = orIsNullSearchInterface;
    }

    public String getPackageName() {
        return orIsNullSearchInterface.packageName();
    }

    public String getFullname() {
        return orIsNullSearchInterface.name();
    }
    public String getShortname() {
        return orIsNullSearchInterface.shortName();
    }

    public List<FreeField> getFields() {
        return orIsNullSearchInterface.fields().stream()
                .map(f -> new FreeField(f))
                .collect(Collectors.toList());
    }
}
