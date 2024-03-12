package com.peanut.asm.r8check

import org.objectweb.asm.Opcodes

class MethodVisitorHelper {
    //由于gradle会并行编译，导致会有多个class 同时执行 ClassVisitor，故每个类单独保存该变量
    private var shouldFocusNextCHECKCAST = false
    fun visitMethod(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        if ((opcode == Opcodes.INVOKEVIRTUAL && owner == "com/google/gson/Gson" && name == "fromJson")
            || (opcode == Opcodes.INVOKESTATIC && owner == "com/huiwan/base/util/JsonUtil" && name == "parseJson")
        ) {
            shouldFocusNextCHECKCAST = true
        }
    }


    fun visitTypeInsn(opcode: Int, type: String?) {
        if (shouldFocusNextCHECKCAST && opcode == Opcodes.CHECKCAST) {
            Result.classMap.add(type ?: "")
            println("$$$ ,${type ?: ""}")
            shouldFocusNextCHECKCAST = false
        }
    }
}