package com.peanut.asm.r8check

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.com.google.gson.Gson
import java.io.File

class SaveResultPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        println(target.name)
        // Create a task that runs after ':app:transformDebugClassesWithAsm'
        target.tasks.create("save_result") {
            this.group = "build"
            this.description = "My custom task description"
            this.dependsOn("transformDebugClassesWithAsm")
            this.doLast {
                File("/Users/panrunqiu/Github/MyApplication2/annotation_map.json").writeText(Gson().toJson(Result.annotationMap))
                println("Saved")
            }
        }
    }
}