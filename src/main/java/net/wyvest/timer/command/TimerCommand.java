package net.wyvest.timer.command;

import club.sk1er.mods.core.universal.ChatColor;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.wyvest.lib.util.ChatHandler;
import net.wyvest.lib.util.GuiHelper;
import net.wyvest.timer.config.TimerConfig;
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
            GuiHelper.open(TimerConfig.INSTANCE.gui());
            return;
        }
        switch (args[0].toLowerCase()) {
            default:
                ChatHandler.sendMessage(ChatColor.GREEN + "[TimerHUD] " + ChatColor.LIGHT_PURPLE + "Unknown argument. Type /timerhud help for correct usage.");
                break;
            case "gui":
            case "hud":
                GuiHelper.open(new GUI());
                break;
            case "help":
                ChatHandler.sendMessage(ChatColor.GREEN + "[TimerHUD] " + ChatColor.LIGHT_PURPLE + "Command Help\n" + "/timerhud - Open Config Menu\n" + "/timerhud help - Shows help for command usage\n" + "/timerhud hud or /timerhud gui - Opens a GUI to configure where the timer is rendered.\n" + "/timerhud config - Open Config Menu");
                break;
            case "config":
                GuiHelper.open(TimerConfig.INSTANCE.gui());
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return -1;
    }
}
