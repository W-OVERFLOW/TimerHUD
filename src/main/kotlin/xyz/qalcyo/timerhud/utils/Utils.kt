package xyz.qalcyo.timerhud.utils

import net.minecraft.client.gui.FontRenderer
import java.awt.Color
import org.apache.commons.lang3.StringUtils as ApacheStringUtils


/**
 * Adapted from Skytils under AGPLv3
 * https://github.com/Skytils/SkytilsMod/blob/1.x/LICENSE.md
 */
fun String?.startsWithAny(vararg sequences: CharSequence?) = ApacheStringUtils.startsWithAny(this, *sequences)

fun FontRenderer.drawString(text: String, x: Int, y: Int, color: Int, dropShadow: Boolean, chroma: Boolean) {
    if (chroma) {
        var newX = x
        for (c in text.toCharArray()) {
            val col: Int = getChroma(newX, y).rgb
            val charStr = c.toString()
            this.drawString(charStr, newX.toFloat(), y.toFloat(), col, dropShadow)
            newX += this.getStringWidth(charStr)
        }
    } else {
        this.drawString(text, x.toFloat(), y.toFloat(), color, dropShadow)
    }
}

private fun getChroma(x: Int, y: Int): Color {
    val v = 2000.0f
    return Color(
        Color.HSBtoRGB(
            ((System.currentTimeMillis() - x * 10.0 * 1.0 - y * 10.0 * 1.0) % v).toFloat() / v,
            1.0f, 1.0f
        )
    )
}