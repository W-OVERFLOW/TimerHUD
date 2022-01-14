package net.wyvest.timerhud

import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.common.MinecraftForge.EVENT_BUS
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.wyvest.timerhud.commands.TimerHUDCommand
import net.wyvest.timerhud.config.TimerConfig
import net.wyvest.timerhud.listener.Listener
import net.wyvest.timerhud.updater.Updater
import org.lwjgl.input.Keyboard
import java.io.File

@Mod(
    name = TimerHUD.NAME,
    modid = TimerHUD.ID,
    version = TimerHUD.VERSION,
    modLanguageAdapter = "gg.essential.api.utils.KotlinAdapter"
)
object TimerHUD {
    const val NAME = "TimerHUD"
    const val VERSION = "3.1.0"
    const val ID = "timerhud"
    val mc: Minecraft
        get() = Minecraft.getMinecraft()

    lateinit var jarFile: File
    val modDir = File(File(mc.mcDataDir, "W-OVERFLOW"), NAME)
    var timerKeybind: KeyBinding = KeyBinding("Toggle Timer", Keyboard.KEY_NONE, "TimerHUD")

    @Mod.EventHandler
    private fun onFMLPreInitialization(event: FMLPreInitializationEvent) {
        if (!modDir.exists()) modDir.mkdirs()
        jarFile = event.sourceFile
    }

    @Mod.EventHandler
    fun onFMLInitialization(event: FMLInitializationEvent) {
        TimerConfig.initialize()
        TimerHUDCommand.register()
        Updater.update()
        TimerTask.setTimer(true)
        TimerTask.setTimer(false)
        ClientRegistry.registerKeyBinding(timerKeybind)
        EVENT_BUS.register(Listener)
    }
}