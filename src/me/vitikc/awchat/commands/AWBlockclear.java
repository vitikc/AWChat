package me.vitikc.awchat.commands;

import me.vitikc.awchat.block.AWBlockManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AWBlockclear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        AWBlockManager.getBlocked(sender.getName().toLowerCase()).clear();
        sender.sendMessage("Unblocked all players");
        return true;
    }
}
