package com.peanut.asm.r8check

data class R8CheckResult(
    var source: String? = "",
    var classForName: String? = "",
    var T: String? = "",
    var bool: Boolean = false,
)