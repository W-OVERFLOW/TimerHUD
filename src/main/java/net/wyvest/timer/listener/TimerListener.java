package net.wyvest.timer.listener;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.wyvest.timer.Timer;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.overlay.GUI;
import net.wyvest.timer.overlay.HUD;

/**
 * @author Wyvest
 */

public class TimerListener {

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (!TimerConfig.turnOnTimerWhenWorldEnter) return;
        Timer.instance.setTimer(true);
    }

    @SubscribeEvent
    public void onWorldUnload(WorldEvent.Unload event) {
        Timer.instance.setTimer(false);
    }

    @SubscribeEvent
    protected void onGameOverlayRendered(RenderGameOverlayEvent.Post event) {
        if (event.type == RenderGameOverlayEvent.ElementType.ALL) {
            if (TimerConfig.modToggled) {
                if (((Minecraft.getMinecraft().currentScreen == null || TimerConfig.showinGui) && !(Minecraft.getMinecraft().currentScreen instanceof GUI)) && Minecraft.getMinecraft().thePlayer != null) {
                    HUD.drawTimer(Timer.instance.secondsPassed);
                }
            }
        }
    }

}