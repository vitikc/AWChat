package me.vitikc.awchat.commands;

import me.vitikc.awchat.block.AWBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AWUnblock implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage("Type name of player to unblock!");
            return true;
        }
        String playerName = args[0];
        if (Bukkit.getPlayer(playerName) == null || !Bukkit.getPlayer(playerName).isOnline()){
            sender.sendMessage("Can't find player " + playerName);
            return true;
        }
        if (!AWBlockManager.getBlocked(sender.getName().toLowerCase()).contains(playerName.toLowerCase())){
            sender.sendMessage("Player " + playerName + " not blocked!");
            return true;
        }
        AWBlockManager.getBlocked(sender.getName().toLowerCase()).remove(playerName.toLowerCase());
        sender.sendMessage("Player " + playerName + " unblocked!");
        return true;
    }
}
