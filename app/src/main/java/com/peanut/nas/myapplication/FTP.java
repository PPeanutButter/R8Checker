package com.peanut.nas.myapplication;

import com.google.gson.annotations.SerializedName;

public class FTP {
    @SerializedName("Host")
    public String host;

    public FTP(String host, String protocol) {
        this.host = host;
    }
}
