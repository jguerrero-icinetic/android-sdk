package com.aplazame.sdk.network.model;

import com.google.gson.annotations.SerializedName;

public class MetaDto {

    @SerializedName("version")
    private final String version;

    public MetaDto(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}
