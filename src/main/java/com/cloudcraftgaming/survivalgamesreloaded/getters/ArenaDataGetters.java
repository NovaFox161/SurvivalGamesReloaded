package com.cloudcraftgaming.survivalgamesreloaded.getters;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaFileManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.Cuboid;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;

/**
 * Created by Nova Fox on 3/13/2016.
 * Website: www.cloudcraftgaming.com
 */
public class ArenaDataGetters {
    public static int getMinPlayers(int id) {
        return ArenaFileManager.getArenaConfigYml(id).getInt("Players.Min");
    }
    public static int getMaxPlayers(int id) {
        return ArenaFileManager.getArenaConfigYml(id).getInt("Players.Max");
    }
    public static World getWorld(int id) {
        String worldName = ArenaFileManager.getArenaConfigYml(id).getString("WorldName");
        return Bukkit.getWorld(worldName);
    }
    public static Location getLobbyPosition(int id) {
        String worldName = ArenaFileManager.getArenaConfigYml(id).getString("Positions.Lobby.world");
        Double x = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Lobby.x");
        Double y = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Lobby.y");
        Double z = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Lobby.z");
        Integer ya = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Lobby.yaw");
        Integer pi = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Lobby.pitch");
        World world = Bukkit.getWorld(worldName);
        return new Location(world, x, y, z, ya, pi);
    }
    public static Location getEndPosition(int id) {
        String worldName = ArenaFileManager.getArenaConfigYml(id).getString("Positions.End.world");
        Double x = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.End.x");
        Double y = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.End.y");
        Double z = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.End.z");
        Integer ya = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.End.yaw");
        Integer pi = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.End.pitch");
        World world = Bukkit.getWorld(worldName);
        return new Location(world, x, y, z, ya, pi);
    }
    public static Location getQuitPosition(int id) {
        String worldName = ArenaFileManager.getArenaConfigYml(id).getString("Positions.Quit.world");
        Double x = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Quit.x");
        Double y = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Quit.y");
        Double z = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Quit.z");
        Integer ya = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Quit.yaw");
        Integer pi = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Quit.pitch");
        World world = Bukkit.getWorld(worldName);
        return new Location(world, x, y, z, ya, pi);
    }
    public static Location getSpectatePosition(int id) {
        String worldName = ArenaFileManager.getArenaConfigYml(id).getString("Positions.Spectate.world");
        Double x = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Spectate.x");
        Double y = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Spectate.y");
        Double z = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Spectate.z");
        Integer ya = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Spectate.yaw");
        Integer pi = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Spectate.pitch");
        World world = Bukkit.getWorld(worldName);
        return new Location(world, x, y, z, ya, pi);
    }
    public static int getWaitDelay(int id) {
        return ArenaFileManager.getArenaConfigYml(id).getInt("Time.WaitDelay");
    }
    public static int getStartDelay(int id) {
        return ArenaFileManager.getArenaConfigYml(id).getInt("Time.StartDelay");
    }
    public static int getGameLength(int id) {
        return ArenaFileManager.getArenaConfigYml(id).getInt("Time.GameLength");
    }
    public static int getSafeTime(int id) {
        return ArenaFileManager.getArenaConfigYml(id).getInt("Time.SafeTime");
    }
    public static int getTieEndLength(int id) {
        return  ArenaFileManager.getArenaConfigYml(id).getInt("Time.TieEndLength");
    }
    public static ArrayList<Location> getSpawns(int id) {
        ArrayList<Location> spawns = new ArrayList<>();
        for (String spawnNum : ArenaFileManager.getArenaConfigYml(id).getStringList("Lists.Spawns")) {
            String worldName = ArenaFileManager.getArenaConfigYml(id).getString("Positions.Spawns." + spawnNum + ".world");
            Double x = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Spawns." + spawnNum + ".x");
            Double y = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Spawns." + spawnNum + ".y");
            Double z = ArenaFileManager.getArenaConfigYml(id).getDouble("Positions.Spawns." + spawnNum + ".z");
            Integer ya = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Spawns." + spawnNum + ".yaw");
            Integer pi = ArenaFileManager.getArenaConfigYml(id).getInt("Positions.Spawns." + spawnNum + ".pitch");
            World world = Bukkit.getWorld(worldName);
            Location spawn = new Location(world, x, y, z, ya, pi);
            spawns.add(spawn);
        }
        return spawns;
    }
    public static String getArenaName(int id) {
        String nameOr = ArenaFileManager.getArenaConfigYml(id).getString("Name");
        return ChatColor.translateAlternateColorCodes('&', nameOr);
    }
    public static Cuboid getRegenArea(int id) {
        YamlConfiguration config = ArenaFileManager.getArenaConfigYml(id);
        String worldName1 = config.getString("Positions.Regen.loc1.world");
        Integer x1 = config.getInt("Positions.Regen.loc1.x");
        Integer y1 = config.getInt("Positions.Regen.loc1.y");
        Integer z1 = config.getInt("Positions.Regen.loc1.z");
        World world1 = Bukkit.getWorld(worldName1);
        String worldName2 = config.getString("Positions.Regen.loc2.world");
        Integer x2 = config.getInt("Positions.Regen.loc2.x");
        Integer y2 = config.getInt("Positions.Regen.loc2.y");
        Integer z2 = config.getInt("Positions.Regen.loc2.z");
        World world2 = Bukkit.getWorld(worldName2);
        Location loc1 = new Location(world1, x1, y1, z1);
        Location loc2 = new Location(world2, x2, y2, z2);
        return new Cuboid(loc1, loc2);
    }
    public static String getChatPrefix(int id) {
        String prefixOr = Main.plugin.getConfig().getString("Chat.Prefix");
        String prefix = prefixOr.replaceAll("%ArenaName%", getArenaName(id));
        return ChatColor.translateAlternateColorCodes('&', prefix) + ChatColor.RESET;
    }
}