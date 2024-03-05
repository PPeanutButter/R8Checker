package com.peanut.nas.myapplication;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        //two common way in project
        c(new DataCallback<Https>(){});
        c(new DataCallback<Http>(){});
        WebSocket ws = new Gson().fromJson("{\"host\":\"localhost\"}", WebSocket.class);
        WebSocketSecure wss = JsonUtil.parseJson("{\"host\":\"localhost\"}", WebSocketSecure.class);
        String ftp = new Gson().toJson(new FTP("", ""));
        String ftps = JsonUtil.toJsonString(new FTP("", ""));
    }

    public static <T> void c(DataCallback<T> d){
//        d.call(new Object());
    }


}
