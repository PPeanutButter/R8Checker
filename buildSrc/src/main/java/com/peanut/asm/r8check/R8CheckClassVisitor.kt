package com.peanut.asm.r8check

import com.android.build.api.instrumentation.ClassContext
import org.objectweb.asm.AnnotationVisitor
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.MethodVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.signature.SignatureReader
import org.objectweb.asm.signature.SignatureVisitor

class R8CheckClassVisitor(private val classContext: ClassContext, val configure: Configure, nextClassVisitor: ClassVisitor):ClassVisitor(Opcodes.ASM9, nextClassVisitor) {
    private var methodVisitorHelper = MethodVisitorHelper()
    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        signature?.let {
            if (it.contains("com/peanut/nas/myapplication/DataCallback")){
                //todo filename
                Result.classMap.add(it.getClassT()?:"")
            }
        }
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor {
        if (access and Opcodes.ACC_TRANSIENT > 0) {
            return super.visitField(access, name, descriptor, signature, value)
        }
        return object : FieldVisitor(Opcodes.ASM9, super.visitField(access, name, descriptor, signature, value)) {
            init {
                name?.let {
                    val c = classContext.currentClassData.className
                    if (Result.annotationMap.containsKey(c)){
                        Result.annotationMap[c]!![it] = (false) to (descriptor?:"")
                    }else{
                        Result.annotationMap[c] = mutableMapOf(name to (false to (descriptor?:"")))
                    }
                }
            }
            override fun visitAnnotation(descriptor1: String?, visible: Boolean): AnnotationVisitor {
                name?:return super.visitAnnotation(descriptor1, visible)
                val c = classContext.currentClassData.className
                if (Result.annotationMap.containsKey(c)){
                    Result.annotationMap[c]!![name] = (descriptor1 == "Lcom/google/gson/annotations/SerializedName;") to (descriptor?:"")
                }else{
                    Result.annotationMap[c] = mutableMapOf(name to ((descriptor1 == "Lcom/google/gson/annotations/SerializedName;") to (descriptor?:"")))
                }
                return super.visitAnnotation(descriptor1, visible)
            }
        }
    }

    override fun visitMethod(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        exceptions: Array<out String>?
    ): MethodVisitor {
        return object : MethodVisitor(Opcodes.ASM9,super.visitMethod(access, name, descriptor, signature, exceptions)){
            override fun visitMethodInsn(
                opcode: Int,
                owner: String?,
                name: String?,
                descriptor: String?,
                isInterface: Boolean
            ) {
                val mv = super.visitMethodInsn(opcode, owner, name, descriptor, isInterface)
                methodVisitorHelper.visitMethod(opcode, owner, name, descriptor, isInterface)
                return mv
            }

            override fun visitTypeInsn(opcode: Int, type: String?) {
                super.visitTypeInsn(opcode, type)
                methodVisitorHelper.visitTypeInsn(opcode, type)
            }
        }
    }

    private fun String?.getClassT():String?{
        this?:return null
        var result: String? = null
        val signatureReader = SignatureReader(this)
        val signatureVisitor: SignatureVisitor = object : SignatureVisitor(Opcodes.ASM9) {
            override fun visitClassType(name: String) {
                result = name
            }
        }
        signatureReader.accept(signatureVisitor)
        return result
    }
}