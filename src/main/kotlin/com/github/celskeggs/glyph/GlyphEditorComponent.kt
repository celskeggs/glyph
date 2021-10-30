package com.github.celskeggs.glyph

import com.amihaiemil.eoyaml.Yaml
import com.amihaiemil.eoyaml.exceptions.YamlIndentationException
import com.amihaiemil.eoyaml.exceptions.YamlReadingException
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.rd.util.string.print
import java.awt.BorderLayout
import java.io.IOException
import java.nio.charset.CodingErrorAction
import javax.swing.JLabel
import javax.swing.JPanel

class GlyphEditorComponent(file: VirtualFile) : JPanel() {
    init {
        layout = BorderLayout()
        try {
            val sequence = file.inputStream.use { Yaml.createYamlInput(it).readYamlSequence() }
            sequence.forEach {
                add(JLabel(it.asScalar().value()))
            }
        } catch (e : IOException) {
            e.printStackTrace()
        } catch (e : YamlIndentationException) {
            e.printStackTrace()
        } catch (e : YamlReadingException) {
            e.printStackTrace()
        } catch (e : ClassCastException) {
            e.printStackTrace()
        }
    }
}
