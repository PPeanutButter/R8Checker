package com.peanut.asm.r8check

import com.android.build.api.instrumentation.ClassContext
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes

class R8CheckClassVisitor(private val classContext: ClassContext, nextClassVisitor: ClassVisitor):ClassVisitor(Opcodes.ASM9, nextClassVisitor) {
    private var result: R8CheckResult? = null

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        return object : MethodVisitor(Opcodes.ASM9, super.visitMethod(access, name, descriptor, signature, exceptions)) {
            override fun visitTypeInsn(opcode: Int, type: String?) {
                if (opcode == Opcodes.NEW){
                    val c = classContext
                    println(c)
                }
                println(type)
                super.visitTypeInsn(opcode, type)
            }
        }
    }

    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor {
        return object : FieldVisitor(Opcodes.ASM9, super.visitField(access, name, descriptor, signature, value)) {
            override fun visitAnnotation(descriptor: String?, visible: Boolean): AnnotationVisitor {
                name?:return super.visitAnnotation(descriptor, visible)
                val c = classContext.currentClassData.className
                if (Result.annotationMap.containsKey(c)){
                    Result.annotationMap[c]!![name] = (descriptor == "Lcom/google/gson/annotations/SerializedName;")
                }else{
                    Result.annotationMap[c] = mutableMapOf(name to (descriptor == "Lcom/google/gson/annotations/SerializedName;"))
                }
                return super.visitAnnotation(descriptor, visible)
            }
        }
    }

//    override fun visitEnd() {
//        super.visitEnd()
//        result?.let {
//            if (it.bool)
//                println(it)
//        }
//    }
//    private fun visitInterface(): String?{
//        for (a in classContext.currentClassData.interfaces){
//            if (a.contains("DataCallback")) {
//                return a
//            }
//        }
//        return null
//    }
//    private fun String?.getClassT():String?{
//        this?:return null
//        var result: String? = null
//        val signatureReader = SignatureReader(this)
//        val signatureVisitor: SignatureVisitor = object : SignatureVisitor(Opcodes.ASM9) {
//            override fun visitClassType(name: String) {
//                result = name
//            }
//        }
//        signatureReader.accept(signatureVisitor)
//        return result
//    }
}