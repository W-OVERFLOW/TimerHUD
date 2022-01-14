package net.wyvest.timerhud.gui

import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.universal.UMatrixStack
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.renderer.GlStateManager
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import net.wyvest.timerhud.config.TimerConfig
import net.wyvest.timerhud.hud.HUD

class HUDGui : WindowScreen(version = ElementaVersion.V1, restoreCurrentGuiOnClose = true, enableRepeatKeys = true, drawDefaultBackground = false) {

    override fun initScreen(width: Int, height: Int) {
        super.initScreen(width, height)
        buttonList.add(GuiButton(0, width / 2 - 50, height - 20, 100, 20, "Close"))
        window.onMouseDrag { mouseX, mouseY, mouseButton ->
            if (mouseButton == 0) {
                TimerConfig.x = mouseX.toInt()
                TimerConfig.y = mouseY.toInt()
            }
        }.onKeyType { _, keyCode ->
            when (keyCode) {
                Keyboard.KEY_UP -> TimerConfig.y -= 5
                Keyboard.KEY_DOWN -> TimerConfig.y += 5
                Keyboard.KEY_LEFT -> TimerConfig.x -= 5
                Keyboard.KEY_RIGHT -> TimerConfig.x += 5
            }
        }
    }

    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            0 -> restorePreviousScreen()
        }
    }

    override fun onDrawScreen(matrixStack: UMatrixStack, mouseX: Int, mouseY: Int, partialTicks: Float) {
        super.onDrawScreen(matrixStack, mouseX, mouseY, partialTicks)
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0)
        HUD.render()
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
    }

    override fun doesGuiPauseGame(): Boolean {
        return false
    }

    override fun onScreenClose() {
        super.onScreenClose()
        TimerConfig.markDirty()
        TimerConfig.writeData()
    }
}