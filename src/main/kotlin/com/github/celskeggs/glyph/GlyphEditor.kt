package com.github.celskeggs.glyph

import com.intellij.diff.util.FileEditorBase
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.JComponent

class GlyphEditor(file: VirtualFile) : FileEditorBase() {
    val localFile = file
    private val myComponent = GlyphEditorComponent(file)

    override fun getComponent(): JComponent =
        myComponent

    override fun getName(): String =
        "Glyph"

    override fun getPreferredFocusedComponent(): JComponent? =
        null // TODO: better answer?

    override fun getFile(): VirtualFile =
        localFile
}
