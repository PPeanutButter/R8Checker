package com.peanut.asm.r8check

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArraySet

object Result {
    val annotationMap = ConcurrentHashMap<String, MutableMap<String, Pair<Boolean, String>>>()
    val classMap = CopyOnWriteArraySet<String>()
    val classMapReason = ConcurrentHashMap<String, String>()
}