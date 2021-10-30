package com.github.celskeggs.glyph

import com.intellij.icons.AllIcons
import com.intellij.openapi.module.ModuleType
import javax.swing.Icon

class GlyphModuleType : ModuleType<GlyphModuleBuilder>("GLYPH_MODULE") {
    override fun createModuleBuilder(): GlyphModuleBuilder =
        GlyphModuleBuilder()

    override fun getName(): String =
        "Glyph Module"

    override fun getDescription(): String =
        "Description of the Glyph module"

    override fun getNodeIcon(isOpened: Boolean): Icon =
        AllIcons.Nodes.Module
}
