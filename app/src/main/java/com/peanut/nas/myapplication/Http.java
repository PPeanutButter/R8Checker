package com.peanut.nas.myapplication;

import com.google.gson.annotations.SerializedName;

public class Http {
    @SerializedName("Host")
    public String host;

    public String protocol;
    public Http(String host, String protocol) {
        this.host = host;
        this.protocol = protocol;
    }
}
