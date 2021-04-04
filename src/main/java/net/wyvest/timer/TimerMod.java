package net.wyvest.timer;

import club.sk1er.modcore.ModCoreInstaller;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.wyvest.timer.command.TimerCommand;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.listener.TimerListener;
import net.wyvest.timer.others.Constants;

@Mod(name = Constants.NAME, version = Constants.VER, modid = Constants.ID)
public class TimerMod {

    @Mod.Instance(Constants.ID)
    private static TimerMod INSTANCE;

    private boolean running;
    public KeyBinding keyTimer = new KeyBinding("Toggle MeasureTimer", 25, "MeasureTimer");
    private final TimerConfig config = new TimerConfig();

    public static TimerMod getInstance() {
        if (INSTANCE == null)
            INSTANCE = new TimerMod();
        return INSTANCE;
    }


    @Mod.EventHandler
    protected void onInit(FMLInitializationEvent event) {
        ModCoreInstaller.initializeModCore(Minecraft.getMinecraft().mcDataDir);
        ClientRegistry.registerKeyBinding(this.keyTimer);
        MinecraftForge.EVENT_BUS.register(new TimerListener());
        ClientCommandHandler.instance.registerCommand(new TimerCommand());
        config.preload();
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
}