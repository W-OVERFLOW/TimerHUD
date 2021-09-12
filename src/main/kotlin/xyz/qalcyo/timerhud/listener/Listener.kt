package xyz.qalcyo.timerhud.listener

import net.minecraft.client.gui.GuiChat
import net.minecraftforge.client.event.RenderGameOverlayEvent
import net.minecraftforge.event.world.WorldEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent
import xyz.qalcyo.timerhud.TimerHUD.mc
import xyz.qalcyo.timerhud.TimerHUD.timerKeybind
import xyz.qalcyo.timerhud.TimerTask
import xyz.qalcyo.timerhud.config.TimerConfig
import xyz.qalcyo.timerhud.gui.HUDGui
import xyz.qalcyo.timerhud.hud.HUD
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