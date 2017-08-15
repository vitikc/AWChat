package me.vitikc.awchat.listeners;

import me.vitikc.awchat.AWMain;
import me.vitikc.awchat.block.AWBlockManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class AWChatListener implements Listener{

    private static int chatCleanDelay = 30;
    private static int taskId = 0;

    @EventHandler
    public void onPlayerChatEvent(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        String message = event.getMessage();
        event.setFormat("%2$s");
        if (message.startsWith("!")){
            message = message.substring(1);
            if (message.isEmpty()) {
                event.setCancelled(true);
                return;
            }
            event.setMessage(formatMessage(message,ChatColor.YELLOW, "", player.getName()));
        } else if (message.startsWith("+")){
            message = message.substring(1);
            if (message.isEmpty()) {
                event.setCancelled(true);
                return;
            }
            event.setMessage(formatMessage(message,ChatColor.GOLD, "", player.getName()));
        } else if (message.startsWith("\"")) {
            message = message.substring(1);
            if (message.isEmpty()) {
                event.setCancelled(true);
                return;
            }
            String receiver = message.split(" ")[0];
            if (player.getName().equalsIgnoreCase(receiver)){
                player.sendMessage("Can't send message to myself");
                event.setCancelled(true);
                return;
            }
            if (receiver.isEmpty()){
                player.sendMessage("No receiver for message!");
                event.setCancelled(true);
                return;
            }
            if (Bukkit.getPlayer(receiver)==null || !Bukkit.getPlayer(receiver).isOnline()) {
                player.sendMessage("Player " + receiver + " not found!");
                event.setCancelled(true);
                return;
            }
            if (AWBlockManager.contains(receiver)&&AWBlockManager.getBlocked(receiver).contains(player.getName().toLowerCase())){
                player.sendMessage(receiver + " is ignoring you");
                event.setCancelled(true);
                return;
            }
            if (message.split(" ").length < 2 || message.split(" ")[1].isEmpty()){
                player.sendMessage("Can't send empty message!");
                event.setCancelled(true);
                return;
            }
            String splitMessage = message.split(" ")[1];
            Player playerReceiver = Bukkit.getPlayer(receiver);
            playerReceiver.sendMessage(formatMessage(splitMessage,ChatColor.LIGHT_PURPLE,
                    ChatColor.LIGHT_PURPLE.toString(), player.getName()));
            player.sendMessage(formatMessage(splitMessage,ChatColor.LIGHT_PURPLE,
                    ChatColor.LIGHT_PURPLE + "> ", receiver ));
            event.setCancelled(true);
        } else {
            Location location = player.getLocation();
            for (Player p : event.getRecipients()){
                if (location.distance(p.getLocation())< 100){
                    p.sendMessage(formatMessage(message, ChatColor.WHITE, "", player.getName()));
                }
            }
            event.setCancelled(true);
        }
    }

    private String formatMessage(String message, ChatColor color, String prefix, String name){
        StringBuilder sb = new StringBuilder();
        sb.append(prefix)
                .append(name)
                .append(color + ": ")
                .append(message);
        return sb.toString();
    }

    public static void startClearChat(){
        taskId = new BukkitRunnable(){
            @Override
            public void run() {
                AWMain.getInstance().getServer().getLogger().info("Chat cleaning!");
                for(Player p : AWMain.getInstance().getServer().getOnlinePlayers()){
                    for(int i = 0; i < 100; i++)
                        p.sendMessage("");
                }
                startClearChat();
            }
        }.runTaskLaterAsynchronously(AWMain.getInstance(), chatCleanDelay * 1200L).getTaskId();
    }

    public static void setChatCleanDelay(int delay){
        chatCleanDelay = delay;
    }

    public static int getTaskId() {
        return taskId;
    }
}
