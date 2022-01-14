package net.wyvest.timerhud.hud

import net.minecraft.client.gui.Gui
import net.wyvest.timerhud.TimerHUD.mc
import net.wyvest.timerhud.TimerTask
import net.wyvest.timerhud.config.TimerConfig
import net.wyvest.timerhud.utils.drawString
import kotlin.math.max

object HUD {

    fun render() {
        val text = if (TimerTask.running) {
            "${TimerTask.secondsPassed}s"
        } else {
            "0s"
        }
        val height = mc.fontRendererObj.FONT_HEIGHT
        val width = max(mc.fontRendererObj.getStringWidth(text), 10)
        if (TimerConfig.displayBackground) {
            if (TimerConfig.backgroundColor.alpha != 0) {
                Gui.drawRect(
                    TimerConfig.x - TimerConfig.padding,
                    TimerConfig.y - TimerConfig.padding,
                    width + TimerConfig.x + TimerConfig.padding,
                    height + TimerConfig.y + TimerConfig.padding,
                    TimerConfig.backgroundColor.rgb
                )
            }
        }
        mc.fontRendererObj.drawString(
            text,
            TimerConfig.x,
            TimerConfig.y,
            TimerConfig.color.rgb,
            TimerConfig.renderShadow,
            TimerConfig.chroma
        )
    }

}