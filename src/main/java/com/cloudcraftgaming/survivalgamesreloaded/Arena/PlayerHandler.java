package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.getters.ArenaDataGetters;
import com.cloudcraftgaming.survivalgamesreloaded.utils.ArenaStatus;
import com.cloudcraftgaming.survivalgamesreloaded.utils.GameBoardManager;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/**
 * Created by Nova Fox on 3/13/2016.
 * Website: www.cloudcraftgaming.com
 */
public class PlayerHandler {

    public static void joinArena(Player player, int id) {
        if (!(ArenaManager.getManager().isInGame(player))) {
            if (ArenaManager.getManager().arenaExists(id)) {
                Arena arena = ArenaManager.getManager().getArena(id);
                if (arena.isJoinable() && arena.getPlayerCount() < arena.getMaxPlayers()) {
                    if (arena.getStatus().equals(ArenaStatus.WAITING_FOR_PLAYERS) || arena.getStatus().equals(ArenaStatus.EMPTY)) {
                        if (inventoryEmpty(player)) {
                            arena.setPlayerCount(arena.getPlayerCount() + 1);
                            arena.getPlayers().add(player.getUniqueId());
                            player.teleport(arena.getLobbyLocation());
                            GameMessages.announcePlayerJoin(id, player);
                            ArenaManager.getManager().checkPlayerCount(id);
                        } else {
                            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Player.InvNotEmpty"));
                        }
                    } else {
                       if (arena.getStatus().equals(ArenaStatus.INGAME)) {
                           player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Arena.InGame"));
                       } else if (arena.getStatus().equals(ArenaStatus.REGENERATING)) {
                           player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Arena.Regenerating"));
                       } else {
                           player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.Error"));
                       }
                    }
                } else {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.NotJoinable"));
                }
            } else {
                player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.ArenaDoesNotExist"));
            }
        } else {
            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Player.AlreadyInGame"));
        }
    }
    public static void quitArena(Player player) {
        if (ArenaManager.getManager().isInGame(player)) {
            Arena arena = ArenaManager.getManager().getArena(player);
            player.setExhaustion(20f);
            player.getInventory().clear();
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setFireTicks(0);
            player.teleport(arena.getQuitLocation());
            GameBoardManager.removePlayer(player, arena.getId(), "Player");
            arena.getPlayers().remove(player.getUniqueId());
            arena.setPlayerCount(arena.getPlayerCount() - 1);
            GameMessages.announcePlayerQuit(arena.getId(), player);
            ArenaManager.getManager().checkPlayerCount(arena.getId());
        } else if (ArenaManager.getManager().isSpectating(player)) {
            Arena arena = ArenaManager.getManager().getSpectatingArena(player);
            arena.getSpectators().remove(player.getUniqueId());
            player.setGameMode(GameMode.SURVIVAL);
            player.teleport(arena.getQuitLocation());
            GameBoardManager.removePlayer(player, arena.getId(), "Spectator");
            String msgOr = MessageManager.getMessageYml().getString("Player.Spectate.Stop");
            String msg = msgOr.replaceAll("%id%", String.valueOf(arena.getId()));
            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        } else {
            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Player.NotInGame"));
        }
    }
    public static void playerEliminated(Player player) {
        if (ArenaManager.getManager().isInGame(player)) {
            Arena arena = ArenaManager.getManager().getArena(player);
            player.setExhaustion(20f);
            player.getInventory().clear();
            player.setFoodLevel(20);
            player.setHealth(20);
            player.setFireTicks(0);
            player.teleport(arena.getEndLocation());
            arena.getPlayers().remove(player.getUniqueId());
            arena.setPlayerCount(arena.getPlayerCount() - 1);
            GameBoardManager.removePlayer(player, arena.getId(), "Player");
            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Player.Out"));
            ArenaManager.getManager().checkPlayerCount(arena.getId());
        }
    }
    public static void removeAllPlayers(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        if (arena.getPlayerCount() > 0) {
            GameBoardManager.removePlayers(id);
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                p.setExhaustion(20f);
                p.getInventory().clear();
                p.setFoodLevel(20);
                p.setHealth(20);
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(arena.getQuitLocation());
            }
            for (UUID uuid : arena.getSpectators()) {
                Player p = Bukkit.getPlayer(uuid);
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(arena.getQuitLocation());
            }
        }
    }
    public static void spectateArena(Player player, int id) {
        if (!(ArenaManager.getManager().isInGame(player))) {
            if (!(ArenaManager.getManager().isSpectating(player))) {
                if (ArenaManager.getManager().arenaExists(id)) {
                    Arena arena = ArenaManager.getManager().getArena(id);
                    if (arena.getStatus().equals(ArenaStatus.INGAME)) {
                        player.teleport(arena.getSpectateLocation());
                        arena.getSpectators().add(player.getUniqueId());
                        player.setGameMode(GameMode.SPECTATOR);
                        GameBoardManager.addPlayer(player, id, "Spectator");
                        String msgOr = MessageManager.getMessageYml().getString("Player.Spectate.Spectate");
                        String msg = msgOr.replaceAll("%id%", String.valueOf(id));
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
                        GameMessages.announcePlayerSpectating(player, id);
                    } else {
                        String msgOr = MessageManager.getMessageYml().getString("Player.Spectate.Cannot");
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgOr));
                    }
                } else {
                    player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.ArenaDoesNotExist"));
                }
            } else {
                String msgOr = MessageManager.getMessageYml().getString("Player.Spectate.Already");
                player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgOr));
            }
        } else {
            String msgOr = MessageManager.getMessageYml().getString("Player.Spectate.InGame");
            player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgOr));
        }
    }
    public static boolean inventoryEmpty(Player player) {
        for (ItemStack itemStack : player.getInventory().getArmorContents()) {
            if (!(itemStack == null)) {
                if (!(itemStack.getType().equals(Material.AIR))) {
                    return false;
                }
            }
        }
        for (ItemStack itemStack : player.getInventory().getContents()) {
            if (!(itemStack == null)) {
                if (!(itemStack.getType().equals(Material.AIR))) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void sendArenaInfo(Player player, int id) {
        if (ArenaFileManager.arenaExists(id)) {
            ArenaStatus status = ArenaStatus.EMPTY;
            String players = "";
            Integer playerCount = 0;
            Boolean enabled = false;
            Integer max = ArenaDataGetters.getMaxPlayers(id);
            if (ArenaManager.getManager().arenaExists(id)) {
                Arena arena = ArenaManager.getManager().getArena(id);
                playerCount = arena.getPlayerCount();
                if (arena.getPlayerCount() > 0) {
                    for (UUID uuid : arena.getPlayers()) {
                        Player p = Bukkit.getPlayer(uuid);
                        players = players + p.getDisplayName() + ", " + ChatColor.RESET;
                    }
                }
                status = arena.getStatus();
                enabled = true;
            }
            String headingOr = MessageManager.getMessageYml().getString("Sign.Info.Heading");
            String heading = headingOr.replaceAll("%id%", String.valueOf(id));
            String statusString = String.valueOf(status).replaceAll("_", " ").toLowerCase();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', heading));
            player.sendMessage(ChatColor.GREEN + "Enabled: " + ChatColor.RED + String.valueOf(enabled));
            player.sendMessage(ChatColor.GREEN + "Status: " + ChatColor.RED + statusString);
            player.sendMessage(ChatColor.GREEN + "Player Count: " + ChatColor.RED + String.valueOf(playerCount) + "/" + String.valueOf(max));
            player.sendMessage(ChatColor.GREEN + "Players: " + ChatColor.RED + players);
        } else {
            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.ArenaDoesNotExist"));
        }
    }
}