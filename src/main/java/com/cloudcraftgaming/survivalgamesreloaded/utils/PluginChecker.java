package com.cloudcraftgaming.survivalgamesreloaded.utils;

import com.cloudcraftgaming.survivalgamesreloaded.Main;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class PluginChecker {
    public static void checkForPerWorldChatPlus() {
        if (Main.plugin.getConfig().getString("Chat.PerWorldChatPlus.CompatibilityMode").equalsIgnoreCase("True")) {
            if (Main.plugin.getServer().getPluginManager().getPlugin("PerWorldChatPlus") != null) {
                Main.plugin.getLogger().info("PerWorldChatPlus detected! Will use compatibility mode for chat!");
            } else {
                Main.plugin.getConfig().set("Chat.PerWorldChatPlus.CompatibilityMode", false);
                Main.plugin.saveConfig();
                Main.plugin.getLogger().info("PerWorldChatPlus not found! Turning off compatibility mode!");
            }
        }
    }
}