package net.wyvest.timer.command;

import club.sk1er.mods.core.ModCore;
import club.sk1er.mods.core.universal.ChatColor;
import club.sk1er.mods.core.util.MinecraftUtils;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.wyvest.timer.TimerHUD;
import net.wyvest.timer.overlay.GUI;

public class TimerCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "timerhud";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/timerhud";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length <= 0) {
            ModCore.getInstance().getGuiHandler().open(TimerHUD.getInstance().config.gui());
            return;
        }
        switch (args[0].toLowerCase()) {
            default:
                MinecraftUtils.sendMessage(ChatColor.GREEN + "[TimerHUD] ", ChatColor.LIGHT_PURPLE + "Unknown argument. Type /timerhud help for correct usage.");
                break;
            case "gui":
            case "hud":
                ModCore.getInstance().getGuiHandler().open(new GUI());
                break;
            case "help":
                MinecraftUtils.sendMessage(ChatColor.GREEN + "[TimerHUD] ", ChatColor.LIGHT_PURPLE + "Command Help\n" + "/timerhud - Open Config Menu\n" + "/timerhud help - Shows help for command usage\n" + "/timerhud hud or /timerhud gui - Opens a GUI to configure where the timer is rendered.\n" + "/timerhud config - Open Config Menu");
                break;
            case "config":
                ModCore.getInstance().getGuiHandler().open(TimerHUD.getInstance().config.gui());
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
