package me.vitikc.awchat.config;

import me.vitikc.awchat.AWMain;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.nio.file.attribute.FileAttribute;
import java.util.Properties;


public class AWConfigManager {
    File file;
    YamlConfiguration config;

    public AWConfigManager(){
        file = new File(AWMain.getInstance().getDataFolder(), "config.yml");
        config = YamlConfiguration.loadConfiguration(file);
        if (!file.exists()){
            saveConfig();
        }
        if (!config.isSet("chatCleanDelay")){
            config.set("chatCleanDelay", 30);
            saveConfig();
        }
    }

    private void saveConfig(){
        try {
            config.save(file);
        } catch (IOException e) {
            AWMain.getInstance().getServer().getLogger().info("Can't save config file!");
            e.printStackTrace();
        }
    }

    public int getChatCleanDelay(){
        int delay = 30;
        if (config.getInt("chatCleanDelay") <= 0){
            return delay;
        }
        delay = config.getInt("chatCleanDelay");
        return delay;
    }

}