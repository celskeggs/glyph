package com.github.celskeggs.glyph

import com.intellij.ide.util.projectWizard.ModuleBuilder
import com.intellij.openapi.module.ModuleType
import com.intellij.openapi.module.ModuleTypeManager

class GlyphModuleBuilder : ModuleBuilder() {
    override fun getModuleType(): ModuleType<*> =
        ModuleTypeManager.getInstance().findByID("GLYPH_MODULE");
}
