package com.aplazame.sdk.network.model.builder;

import com.aplazame.sdk.network.model.MetaDto;

public class MetaDtoBuilder {

    private String version;

    public MetaDtoBuilder() {}

    public MetaDto create() {
        return new MetaDto(version);
    }

    public MetaDtoBuilder withVersion(String version) {
        this.version = version;
        return this;
    }
}
