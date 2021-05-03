package net.wyvest.timer;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.wyvest.lib.WyLib;
import net.wyvest.lib.util.Notifications;
import net.wyvest.lib.util.betterkeybinds.KeyBind;
import net.wyvest.lib.util.betterkeybinds.KeyBindManager;
import net.wyvest.timer.command.TimerCommand;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.keybind.TimerKeybind;
import net.wyvest.timer.listener.TimerListener;
import net.wyvest.timer.others.Constants;
import net.wyvest.timer.others.JsonResponse;
import net.wyvest.timer.others.VersionChecker;

import java.awt.*;
import java.net.URI;

/**
 * @author Wyvest
 */

@Getter
@Mod(name = Constants.NAME, version = Constants.VER, modid = Constants.ID)
public class TimerHUD {

    @Setter @Getter private boolean running;
    public final TimerConfig config = new TimerConfig();
    @Setter @Getter private JsonResponse onlineData;

    @Mod.Instance(Constants.ID)
    public static TimerHUD INSTANCE;

    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {
        WyLib.getInstance().onForgePreInit();
        VersionChecker.getVersion();
    }

    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        KeyBindManager.register(new TimerKeybind());
        MinecraftForge.EVENT_BUS.register(new TimerListener());
        ClientCommandHandler.instance.registerCommand(new TimerCommand());
        config.preload();
    }

    @Mod.EventHandler
    protected void onPostInit(FMLPostInitializationEvent event) {
        if (Double.parseDouble(VersionChecker.version) > Double.parseDouble(Constants.VER)) Notifications.push("TimerHUD", "Your version of TimerHUD is outdated. Please update to the latest version by clicking here.", this::openTab);
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