package net.wyvest.timer.command;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.universal.ChatColor;
import club.sk1er.mods.core.util.MinecraftUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.wyvest.timer.config.TimerConfig;
import net.wyvest.timer.overlay.GUI;

public class TimerCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "timermod";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/timermod";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length <= 0) {
            ModCore.getInstance().getGuiHandler().open(new TimerConfig().gui());
            return;
        }
        switch (args[0].toLowerCase()) {
            default:
                MinecraftUtils.sendMessage(ChatColor.GREEN + "[Timer Mod] ", ChatColor.LIGHT_PURPLE + "Unknown argument. Type /timermod help for correct usage.");
                break;
            case "hud":
                ModCore.getInstance().getGuiHandler().open(new GUI());
                break;
            case "help":
                MinecraftUtils.sendMessage(ChatColor.GREEN + "[Timer Mod] ", ChatColor.LIGHT_PURPLE + "Command Help\n" + "/timermod - Open Config Menu\n" + "/timermod help - Shows help for command usage\n" + "/timermod hud - Opens a GUI to configure where the timer is rendered.\n" + "/timermod config - Open Config Menu");
                break;
            case "config":
                ModCore.getInstance().getGuiHandler().open(new TimerConfig().gui());
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
