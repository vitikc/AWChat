package me.vitikc.awchat.block;

import java.util.HashMap;
import java.util.HashSet;

public class AWBlockManager {
    private static HashMap<String, HashSet<String>> blocked = new HashMap<>();

    public static HashSet<String> getBlocked(String player){
        if (!contains(player)){
            blocked.put(player, new HashSet<String>());
        }
        return blocked.get(player);
    }

    public static boolean contains(String player){
        return blocked.containsKey(player);
    }
}
