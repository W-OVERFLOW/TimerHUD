package xyz.qalcyo.timerhud

import gg.essential.universal.ChatColor
import net.minecraft.client.Minecraft
import net.minecraft.client.settings.KeyBinding
import net.minecraft.util.ChatComponentText
import net.minecraft.util.EnumChatFormatting
import net.minecraftforge.common.MinecraftForge.EVENT_BUS
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import xyz.qalcyo.timerhud.commands.TimerHUDCommand
import xyz.qalcyo.timerhud.config.TimerConfig
import xyz.qalcyo.timerhud.listener.Listener
import xyz.qalcyo.timerhud.utils.Updater
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
    const val VERSION = "3.0.0"
    const val ID = "timerhud"
    val mc: Minecraft
        get() = Minecraft.getMinecraft()

    fun sendMessage(message: String) {
        if (mc.thePlayer == null)
            return
        val text =
            ChatComponentText(EnumChatFormatting.DARK_PURPLE.toString() + "[$NAME] " + ChatColor.RESET.toString() + " " + message)
        Minecraft.getMinecraft().thePlayer.addChatMessage(text)
    }

    lateinit var jarFile: File
    val modDir = File(File(File(mc.mcDataDir, "config"), "Qalcyo"), NAME)
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