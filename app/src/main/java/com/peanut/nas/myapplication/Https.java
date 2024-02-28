package com.peanut.nas.myapplication;

import com.google.gson.annotations.SerializedName;

public class Https {
    @SerializedName("Host")
    public String host;
    @SerializedName("Protocol")
    public String protocol;

    public Https(String host, String protocol) {
        this.host = host;
        this.protocol = protocol;
    }
}
