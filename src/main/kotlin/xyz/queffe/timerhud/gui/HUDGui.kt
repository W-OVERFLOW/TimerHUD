package xyz.queffe.timerhud.gui

import gg.essential.api.EssentialAPI
import net.minecraft.client.gui.GuiButton
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.renderer.GlStateManager
import xyz.queffe.timerhud.config.TimerConfig
import xyz.queffe.timerhud.hud.HUD
import org.lwjgl.input.Keyboard
import org.lwjgl.opengl.GL11
import java.io.IOException

class HUDGui(private val parent: GuiScreen?) : GuiScreen() {
    private var dragging = false
    private var prevX = 0
    private var prevY = 0

    override fun initGui() {
        buttonList.add(GuiButton(0, width / 2 - 50, height - 20, 100, 20, "Close"))
        super.initGui()
    }

    override fun actionPerformed(button: GuiButton) {
        when (button.id) {
            0 -> EssentialAPI.getGuiUtil().openScreen(parent)
        }
    }

    override fun drawScreen(mouseX: Int, mouseY: Int, partialTicks: Float) {
        updatePos(mouseX, mouseY)
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, 1, 0)
        HUD.render()
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()

        super.drawScreen(mouseX, mouseY, partialTicks)
    }

    @Throws(IOException::class)
    override fun mouseClicked(mouseX: Int, mouseY: Int, mouseButton: Int) {
        super.mouseClicked(mouseX, mouseY, mouseButton)
        prevX = mouseX
        prevY = mouseY
        if (mouseButton == 0) {
            dragging = true
        }
    }

    override fun keyTyped(typedChar: Char, keyCode: Int) {
        super.keyTyped(typedChar, keyCode)
        when (keyCode) {
            Keyboard.KEY_UP -> TimerConfig.y -= 5
            Keyboard.KEY_DOWN -> TimerConfig.y += 5
            Keyboard.KEY_LEFT -> TimerConfig.x -= 5
            Keyboard.KEY_RIGHT -> TimerConfig.x += 5
        }
    }

    private fun updatePos(x: Int, y: Int) {
        if (dragging) {
            TimerConfig.x = prevX
            TimerConfig.y = prevY
        }
        prevX = x
        prevY = y
    }

    override fun mouseReleased(mouseX: Int, mouseY: Int, state: Int) {
        super.mouseReleased(mouseX, mouseY, state)
        dragging = false
    }

    override fun doesGuiPauseGame(): Boolean {
        return false
    }

    override fun onGuiClosed() {
        TimerConfig.markDirty()
        TimerConfig.writeData()
        super.onGuiClosed()
    }
}