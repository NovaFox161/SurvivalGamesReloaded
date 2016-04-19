package com.cloudcraftgaming.survivalgamesreloaded.setters;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaFileManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

/**
 * Created by Nova Fox on 3/13/2016.
 * Website: www.cloudcraftgaming.com
 */
public class ArenaDataSetters {
    public static void setMinPlayers(int id, int amount) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Players.Min", amount);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setMaxPlayers(int id, int amount) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Players.Max", amount);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setWorld(int id, World world) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("WorldName", world.getName());
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setLobbyPosition(int id, Location lobby) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Positions.Lobby.world", lobby.getWorld().getName());
        config.set("Positions.Lobby.x", lobby.getX());
        config.set("Positions.Lobby.y", lobby.getY());
        config.set("Positions.Lobby.z", lobby.getZ());
        config.set("Positions.Lobby.yaw", lobby.getYaw());
        config.set("Positions.Lobby.pitch", lobby.getPitch());
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setEndPosition(int id, Location end) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Positions.End.world", end.getWorld().getName());
        config.set("Positions.End.x", end.getX());
        config.set("Positions.End.y", end.getY());
        config.set("Positions.End.z", end.getZ());
        config.set("Positions.End.yaw", end.getYaw());
        config.set("Positions.End.pitch", end.getPitch());
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setQuitPosition(int id, Location quit) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Positions.Quit.world", quit.getWorld().getName());
        config.set("Positions.Quit.x", quit.getX());
        config.set("Positions.Quit.y", quit.getY());
        config.set("Positions.Quit.z", quit.getZ());
        config.set("Positions.Quit.yaw", quit.getYaw());
        config.set("Positions.Quit.pitch", quit.getPitch());
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setSpectatePosition(int id, Location loc) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Positions.Spectate.world", loc.getWorld().getName());
        config.set("Positions.Spectate.x", loc.getX());
        config.set("Positions.Spectate.y", loc.getY());
        config.set("Positions.Spectate.z", loc.getZ());
        config.set("Positions.Spectate.yaw", loc.getYaw());
        config.set("Positions.Spectate.pitch", loc.getPitch());
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setWaitDelay(int id, int time) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Time.WaitDelay", time);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setStartDelay(int id, int time) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Time.StartDelay", time);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setGameLength(int id, int time) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Time.GameLength", time);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setSafeTime(int id, int time) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Time.SafeTime", time);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setTieEndLength(int id, int time) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Time.TieEndLength", time);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setSpawn(int id, Location spawn, int spawnNumber) {
        if (ArenaFileManager.arenaExists(id)) {
            YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
            String spawnNum = String.valueOf(spawnNumber);
            config.set("Positions.Spawns." + spawnNum + ".world", spawn.getWorld().getName());
            config.set("Positions.Spawns." + spawnNum + ".x", spawn.getX());
            config.set("Positions.Spawns." + spawnNum + ".y", spawn.getY());
            config.set("Positions.Spawns." + spawnNum + ".z", spawn.getZ());
            config.set("Positions.Spawns." + spawnNum + ".yaw", spawn.getYaw());
            config.set("Positions.Spawns." + spawnNum + ".pitch", spawn.getPitch());
            List<String> spawnList = config.getStringList("Lists.Spawns");
            if (!(spawnList.contains(spawnNum))) {
                spawnList.add(spawnNum);
                config.set("Lists.Spawns", spawnList);
            }
            Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
        }
    }
    public static void setRegenArea(int id, Location loc1, Location loc2) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Positions.Regen.loc1.world", loc1.getWorld().getName());
        config.set("Positions.Regen.loc1.x", loc1.getX());
        config.set("Positions.Regen.loc1.y", loc1.getY());
        config.set("Positions.Regen.loc1.z", loc1.getZ());
        config.set("Positions.Regen.loc2.world", loc2.getWorld().getName());
        config.set("Positions.Regen.loc2.x", loc2.getX());
        config.set("Positions.Regen.loc2.y", loc2.getY());
        config.set("Positions.Regen.loc2.z", loc2.getZ());
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
    public static void setName(int id, String name) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        config.set("Name", name);
        Main.plugin.saveCustomConfig(config, ArenaFileManager.getArenaConfigFile(id));
    }
}