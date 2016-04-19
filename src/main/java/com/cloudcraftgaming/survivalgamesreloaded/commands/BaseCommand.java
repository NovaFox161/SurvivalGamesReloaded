package com.cloudcraftgaming.survivalgamesreloaded.commands;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaFileManager;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.PlayerHandler;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class BaseCommand implements CommandExecutor {
    Main plugin;
    public BaseCommand(Main instance) {
        plugin = instance;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("Survivalgames") || command.getName().equalsIgnoreCase("sg")) {
            if (sender.hasPermission("SGR.use.command")) {
                if (sender instanceof Player) {
                    Player player = (Player) sender;
                    if (args.length < 1) {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Few"));
                    } else if (args.length == 1) {
                        String type = args[0];
                        if (type.equalsIgnoreCase("Join")) {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Few"));
                        } else if (type.equalsIgnoreCase("Quit")) {
                            PlayerHandler.quitArena(player);
                        } else if (type.equalsIgnoreCase("Set")) {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Few"));
                        } else if (type.equalsIgnoreCase("create") || type.equalsIgnoreCase("CreateArena")) {
                            ArenaFileManager.createArena(sender);
                        } else if (type.equalsIgnoreCase("tool") || type.equalsIgnoreCase("ArenaTool")
                                || type.equalsIgnoreCase("regionTool")) {
                            if (player.hasPermission("SGR.use.command.set")) {
                                UUID uuid = player.getUniqueId();
                                if (plugin.playerCache.getString("Players." + uuid + ".ArenaTool.Enabled").equalsIgnoreCase("True")) {
                                    plugin.playerCache.set("Players." + uuid + ".ArenaTool.Enabled", false);
                                    plugin.saveCustomConfig(plugin.playerCache, plugin.playerCacheFile);
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Command.Tool.Disable"));
                                } else {
                                    plugin.playerCache.set("Players." + uuid + ".ArenaTool.Enabled", true);
                                    plugin.saveCustomConfig(plugin.playerCache, plugin.playerCacheFile);
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Command.Tool.Enable"));
                                }
                            } else {
                                player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.NoPerm"));
                            }
                        } else {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Invalid"));
                        }
                    } else if (args.length == 2) {
                        String type = args[0];
                        String arenaIdString = args[1];
                        try {
                            Integer arenaId = Integer.valueOf(arenaIdString);
                            if (ArenaFileManager.arenaExists(arenaId)) {
                                if (type.equalsIgnoreCase("Quit")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else if (type.equalsIgnoreCase("Join")) {
                                    PlayerHandler.joinArena(player, arenaId);
                                } else if (type.equalsIgnoreCase("Spectate")) {
                                    PlayerHandler.spectateArena(player, arenaId);
                                } else if (type.equalsIgnoreCase("info")) {
                                    PlayerHandler.sendArenaInfo(player, arenaId);
                                } else if (type.equalsIgnoreCase("Reload")) {
                                    if (player.hasPermission("SGR.use.command.set")) {
                                        if (ArenaManager.getManager().arenaExists(arenaId)) {
                                            ArenaManager.getManager().reloadArena(arenaId);
                                            String msgOr = MessageManager.getMessageYml().getString("Command.ReloadArena");
                                            String msg = msgOr.replaceAll("%id%", String.valueOf(arenaId));
                                            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                                        } else {
                                            String msgAd = MessageManager.getMessage("Notifications.ArenaDoesNotExist");
                                            player.sendMessage(MessageManager.getPrefix() + msgAd);
                                        }
                                    } else {
                                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.NoPerm"));
                                    }
                                } else if (type.equalsIgnoreCase("Set")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Few"));
                                } else if (type.equalsIgnoreCase("Create") || type.equalsIgnoreCase("CreateArena")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Invalid"));
                                }
                            } else {
                                player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.ArenaDoesNotExist"));
                                return false;
                            }
                        } catch (NumberFormatException e) {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Arena"));
                            return false;
                        }
                    } else if (args.length == 3) {
                        String type = args[0];
                        String arenaIdString = args[1];
                        try {
                            Integer arenaId = Integer.valueOf(arenaIdString);
                            if (ArenaFileManager.arenaExists(arenaId)) {
                                if (type.equalsIgnoreCase("Quit")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else if (type.equalsIgnoreCase("Join")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else if (type.equalsIgnoreCase("Set")) {
                                    SetCommand.setCommand(player, arenaId, args);
                                } else if (type.equalsIgnoreCase("Create") || type.equalsIgnoreCase("CreateArena")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Invalid"));
                                }
                            } else {
                                player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.ArenaDoesNotExist"));
                                return false;
                            }
                        } catch (NumberFormatException e) {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Arena"));
                            return false;
                        }
                    } else if (args.length == 4) {
                        String type = args[0];
                        String arenaIdString = args[1];
                        try {
                            Integer arenaId = Integer.valueOf(arenaIdString);
                            if (ArenaFileManager.arenaExists(arenaId)) {
                                if (type.equalsIgnoreCase("Quit")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else if (type.equalsIgnoreCase("Join")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else if (type.equalsIgnoreCase("Create") || type.equalsIgnoreCase("CreateArena")) {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Many"));
                                } else if (type.equalsIgnoreCase("Set")) {
                                    SetCommand.setCommand(player, arenaId, args);
                                } else {
                                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Invalid"));
                                }
                            } else {
                                player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.ArenaDoesNotExist"));
                                return false;
                            }
                        } catch (NumberFormatException e) {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Int.Arena"));
                            return false;
                        }
                    } else {
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Args.Invalid"));
                        return false;
                    }
                } else {
                    sender.sendMessage(MessageManager.getPrefix() + "Sorry, only players can use that command. " +
                            "Support for non-players coming soon!");
                    return false;
                }
            } else {
                sender.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.NoPerm"));
                return false;
            }
        }
        return false;
    }
}