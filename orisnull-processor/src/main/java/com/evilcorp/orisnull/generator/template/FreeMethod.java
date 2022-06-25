package com.evilcorp.orisnull.generator.template;

import com.evilcorp.orisnull.model.OrIsNullSearchMethod;

public class FreeMethod {
    private final OrIsNullSearchMethod method;
    public FreeMethod(OrIsNullSearchMethod method) {
        this.method = method;
    }

    public FreeKlass getFilter() {
        return new FreeKlass(method.filter());
    }

    public FreeKlass getEntity() {
        return new FreeKlass(method.entity());
    }

    public String getName() {
        return method.name();
    }

    public String getQuery() {
        return method.query();
    }
}
