package com.peanut.nas.myapplication;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        //two common way in project
        c(new DataCallback<Https>(){});
        WebSocket ws = new Gson().fromJson("{\"host\":\"localhost\"}", WebSocket.class);
    }

    public static void c(DataCallback<Https> d){
//        d.call(new Object());
    }
}
