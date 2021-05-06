package net.wyvest.timer;

import club.sk1er.mods.core.universal.UDesktop;
import club.sk1er.vigilance.Vigilance;
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
import net.wyvest.timer.others.VersionChecker;

import java.net.URI;

/**
 * @author Wyvest
 */

@Mod(name = TimerHUD.modName, version = TimerHUD.version, modid = TimerHUD.modId)
public class TimerHUD {

    public static final String modName = "TimerHUD";
    public static final String version = "2.0.1";
    public static final String modId = "timer";

    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {
        WyLib.getInstance().onForgePreInit();
        VersionChecker.getVersion();
        Timer.instance.setTimer(false);
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
        try {
            if (!VersionChecker.version.matches(version)) Notifications.push("TimerHUD", "Your version of TimerHUD is outdated. Please update to the latest version by clicking here.", this::openTab);
        } catch (Exception e) {e.printStackTrace();}
    }

    void openTab() {
        try {
            UDesktop.browse(URI.create("https://wyvest.net/timerhud"));
        } catch (Exception e) {e.printStackTrace();}
    }

}