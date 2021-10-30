package com.github.celskeggs.glyph

import com.intellij.ui.components.JBScrollPane
import org.yaml.snakeyaml.Yaml
import java.io.InputStream
import javax.swing.Box
import javax.swing.JComponent
import javax.swing.JScrollPane

class RenderedGlyphs(input: InputStream) {
    private val renderOps: List<RenderOp> = try {
        val data: ArrayList<Map<String, Any>> = Yaml().load(input)
        RenderOp.parseAll(data)
    } catch (e : Exception) {
        listOf(RenderSetColorOp(255, 0, 0), RenderTextOp(e.stackTraceToString()))
    }

    fun renderAll(): JComponent {
        // val scroll = JBScrollPane()
        // scroll.alignmentX = JComponent.LEFT_ALIGNMENT
        val context = RenderContext()
        for (op in renderOps) {
            op.render(context)
        }
        context.finish()
        // scroll.add(context)
        return JBScrollPane(context)
    }
}
