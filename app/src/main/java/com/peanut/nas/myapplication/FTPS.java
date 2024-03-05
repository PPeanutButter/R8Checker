package com.peanut.nas.myapplication;

import com.google.gson.annotations.SerializedName;

public class FTPS {
    @SerializedName("Host")
    public String host;

    public FTPS(String host, String protocol) {
        this.host = host;
    }
}
