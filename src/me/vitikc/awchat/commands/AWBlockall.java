package me.vitikc.awchat.commands;

import me.vitikc.awchat.block.AWBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AWBlockall implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        for (Player player : Bukkit.getServer().getOnlinePlayers()){
            if (player.getName().equalsIgnoreCase(sender.getName()))
                continue;
            AWBlockManager.getBlocked(sender.getName().toLowerCase()).add(player.getName().toLowerCase());
        }
        sender.sendMessage("All online players blocked!");
        return true;
    }
}
