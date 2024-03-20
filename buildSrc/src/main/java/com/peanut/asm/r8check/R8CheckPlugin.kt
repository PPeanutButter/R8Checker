package com.peanut.asm.r8check

import com.android.build.api.instrumentation.FramesComputationMode
import com.android.build.api.instrumentation.InstrumentationScope
import com.android.build.api.variant.AndroidComponentsExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.com.google.gson.Gson
import java.io.File

class R8CheckPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        println("R8CheckPlugin apply in ${target.name}")
        clearFile(target)
        val androidComponents = target.extensions.getByType(AndroidComponentsExtension::class.java)
        androidComponents.onVariants { variant ->
            variant.instrumentation.transformClassesWith(
                R8CheckFactory::class.java,
                InstrumentationScope.PROJECT
            ) {}
            variant.instrumentation.setAsmFramesComputationMode(
                FramesComputationMode.COMPUTE_FRAMES_FOR_ALL_CLASSES
            )
        }
        addSaveTask(target)
    }


    private fun addSaveTask(target: Project) {
        // Create a task that runs after ':app:transformDebugClassesWithAsm'
        var dependsTransformTaskName = "transformOfficialDebugClassesWithAsm"
        if (target.name != "wepie") {
            dependsTransformTaskName = "transformDebugClassesWithAsm"
        }
        target.tasks.create("save_result") {
            this.group = "build"
            this.description = "My custom task description"
            this.dependsOn(dependsTransformTaskName)
            this.doLast {
                File("${target.rootDir}/annotation_map.json").apply {
                    println("Saved to ${this.absolutePath}")
                }.writeText(Gson().toJson(Result.annotationMap))
                File("${target.rootDir}/suspicious_class_set.json").apply {
                    println("Saved to ${this.absolutePath}")
                }.writeText(Gson().toJson(Result.classMap))
                File("${target.rootDir}/suspicious_class_set_reason.json").apply {
                    println("Saved to ${this.absolutePath}")
                }.writeText(Gson().toJson(Result.classMapReason))
            }
        }
    }

    private fun clearFile(target: Project) {
        val names = arrayListOf(
            "${target.rootDir}/annotation_map.json",
            "${target.rootDir}/suspicious_class_set.json",
            "${target.rootDir}/suspicious_class_set_reason.json",
        )
        names.forEach {
            val file = File(it)
            if (file.exists()) {
                println("delete $it")
                file.delete()
            }
        }
    }

}