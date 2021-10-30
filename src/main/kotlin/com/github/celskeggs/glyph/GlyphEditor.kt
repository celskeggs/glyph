package com.github.celskeggs.glyph

import com.intellij.diff.util.FileEditorBase
import com.intellij.openapi.vfs.VirtualFile
import javax.swing.JComponent
import javax.swing.JLabel

class GlyphEditor(private val sourceFile: VirtualFile) : FileEditorBase() {
    private val renderFile = sourceFile.findFileByRelativePath("../" + deriveName(sourceFile.name))
    private val rendered = renderFile?.inputStream?.use { RenderedGlyphs(it) }
    private val myComponent: JComponent = rendered?.renderAll() ?: JLabel("Cannot load rendered output")

    companion object {
        fun deriveName(name: String): String {
            val ix = name.lastIndexOf('.')
            return if (ix == -1) {
                "$name.gry"
            } else {
                name.substring(0, ix) + ".gry"
            }
        }
    }

    override fun getComponent(): JComponent =
        myComponent

    override fun getName(): String =
        "Glyph"

    override fun getPreferredFocusedComponent(): JComponent? =
        null // TODO: better answer?

    override fun getFile(): VirtualFile =
        sourceFile
}
