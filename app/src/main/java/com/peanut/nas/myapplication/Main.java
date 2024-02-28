package com.peanut.nas.myapplication;

public class Main {
    public static void main(String[] args) {
        DataCallback<Https> one = new DataCallback<Https>() {
            @Override
            public void call(Https data) {}
        };
        System.out.println(one);
    }
}
