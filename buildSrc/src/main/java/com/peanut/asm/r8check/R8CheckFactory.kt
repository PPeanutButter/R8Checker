package com.peanut.asm.r8check

import com.android.build.api.instrumentation.AsmClassVisitorFactory
import com.android.build.api.instrumentation.ClassContext
import com.android.build.api.instrumentation.ClassData
import com.android.build.api.instrumentation.InstrumentationParameters
import org.gradle.api.provider.Property
import org.objectweb.asm.ClassVisitor
import javax.inject.Inject

abstract class R8CheckFactory : AsmClassVisitorFactory<InstrumentationParameters.None> {

    override fun createClassVisitor(
        classContext: ClassContext,
        nextClassVisitor: ClassVisitor
    ): ClassVisitor {
        return R8CheckClassVisitor(classContext, Configure(), nextClassVisitor)
    }

    override fun isInstrumentable(classData: ClassData) :Boolean {
        return true
    }
}

open class Configure: InstrumentationParameters{
    var project: String = ""
}