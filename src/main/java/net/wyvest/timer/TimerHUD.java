package net.wyvest.timer;

import club.sk1er.modcore.ModCoreInstaller;
import lombok.Getter;
import lombok.Setter;
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
import net.wyvest.timer.others.APICaller;
import net.wyvest.timer.others.Constants;
import net.wyvest.timer.others.JsonResponse;
import net.wyvest.timer.others.Updater;

/**
 * @author Wyvest
 */

@Getter
@Mod(name = Constants.NAME, version = Constants.VER, modid = Constants.ID)
public class TimerHUD {

    private final APICaller apiCaller;
    @Setter @Getter private boolean running;
    public KeyBinding keyTimer = new KeyBinding("Toggle Timer", 25, "TimerHUD");
    public final TimerConfig config = new TimerConfig();
    @Setter @Getter private JsonResponse onlineData;
    @Getter private static TimerHUD instance;
    private final Updater updater;

    public TimerHUD() {
        instance = this;
        apiCaller = new APICaller();
        updater = new Updater();
    }

    @Mod.EventHandler
    protected void onPreInit(FMLPreInitializationEvent event) {}

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
        apiCaller.pullOnlineData();
    }

    public void toggleRunning() {
        running = !running;
    }


}