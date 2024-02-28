package com.peanut.asm.r8check

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.objectweb.asm.ClassVisitor

abstract class R8CheckFactory : AsmClassVisitorFactory<InstrumentationParameters.None> {
    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        return R8CheckClassVisitor(classContext, nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData) :Boolean {
        return classData.className.contains("myapplication")
    }

}