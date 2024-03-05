package com.peanut.nas.myapplication;

import com.google.gson.annotations.SerializedName;

public class WebSocketSecure {
    @SerializedName("Host")
    public String host;

    public WebSocketSecure(String host, String protocol) {
        this.host = host;
    }
}
