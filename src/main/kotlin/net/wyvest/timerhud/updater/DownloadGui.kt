package net.wyvest.timerhud.updater

import gg.essential.api.EssentialAPI
import gg.essential.api.gui.buildConfirmationModal
import gg.essential.api.utils.Multithreading
import gg.essential.elementa.ElementaVersion
import gg.essential.elementa.WindowScreen
import gg.essential.elementa.dsl.childOf
import net.wyvest.timerhud.TimerHUD
import java.io.File

class DownloadGui : WindowScreen(version = ElementaVersion.V1, true, true, true, -1) {
    override fun initScreen(width: Int, height: Int) {
        super.initScreen(width, height)
        EssentialAPI.getEssentialComponentFactory().buildConfirmationModal {
            this.text = "Are you sure you want to update?"
            this.secondaryText =
                "(This will update from v${TimerHUD.VERSION} to ${Updater.latestTag})"
            this.onConfirm = {
                restorePreviousScreen()
                Multithreading.runAsync {
                    if (Updater.download(
                            Updater.updateUrl,
                            File(
                                "mods/${TimerHUD.NAME}-${
                                    Updater.latestTag!!.substringAfter("v")
                                }.jar"
                            )
                        ) && Updater.download(
                            "https://github.com/Wyvest/Deleter/releases/download/v1.2/Deleter-1.2.jar",
                            File(TimerHUD.modDir.parentFile, "Deleter-1.2.jar")
                        )
                    ) {
                        EssentialAPI.getNotifications()
                            .push(
                                TimerHUD.NAME,
                                "The ingame updater has successfully installed the newest version."
                            )
                        Updater.addShutdownHook()
                        Updater.shouldUpdate = false
                    } else {
                        EssentialAPI.getNotifications().push(
                            TimerHUD.NAME,
                            "The ingame updater has NOT installed the newest version as something went wrong."
                        )
                    }
                }
            }
            this.onDeny = {
                restorePreviousScreen()
            }
        } childOf this.window
    }
}