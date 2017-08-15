package me.vitikc.awchat.commands;

import me.vitikc.awchat.block.AWBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AWBlock implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0){
            sender.sendMessage("Type name of player to block!");
            return true;
        }
        String playerName = args[0];
        if (Bukkit.getPlayer(playerName) == null || !Bukkit.getPlayer(playerName).isOnline()){
            sender.sendMessage("Can't find player " + playerName);
            return true;
        }
        if (AWBlockManager.getBlocked(sender.getName().toLowerCase()).contains(playerName.toLowerCase())){
            sender.sendMessage("Player " + playerName + " already blocked!");
            return true;
        }
        AWBlockManager.getBlocked(sender.getName().toLowerCase()).add(playerName.toLowerCase());
        sender.sendMessage("Player " + playerName + " is blocked!");
        return true;
    }
}
