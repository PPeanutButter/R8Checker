package com.peanut.nas.myapplication;

public interface DataCallback<T> {
    default void call(T data){

    }
}
