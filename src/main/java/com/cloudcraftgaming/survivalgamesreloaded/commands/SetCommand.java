package com.cloudcraftgaming.survivalgamesreloaded.commands;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaFileManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.setters.ArenaDataSetters;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class SetCommand {
    protected static void setCommand(Player player, Integer id, String[] args) {
        if (player.hasPermission("SGR.use.command.set")) {
            if (args.length == 3) {
                String setType = args[2];
                if (setType.equalsIgnoreCase("end") || setType.equalsIgnoreCase("EndPosition")) {
                    ArenaDataSetters.setEndPosition(id, player.getLocation());
                    String msgOr = MessageManager.getMessageYml().getString("Command.Set.End");
                    String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                } else if (setType.equalsIgnoreCase("Enabled") || setType.equalsIgnoreCase("Enable")) {
                    if (ArenaFileManager.arenaEnabled(id)) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.AlreadyEnabled"));
                    } else {
                        if (ArenaFileManager.canEnableArena(id)) {
                            ArenaFileManager.enableArena(id);
                            String msgOr = MessageManager.getMessageYml().getString("Command.Set.Enable");
                            String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                        }
                    }
                } else if (setType.equalsIgnoreCase("Disabled") || setType.equalsIgnoreCase("Disable")) {
                    if (ArenaFileManager.arenaEnabled(id)) {
                        ArenaFileManager.disableArena(id);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Disable");
                        String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } else {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.AlreadyDisabled"));
                    }
                } else if (setType.equalsIgnoreCase("quit") || setType.equalsIgnoreCase("QuitPosition")) {
                    ArenaDataSetters.setQuitPosition(id, player.getLocation());
                    String msgOr = MessageManager.getMessageYml().getString("Command.Set.Quit");
                    String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                } else if (setType.equalsIgnoreCase("lobby") || setType.equalsIgnoreCase("LobbyPosition")) {
                    ArenaDataSetters.setLobbyPosition(id, player.getLocation());
                    String msgOr = MessageManager.getMessageYml().getString("Command.Set.Lobby");
                    String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                } else if (setType.equalsIgnoreCase("Spectate") || setType.equalsIgnoreCase("SpectatePosition")) {
                    ArenaDataSetters.setSpectatePosition(id, player.getLocation());
                    String msgOr = MessageManager.getMessageYml().getString("Command.Set.Spectate");
                    String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                } else if (setType.equalsIgnoreCase("regen") || setType.equalsIgnoreCase("RegenArea")) {
                    if (Main.plugin.playerCache.contains("Players." + player.getUniqueId().toString() + ".Pos.Loc2") &&
                            Main.plugin.playerCache.contains("Players." + player.getUniqueId().toString() + ".Pos.Loc1")) {
                        UUID uuid = player.getUniqueId();
                        String worldName1 = Main.plugin.playerCache.getString("Players." + uuid + ".Pos.Loc1.world");
                        Integer x1 = Main.plugin.playerCache.getInt("Players." + uuid + ".Pos.Loc1.x");
                        Integer y1 = Main.plugin.playerCache.getInt("Players." + uuid + ".Pos.Loc1.y");
                        Integer z1 = Main.plugin.playerCache.getInt("Players." + uuid + ".Pos.Loc1.z");
                        World world1 = Bukkit.getWorld(worldName1);
                        String worldName2 = Main.plugin.playerCache.getString("Players." + uuid + ".Pos.Loc2.world");
                        Integer x2 = Main.plugin.playerCache.getInt("Players." + uuid + ".Pos.Loc2.x");
                        Integer y2 = Main.plugin.playerCache.getInt("Players." + uuid + ".Pos.Loc2.y");
                        Integer z2 = Main.plugin.playerCache.getInt("Players." + uuid + ".Pos.Loc2.z");
                        World world2 = Bukkit.getWorld(worldName2);
                        Location loc1 = new Location(world1, x1, y1, z1);
                        Location loc2 = new Location(world2, x2, y2, z2);
                        ArenaDataSetters.setRegenArea(id, loc1, loc2);
                        Main.plugin.playerCache.set("Players." + uuid + ".Pos", null);
                        Main.plugin.saveCustomConfig(Main.plugin.playerCache, Main.plugin.playerCacheFile);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Regen");
                        String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } else {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Command.Set.Loc.Need"));
                    }
                } else if (setType.equalsIgnoreCase("world") || setType.equalsIgnoreCase("arenaWorld")) {
                    ArenaDataSetters.setWorld(id, player.getWorld());
                    String msgOr = MessageManager.getMessageYml().getString("Command.Set.World");
                    String msg = msgOr.replaceAll("%world%", player.getWorld().getName()).replaceAll("%id%", String.valueOf(id));
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                } else {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Few"));
                }
            } else if (args.length == 4) {
                String setType = args[2];
                String value = args[3];
                if (setType.equalsIgnoreCase("end") || setType.equalsIgnoreCase("EndPosition")) {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                } else if (setType.equalsIgnoreCase("quit") || setType.equalsIgnoreCase("QuitPosition")) {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                } else if (setType.equalsIgnoreCase("lobby") || setType.equalsIgnoreCase("LobbyPosition")) {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                } else if (setType.equalsIgnoreCase("regen") || setType.equalsIgnoreCase("RegenPosition")) {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                } else if (setType.equalsIgnoreCase("Name") || setType.equalsIgnoreCase("ArenaName")
                        || setType.equalsIgnoreCase("DisplayName")) {
                    ArenaDataSetters.setName(id, value);
                    String msgOr = MessageManager.getMessageYml().getString("Command.Set.Name");
                    String msg = msgOr.replaceAll("%id%", String.valueOf(id)).replaceAll("%name%", value);
                    player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                } else if (setType.equalsIgnoreCase("minPlayers")) {
                    try {
                        Integer count = Integer.valueOf(value);
                        ArenaDataSetters.setMinPlayers(id, count);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.MinPlayers");
                        String msg = msgOr.replaceAll("%count%", String.valueOf(count)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.MinPlayers"));
                    }
                } else if (setType.equalsIgnoreCase("maxPlayers")) {
                    try {
                        Integer count = Integer.valueOf(value);
                        ArenaDataSetters.setMaxPlayers(id, count);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.MaxPlayers");
                        String msg = msgOr.replaceAll("%count%", String.valueOf(count)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.MaxPlayers"));
                    }
                } else if (setType.equalsIgnoreCase("Wait") || setType.equalsIgnoreCase("WaitTime")
                        || setType.equalsIgnoreCase("WaitDelay")) {
                    try {
                        Integer time = Integer.valueOf(value);
                        ArenaDataSetters.setWaitDelay(id, time);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Time.Wait");
                        String msg = msgOr.replaceAll("%time%", String.valueOf(time)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Time"));
                    }
                } else if (setType.equalsIgnoreCase("Start") || setType.equalsIgnoreCase("StartTime")
                        || setType.equalsIgnoreCase("StartDelay")) {
                    try {
                        Integer time = Integer.valueOf(value);
                        ArenaDataSetters.setStartDelay(id, time);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Time.Start");
                        String msg = msgOr.replaceAll("%time%", String.valueOf(time)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Time"));
                    }
                } else if (setType.equalsIgnoreCase("GameLength") || setType.equalsIgnoreCase("GameTime")
                        || setType.equalsIgnoreCase("Timer")) {
                    try {
                        Integer time = Integer.valueOf(value);
                        ArenaDataSetters.setGameLength(id, time);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Time.Game");
                        String msg = msgOr.replaceAll("%time%", String.valueOf(time)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Time"));
                    }
                } else if (setType.equalsIgnoreCase("SafeTime") || setType.equalsIgnoreCase("Safe")) {
                    try {
                        Integer time = Integer.valueOf(value);
                        ArenaDataSetters.setSafeTime(id, time);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Time.Safe");
                        String msg = msgOr.replaceAll("%time%", String.valueOf(time)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Time"));
                    }
                } else if (setType.equalsIgnoreCase("DeathMatch") || setType.equalsIgnoreCase("DeathMatchTime")
                        || setType.equalsIgnoreCase("TieEnd") || setType.equalsIgnoreCase("TieEndTime")
                        || setType.equalsIgnoreCase("DeathMatchLength") || setType.equalsIgnoreCase("TieEndLength")) {
                    try {
                        Integer time = Integer.valueOf(value);
                        ArenaDataSetters.setTieEndLength(id, time);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Time.DeathMatch");
                        String msg = msgOr.replaceAll("%time%", String.valueOf(time)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Time"));
                    }
                } else if (setType.equalsIgnoreCase("Spawn")) {
                    try {
                        Integer spawnNumber = Integer.valueOf(value);
                        ArenaDataSetters.setSpawn(id, player.getLocation(), spawnNumber);
                        String msgOr = MessageManager.getMessageYml().getString("Command.Set.Spawn");
                        String msg = msgOr.replaceAll("%number%", String.valueOf(spawnNumber)).replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                    } catch (NumberFormatException e) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Spawn"));
                    }
                } else {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Invalid"));
                }
            } else {
                player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
            }
        }
    }
}