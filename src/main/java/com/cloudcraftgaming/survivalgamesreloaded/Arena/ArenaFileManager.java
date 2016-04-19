package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

/**
 * Created by Nova Fox on 3/13/2016.
 * Website: www.cloudcraftgaming.com
 */
public class ArenaFileManager {
    public static void createArenaFiles(int Id) {
        File configFile = new File(Main.plugin.getDataFolder() + "/Arenas/" + String.valueOf(Id) + "/config.yml");
        YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
        config.addDefault("Id", Id);
        config.addDefault("Name", "&5Arena " + String.valueOf(Id));
        config.addDefault("WorldName", "world");

        config.addDefault("Players.Min", 2);
        config.addDefault("Players.Max", 24);

        config.addDefault("Teams.Allow", false);

        config.addDefault("Time.WaitDelay", 90);
        config.addDefault("Time.StartDelay", 30);
        config.addDefault("Time.GameLength", 25);
        config.addDefault("Time.SafeTime", 2);
        config.addDefault("Time.TieEndLength", 5);

        config.addDefault("Rules.Block.Break", false);
        config.addDefault("Rules.Block.Place", false);

        config.options().copyDefaults(true);
        Main.plugin.saveCustomConfig(config, configFile);

        config.options().copyDefaults(true);
        Main.plugin.saveCustomConfig(config, configFile);

        List<String> arenas = Main.plugin.pluginCache.getStringList("Arenas.All");
        arenas.add(String.valueOf(Id));
        Main.plugin.pluginCache.set("Arenas.All", arenas);
        Main.plugin.saveCustomConfig(Main.plugin.pluginCache, Main.plugin.pluginCacheFile);
    }
    public static File getArenaConfigFile(int id) {
        return new File(Main.plugin.getDataFolder() + "/Arenas/" + String.valueOf(id) + "/config.yml");
    }
    public static YamlConfiguration getArenaConfigYml(int id) {
        File configFile = getArenaConfigFile(id);
        return YamlConfiguration.loadConfiguration(configFile);
    }
    public static boolean arenaExists(int id) {
        if (Main.plugin.pluginCache.contains("Arenas.All")) {
            return Main.plugin.pluginCache.getStringList("Arenas.All").contains(String.valueOf(id));
        }
        return false;
    }
    public static boolean canEnableArena(int id) {
        if (!arenaExists(id)) {
            return false;
        }
        if (!getArenaConfigYml(id).contains("Positions.Lobby")) {
            return false;
        }
        if (!getArenaConfigYml(id).contains("Positions.End")) {
            return false;
        }
        if (!getArenaConfigYml(id).contains("Positions.Quit")) {
            return false;
        }
        if (!getArenaConfigYml(id).contains("Positions.Regen")) {
            return false;
        }
        if (!getArenaConfigYml(id).contains("Positions.Spectate")) {
            return false;
        }
        return true;
    }
    public static void createArena(CommandSender sender) {
        if (sender.hasPermission("SGR.use.command.create")) {
            Integer id = Main.plugin.pluginCache.getInt("DoNotEdit.NextId");
            Main.plugin.pluginCache.set("DoNotEdit.NextId", id + 1);
            Main.plugin.saveCustomConfig(Main.plugin.pluginCache, Main.plugin.pluginCacheFile);

            createArenaFiles(id);
            String msgOr = MessageManager.getMessageYml().getString("Command.Create");
            String msg = msgOr.replaceAll("%id%", String.valueOf(id));
            sender.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));

        } else {
            sender.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.NoPerm"));
        }
    }
    public static boolean arenaEnabled(int id) {
        if (Main.plugin.pluginCache.contains("Arenas.Enabled")) {
            if (Main.plugin.pluginCache.getStringList("Arenas.Enabled").contains(String.valueOf(id))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    public static void enableArena(int id) {
        List<String> enabledArenas = Main.plugin.pluginCache.getStringList("Arenas.Enabled");
        enabledArenas.add(String.valueOf(id));
        Main.plugin.pluginCache.set("Arenas.Enabled", enabledArenas);
        Main.plugin.saveCustomConfig(Main.plugin.pluginCache, Main.plugin.pluginCacheFile);
        ArenaManager.getManager().safeLoadArena(id);
    }
    public static void disableArena(int id) {
        List<String> enabledArenas = Main.plugin.pluginCache.getStringList("Arenas.Enabled");
        enabledArenas.remove(String.valueOf(id));
        Main.plugin.pluginCache.set("Arenas.Enabled", enabledArenas);
        Main.plugin.saveCustomConfig(Main.plugin.pluginCache, Main.plugin.pluginCacheFile);
        ArenaManager.getManager().unloadArena(id);
    }
}