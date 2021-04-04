package net.wyvest.timer.listener;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.wyvest.timer.TimerMod;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.overlay.UI;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;


public class TimerListener {
    public int ticks = 0;
    public int secondsPassed;

    @SubscribeEvent
    public void worldSwap(WorldEvent.Unload event) {
        TimerMod.getInstance().setRunning(false);
        ticks = 0;
        secondsPassed = 0;
    }

    @SubscribeEvent
    public void onRender(RenderGameOverlayEvent.Post event) {
        if (event.type != RenderGameOverlayEvent.ElementType.EXPERIENCE) return;
        if (TimerConfig.modToggled && Minecraft.getMinecraft().currentScreen == null) {
            UI.drawTimer(secondsPassed);
        }
    }

    @SubscribeEvent
    public void onKeyInput(InputEvent.KeyInputEvent event) {
        int code = TimerMod.getInstance().keyTimer.getKeyCode();
        if (Keyboard.getEventKeyState() && Keyboard.getEventKey() == code) {
            TimerMod.getInstance().toggleRunning();
            ticks = 0;
            secondsPassed = 0;
        }
    }

    @SubscribeEvent
    public void onMouseInput(InputEvent.MouseInputEvent event) {
        int code = TimerMod.getInstance().keyTimer.getKeyCode();
        if (Mouse.getEventButtonState() && Mouse.getEventButton() == code + 100) {
            TimerMod.getInstance().toggleRunning();
            ticks = 0;
            secondsPassed = 0;
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            if (TimerMod.getInstance().isRunning()) {
                ticks++;
                if (ticks == 20) {
                    secondsPassed++;
                    ticks = 0;
                }
            }
        }
    }


}