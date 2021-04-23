package net.wyvest.timer.listener;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.wyvest.timer.TimerHUD;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.overlay.HUD;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

/**
 * @author Wyvest
 */

public class TimerListener {
    public int ticks = 0;
    public int secondsPassed;

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (!TimerConfig.turnOnTimerWhenWorldEnter) return;
        TimerHUD.getInstance().setRunning(true);
        ticks = 0;
        secondsPassed = 0;
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        if (!TimerConfig.resetWhenWorldExit) return;
        TimerHUD.getInstance().setRunning(false);
        ticks = 0;
        secondsPassed = 0;
    }

    @SubscribeEvent
    protected void onGameOverlayRendered(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            if (TimerConfig.modToggled && (Minecraft.getMinecraft().currentScreen == null || TimerConfig.showinGui) && Minecraft.getMinecraft().thePlayer != null) {
                HUD.drawTimer(secondsPassed);
            }
        }
    }


    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        int code = TimerHUD.getInstance().keyTimer.getKeyCode();
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == code) {
            TimerHUD.getInstance().toggleRunning();
            ticks = 0;
            secondsPassed = 0;
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        int code = TimerHUD.getInstance().keyTimer.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == code + 100) {
            TimerHUD.getInstance().toggleRunning();
            ticks = 0;
            secondsPassed = 0;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if (TimerHUD.getInstance().isRunning()) {
                ticks++;
                if (ticks == 20) {
                    secondsPassed++;
                    ticks = 0;
                }
            }
        }
    }


}