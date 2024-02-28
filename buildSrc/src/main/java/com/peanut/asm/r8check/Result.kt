package com.peanut.asm.r8check

object Result {
    val annotationMap = mutableMapOf<String, MutableMap<String, Boolean>>()
//    init {
//        Runtime.getRuntime().addShutdownHook(Thread {
//            annotationMap.forEach { (key, value) ->
//                println(key)
//                value.forEach { (k, v) ->
//                    println("└──$k:$v")
//                }
//            }
//        })
//    }
}