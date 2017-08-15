package me.vitikc.awchat;

import me.vitikc.awchat.commands.*;
import me.vitikc.awchat.config.AWConfigManager;
import me.vitikc.awchat.listeners.AWChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class AWMain extends JavaPlugin {
    private AWChatListener chatListener;
    private AWConfigManager configManager;
    private static AWMain instance;

    @Override
    public void onEnable(){
        instance = this;
        configManager = new AWConfigManager();
        AWChatListener.setChatCleanDelay(configManager.getChatCleanDelay());
        registerListeners();
        registerCommands();
        AWChatListener.startClearChat();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static AWMain getInstance(){
        return instance;
    }

    private void registerListeners(){
        chatListener = new AWChatListener();
        getServer().getPluginManager().registerEvents(chatListener, this);
    }

    private void registerCommands(){
        getServer().getPluginCommand("block").setExecutor(new AWBlock());
        getServer().getPluginCommand("blockall").setExecutor(new AWBlockall());
        getServer().getPluginCommand("blockclear").setExecutor(new AWBlockclear());
        getServer().getPluginCommand("blocklist").setExecutor(new AWBlocklist());
        getServer().getPluginCommand("unblock").setExecutor(new AWUnblock());
    }

}
