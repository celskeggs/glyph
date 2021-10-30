package com.github.celskeggs.glyph

import com.intellij.icons.AllIcons
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.wm.WelcomeTabFactory
import javax.swing.Icon

object GlyphSourceType : LanguageFileType(GlyphLanguage) {
    override fun getName(): String =
        "Glyph Source"

    override fun getDescription(): String =
        "Glyph language file"

    override fun getDefaultExtension(): String =
        "gf"

    override fun getIcon(): Icon =
        AllIcons.FileTypes.Custom
}
