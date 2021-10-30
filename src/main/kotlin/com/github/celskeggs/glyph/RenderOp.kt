package com.github.celskeggs.glyph

import java.awt.Color
import java.awt.Dimension
import javax.swing.*

interface RenderOp {
    fun render(context: RenderContext)

    companion object {
        fun parseAll(data: ArrayList<Map<String, Any>>): List<RenderOp> =
            data.map { parse(it) }

        fun parse(data: Map<String, Any>): RenderOp =
            when (data["op"]) {
                "glyph_bubble" -> RenderGlyphBubbleOp(data["glyph"] as String, parseAll(data["content"] as ArrayList<Map<String, Any>>))
                "render_text" -> RenderTextOp(data["text"] as String)
                "set_color" -> RenderSetColorOp(data["red"] as Int, data["green"] as Int, data["blue"] as Int)
                else -> throw RuntimeException("Unrecognized render type: " + data["op"])
            }
    }
}

class RenderContext : JPanel() {
    var textColor: Color = Color.WHITE
    private var hadPrevious = false
    init {
        this.layout = BoxLayout(this, BoxLayout.PAGE_AXIS)
        this.alignmentX = JComponent.LEFT_ALIGNMENT
        this.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)
    }

    fun finish() {
        // this.maximumSize = Dimension(Integer.MAX_VALUE, this.preferredSize.height)
        this.maximumSize = this.preferredSize
    }

    fun addChild(inner: JComponent, name: String? = null) {
        if (hadPrevious) {
            add(Box.createRigidArea(Dimension(0, 10)))
        } else {
            hadPrevious = true
        }
        if (name != null) {
            val label = JLabel("$name")
            label.font = label.font.deriveFont(label.font.size2D * 0.8f)
            add(label)
        }
        add(inner)
    }
}

class RenderGlyphBubbleOp(private val glyph: String, private val content: List<RenderOp>) : RenderOp {
    override fun render(context: RenderContext) {
        val inner = RenderContext()
        inner.border = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.WHITE), inner.border)
        for (c in this.content) {
            c.render(inner)
        }
        inner.finish()
        context.addChild(inner, glyph)
    }
}

class RenderTextOp(private val text: String) : RenderOp {
    override fun render(context: RenderContext) {
        val label = JTextArea(text)
        label.foreground = context.textColor

        label.maximumSize = label.preferredSize
        context.addChild(label)
    }
}

class RenderSetColorOp(private val red: Int, private val green: Int, private val blue: Int) : RenderOp {
    override fun render(context: RenderContext) {
        context.textColor = Color(red, green, blue)
    }
}
