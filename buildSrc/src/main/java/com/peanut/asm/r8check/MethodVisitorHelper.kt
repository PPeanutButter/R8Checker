package com.peanut.asm.r8check

import org.objectweb.asm.Opcodes

object MethodVisitorHelper {
    fun visitMethod(
        opcode: Int,
        owner: String?,
        name: String?,
        descriptor: String?,
        isInterface: Boolean
    ) {
        if ((opcode == Opcodes.INVOKEVIRTUAL && owner == "com/google/gson/Gson" && name=="fromJson")
            ||(opcode == Opcodes.INVOKESTATIC && owner== "com/peanut/nas/myapplication/JsonUtil" && name =="parseJson")) {
            Result.shouldFocusNextCHECKCAST = true
        }
    }


    fun visitTypeInsn(opcode: Int, type: String?){
        if (Result.shouldFocusNextCHECKCAST && opcode == Opcodes.CHECKCAST) {
            Result.classMap.add(type?:"")
            println("$$$ ,${type?:""}")
            Result.shouldFocusNextCHECKCAST = false
        }
    }
}