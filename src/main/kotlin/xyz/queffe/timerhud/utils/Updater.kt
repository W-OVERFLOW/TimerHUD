package xyz.queffe.timerhud.utils

import gg.essential.api.EssentialAPI
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import net.minecraft.util.Util
import net.minecraftforge.fml.common.versioning.DefaultArtifactVersion
import xyz.queffe.timerhud.TimerHUD
import xyz.queffe.timerhud.TimerHUD.mc
import xyz.queffe.timerhud.config.TimerConfig
import xyz.queffe.timerhud.gui.DownloadConfirmGui
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpGet
import java.awt.Desktop
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


object Updater {
    var updateUrl: String = ""
    lateinit var latestTag: String
    var shouldUpdate = false

    /**
     * Adapted from SimpleToggleSprint under AGPLv3
     * https://github.com/My-Name-Is-Jeff/SimpleToggleSprint/blob/1.8.9/LICENSE
     */
    fun update() {
        CoroutineScope(Dispatchers.IO + CoroutineName("${TimerHUD.NAME}-UpdateChecker")).launch {
            val latestRelease =
                APIUtil.getJSONResponse("https://api.github.com/repos/Queffe/${TimerHUD.ID}/releases/latest")
            latestTag = latestRelease.get("tag_name").asString

            val currentVersion = DefaultArtifactVersion(TimerHUD.VERSION.substringBefore("-"))
            val latestVersion = DefaultArtifactVersion(latestTag.substringAfter("v").substringBefore("-"))

            if ((TimerHUD.VERSION.contains("BETA") && currentVersion >= latestVersion)) {
                return@launch
            } else if (currentVersion < latestVersion) {
                updateUrl = latestRelease["assets"].asJsonArray[0].asJsonObject["browser_download_url"].asString
            }
            if (updateUrl.isNotEmpty()) {
                if (TimerConfig.showUpdateNotification) {
                    EssentialAPI.getNotifications()
                        .push(
                            "Mod Update",
                            "${TimerHUD.NAME} $latestTag is available!\nClick here to download it!",
                            5f
                        ) {
                            EssentialAPI.getGuiUtil().openScreen(DownloadConfirmGui(mc.currentScreen))
                        }
                }
                shouldUpdate = true
            }
        }
    }

    /**
     * Adapted from RequisiteLaunchwrapper under LGPLv2.1
     * https://github.com/TGMDevelopment/RequisiteLaunchwrapper/blob/main/LICENSE
     */
    fun download(url: String, file: File): Boolean {
        if (file.exists()) return true
        var newUrl = url
        newUrl = newUrl.replace(" ", "%20")
        try {
            FileOutputStream(file).use { fileOut ->
                val downloadResponse: HttpResponse = APIUtil.builder.build().execute(HttpGet(newUrl))
                val buffer = ByteArray(1024)
                var read: Int
                while (downloadResponse.entity.content.read(buffer).also { read = it } > 0) {
                    fileOut.write(buffer, 0, read)
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }
        return true
    }

    /**
     * Adapted from Skytils under AGPLv3
     * https://github.com/Skytils/SkytilsMod/blob/1.x/LICENSE.md
     */
    fun addShutdownHook() {
        EssentialAPI.getShutdownHookUtil().register(Thread {
            println("Deleting old ${TimerHUD.NAME} jar file...")
            try {
                val runtime = getJavaRuntime()
                if (Util.getOSType() == Util.EnumOS.OSX) {
                    println("On Mac, trying to open mods folder")
                    Desktop.getDesktop().open(TimerHUD.jarFile.parentFile)
                }
                println("Using runtime $runtime")
                val file = File(TimerHUD.modDir.parentFile, "Deleter-1.2.jar")
                println("\"$runtime\" -jar \"${file.absolutePath}\" \"${TimerHUD.jarFile.absolutePath}\"")
                Runtime.getRuntime()
                    .exec("\"$runtime\" -jar \"${file.absolutePath}\" \"${TimerHUD.jarFile.absolutePath}\"")
            } catch (e: Throwable) {
                e.printStackTrace()
            }
            Thread.currentThread().interrupt()
        })
    }

    /**
     * Gets the current Java runtime being used.
     * @link https://stackoverflow.com/a/47925649
     */
    @Throws(IOException::class)
    fun getJavaRuntime(): String {
        val os = System.getProperty("os.name")
        val java = "${System.getProperty("java.home")}${File.separator}bin${File.separator}${
            if (os != null && os.lowercase().startsWith("windows")) "java.exe" else "java"
        }"
        if (!File(java).isFile) {
            throw IOException("Unable to find suitable java runtime at $java")
        }
        return java
    }

}