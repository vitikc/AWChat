package me.vitikc.awchat.commands;

import me.vitikc.awchat.block.AWBlockManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AWBlocklist implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (AWBlockManager.getBlocked(sender.getName().toLowerCase()).isEmpty()){
            sender.sendMessage("No blocked players");
            return true;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (String s : AWBlockManager.getBlocked(sender.getName().toLowerCase())){
            sb.append(s)
                    .append(", ");
        }
        sb.replace(sb.length()-2,sb.length(),"]");
        sender.sendMessage("Blocked players: " + sb.toString());
        return true;
    }
}
