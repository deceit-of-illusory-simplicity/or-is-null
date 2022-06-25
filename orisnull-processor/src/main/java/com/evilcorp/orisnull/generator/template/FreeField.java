package com.evilcorp.orisnull.generator.template;

import com.evilcorp.orisnull.model.OrIsNullClass;
import com.evilcorp.orisnull.model.OrIsNullField;

public class FreeField {
    private final OrIsNullField orIsNullfield;

    public FreeField(OrIsNullField orIsNullField) {
        this.orIsNullfield = orIsNullField;
    }

    public String getName() {
        return orIsNullfield.name();
    }

    public String getType() {
        return orIsNullfield.type();
    }

    public FreeKlass getKlass() {
        return new FreeKlass(orIsNullfield.betterClass());
    }

    public String getGetter() {
        return "get" + getName().toUpperCase().charAt(0) + getName().substring(1);
    }
}
