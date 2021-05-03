package net.wyvest.timer;

import club.sk1er.vigilance.Vigilance;
import lombok.Getter;
import lombok.Setter;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.wyvest.lib.WyLib;
import net.wyvest.lib.util.Notifications;
import net.wyvest.lib.util.betterkeybinds.KeyBindManager;
import net.wyvest.timer.command.TimerCommand;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.keybind.TimerKeybind;
import net.wyvest.timer.listener.TimerListener;
import net.wyvest.timer.others.JsonResponse;
import net.wyvest.timer.others.VersionChecker;

import java.awt.*;
import java.net.URI;

/**
 * @author Wyvest
 */

@Getter
@Mod(name = "TimerHUD", version = "1.5.1", modid = "timer")
public class TimerHUD {

    public String modName = "TimerHUD";
    public String version = "1.5.1";
    public String modId = "timer";
    @Setter @Getter private boolean running;
    @Setter @Getter private JsonResponse onlineData;

    @Mod.Instance()
    public static TimerHUD INSTANCE;

    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {
        WyLib.getInstance().onForgePreInit();
        VersionChecker.getVersion();
    }

    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        Vigilance.initialize();
        KeyBindManager.register(new TimerKeybind());
        MinecraftForge.EVENT_BUS.register(new TimerListener());
        ClientCommandHandler.instance.registerCommand(new TimerCommand());
        TimerConfig.INSTANCE.preload();
    }

    @Mod.EventHandler
    protected void onPostInit(FMLPostInitializationEvent event) {
        if (!VersionChecker.version.matches(version)) Notifications.push("TimerHUD", "Your version of TimerHUD is outdated. Please update to the latest version by clicking here.", this::openTab);
    }

    void openTab() {
        try {
            Desktop.getDesktop().browse(URI.create("https://wyvest.net/checker"));
        } catch (Exception e) {e.printStackTrace();}
    }

    public void toggleRunning() {
        running = !running;
        TimerListener.ticks = 0;
        TimerListener.secondsPassed = 0;
    }


}