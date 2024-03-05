package com.peanut.asm.r8check

object Result {
    val annotationMap = mutableMapOf<String, MutableMap<String, Boolean>>()
    val classMap = mutableSetOf<String>()

    var shouldFocusNextCHECKCAST = false
}