package com.peanut.nas.myapplication;

public class JsonUtil {
    public static <T> T parseJson(String json, Class<T> classOfT) {
        return classOfT.cast(new Object());
    }
}
