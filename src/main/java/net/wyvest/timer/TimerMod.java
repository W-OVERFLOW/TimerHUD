package net.wyvest.timer;

import club.sk1er.modcore.ModCoreInstaller;
import club.sk1er.mods.core.gui.notification.Notifications;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.wyvest.timer.command.TimerCommand;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.listener.TimerListener;
import net.wyvest.timer.others.Constants;
import net.wyvest.timer.others.VersionChecker;

@Mod(name = Constants.NAME, version = Constants.VER, modid = Constants.ID)
public class TimerMod {

    @Mod.Instance(Constants.ID)
    private static TimerMod INSTANCE;
    public VersionChecker VERSION_CHECKER = new VersionChecker();
    private boolean latestVersion;
    private boolean running;
    public KeyBinding keyTimer = new KeyBinding("Toggle MeasureTimer", 25, "MeasureTimer");
    public final TimerConfig config = new TimerConfig();

    public static TimerMod getInstance() {
        if (INSTANCE == null)
            INSTANCE = new TimerMod();
        return INSTANCE;
    }

    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {
        if (this.VERSION_CHECKER.getEmergencyStatus())
            throw new RuntimeException("PLEASE UPDATE TO THE NEW VERSION OF " + Constants.NAME + "\nTHIS IS AN EMERGENCY!");
        this.latestVersion = this.VERSION_CHECKER.getVersion().equals(Constants.VER);
    }

    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
        ClientRegistry.registerKeyBinding(this.keyTimer);
        MinecraftForge.EVENT_BUS.register(new TimerListener());
        ClientCommandHandler.instance.registerCommand(new TimerCommand());
        config.preload();

    }

    @Mod.EventHandler
    protected void onPostInit(FMLPostInitializationEvent event) {
        if (!isLatestVersion()) {
            Notifications.INSTANCE.pushNotification(Constants.NAME, Constants.NAME + " is out of date. Please update to the latest version.");
        }
    }


    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void toggleRunning() {
        running = !running;
    }

    public boolean isLatestVersion() {
        return latestVersion;
    }


}