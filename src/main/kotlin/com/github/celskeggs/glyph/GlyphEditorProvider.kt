package com.github.celskeggs.glyph

import com.intellij.openapi.fileEditor.FileEditor
import com.intellij.openapi.fileEditor.FileEditorPolicy
import com.intellij.openapi.fileEditor.FileEditorProvider
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

class GlyphEditorProvider : FileEditorProvider {
    override fun accept(project: Project, file: VirtualFile): Boolean =
        file.fileType == GlyphSourceType

    override fun createEditor(project: Project, file: VirtualFile): FileEditor =
        GlyphEditor(file)

    override fun getEditorTypeId(): String =
        "GlyphEditor"

    override fun getPolicy(): FileEditorPolicy =
        FileEditorPolicy.NONE
}
