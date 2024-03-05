package com.peanut.nas.myapplication;

import com.google.gson.annotations.SerializedName;

public class WebSocket {
    @SerializedName("Host")
    public String host;

    public WebSocket(String host, String protocol) {
        this.host = host;
    }
}
