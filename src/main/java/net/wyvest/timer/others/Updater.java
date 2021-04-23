package net.wyvest.timer.others;

import club.sk1er.mods.core.gui.notification.Notifications;
import kotlin.Unit;
import net.minecraftforge.common.ForgeVersion;
import net.minecraftforge.fml.common.versioning.ComparableVersion;
import net.wyvest.timer.TimerHUD;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

import static net.minecraftforge.common.ForgeVersion.Status.*;

/**
 * @author Biscuit Development, under the MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
public class Updater {
    public static ForgeVersion.Status status;

    public void processUpdateCheckResult() {
        ComparableVersion current = new ComparableVersion(Constants.VER);
        ComparableVersion latest = new ComparableVersion(TimerHUD.getInstance().getOnlineData().version);

        int versionDifference = latest.compareTo(current);
        if (versionDifference == 0) {
            status = UP_TO_DATE;
        } else if (versionDifference < 0) {
            status = AHEAD;
        } else {
            status = OUTDATED;
        }
        if (status == OUTDATED) {
            Notifications.INSTANCE.pushNotification("TimerHUD", "Your version of TimerHUD is outdated. Please update to the latest version by clicking here.", this::browseDownloadPage);
        }
    }

    private Unit browseDownloadPage() {
        try {
            Desktop.getDesktop().browse(this.URLtoURI(new URL("https://wyvest.net/timerhud")));
            return Unit.INSTANCE;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private URI URLtoURI(URL url) {
        try {
            return url.toURI();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
