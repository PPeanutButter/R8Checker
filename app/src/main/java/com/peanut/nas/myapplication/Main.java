package com.peanut.nas.myapplication;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        //two common way in project
        Class<WebSocket> wc= WebSocket.class;
        c(new DataCallback<Https>(){});
        c(new DataCallback<Http>(){});
        FTP ffp = new FTP("", "");
        WebSocket ws = new Gson().fromJson("{\"host\":\"localhost\"}", wc);
        WebSocketSecure wss = JsonUtil.parseJson("{\"host\":\"localhost\"}", WebSocketSecure.class);
        String ftp = new Gson().toJson(ffp);
        String ftps = JsonUtil.toJsonString(new FTPS("", ""));
    }

    public static <T> void c(DataCallback<T> d){
//        d.call(new Object());
    }


}
