package net.wyvest.timerhud.listener

import net.minecraft.client.gui.GuiChat
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import net.wyvest.timerhud.TimerHUD.mc
import net.wyvest.timerhud.TimerHUD.timerKeybind
import net.wyvest.timerhud.TimerTask
import net.wyvest.timerhud.config.TimerConfig
import net.wyvest.timerhud.gui.HUDGui
import net.wyvest.timerhud.hud.HUD
import org.lwjgl.input.Keyboard
import org.lwjgl.input.Mouse


object Listener {

    @SubscribeEvent
    fun onWorldEnter(e: WorldEvent.Load) {
        if (TimerConfig.turnTimerWorldEnter) {
            TimerTask.setTimer(true)
        }
    }

    @SubscribeEvent
    fun onWorldLeave(e: WorldEvent.Unload) {
        if (TimerConfig.turnTimerWorldLeave) {
            TimerTask.setTimer(false)
        }
    }

    @SubscribeEvent
    fun onKeyInput(event: InputEvent.KeyInputEvent) {
        if (mc.currentScreen != null) return
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == timerKeybind.keyCode) {
            TimerTask.toggleTimer()
        }
    }

    @SubscribeEvent
    fun onMouseInput(event: InputEvent.MouseInputEvent) {
        if (mc.currentScreen != null) return
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == timerKeybind.keyCode + 100) {
            TimerTask.toggleTimer()
        }
    }

    @SubscribeEvent
    fun onGameOverlayRendered(event: RenderGameOverlayEvent.Post) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            if (mc.currentScreen !is HUDGui && mc.thePlayer != null) {
                if (!TimerTask.running && TimerConfig.renderNothing) return
                if (!TimerConfig.showInChat && mc.currentScreen is GuiChat) return
                if (!TimerConfig.showInDebug && mc.gameSettings.showDebugInfo) return
                if (!TimerConfig.showInGuis && mc.currentScreen != null) return
                HUD.render()
            }
        }
    }
}