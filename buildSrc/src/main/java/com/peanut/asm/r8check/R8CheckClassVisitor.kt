package com.peanut.asm.r8check

import com.android.build.api.instrumentation.ClassContext
import org.objectweb.asm.ClassVisitor
import org.objectweb.asm.FieldVisitor
import org.objectweb.asm.Opcodes
import org.objectweb.asm.signature.SignatureReader
import org.objectweb.asm.signature.SignatureVisitor

class R8CheckClassVisitor(private val classContext: ClassContext, nextClassVisitor: ClassVisitor):ClassVisitor(Opcodes.ASM9, nextClassVisitor) {
    private var result: R8CheckResult? = null

    override fun visit(
        version: Int,
        access: Int,
        name: String?,
        signature: String?,
        superName: String?,
        interfaces: Array<out String>?
    ) {
        println(name)
        //check if need more visits
//        val t = signature.getClassT()
//        t?.let {
//            result = R8CheckResult()
//            result?.classForName = name
//            result?.T = t
//            val i = visitInterface()
//            if (i != null){
//                result?.bool = true
//            }
//        }
        super.visit(version, access, name, signature, superName, interfaces)
    }

    override fun visitSource(source: String?, debug: String?) {
        result?.let {
            it.source = source
        }
        super.visitSource(source, debug)
    }


    override fun visitField(
        access: Int,
        name: String?,
        descriptor: String?,
        signature: String?,
        value: Any?
    ): FieldVisitor {
        val vf = super.visitField(access, name, descriptor, signature, value)
        println(vf)
        vf.visitAnnotation(descriptor, true)
        return vf
    }
    override fun visitEnd() {
        super.visitEnd()
        result?.let {
            if (it.bool)
                println(it)
        }
    }
    private fun visitInterface(): String?{
        for (a in classContext.currentClassData.interfaces){
            if (a.contains("DataCallback")) {
                return a
            }
        }
        return null
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