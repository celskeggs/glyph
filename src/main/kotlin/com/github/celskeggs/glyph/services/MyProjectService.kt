package com.github.celskeggs.glyph.services

import com.intellij.openapi.project.Project
import com.github.celskeggs.glyph.MyBundle

class MyProjectService(project: Project) {

    init {
        println(MyBundle.message("projectService", project.name))
    }
}
