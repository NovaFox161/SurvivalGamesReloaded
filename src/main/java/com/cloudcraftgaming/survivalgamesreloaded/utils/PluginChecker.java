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
                if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                    if (Main.plugin.getServer().getPluginManager().getPlugin("PerWorldChatPlus").getDescription().getVersion().equals("5.0.0")
                            || Main.plugin.getServer().getPluginManager().getPlugin("PerWorldChatPlus").getDescription().getVersion().equals("5.0.1")) {
                        Main.plugin.perWorldChatPlus = Main.plugin.getServer().getPluginManager().getPlugin("PerWorldChatPlus");
                        Main.plugin.getLogger().info("PerWorldChatPlus detected! Will use compatibility mode for chat!");
                    } else {
                        Main.plugin.getLogger().info("PerWorldChatPlus is installed! But it is outdated!!");
                        Main.plugin.getConfig().set("Chat.PerWorldChatPlus.CompatibilityMode", false);
                        Main.plugin.saveConfig();
                    }
                }
            } else {
                Main.plugin.getConfig().set("Chat.PerWorldChatPlus.CompatibilityMode", false);
                Main.plugin.saveConfig();
                if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                    Main.plugin.getLogger().info("PerWorldChatPlus not found! Turning off compatibility mode!");
                }
            }
        }
    }
}