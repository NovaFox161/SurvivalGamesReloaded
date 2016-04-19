package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.getters.ArenaDataGetters;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Nova Fox on 3/13/2016.
 * Website: www.cloudcraftgaming.com
 */
public class GameMessages {
    public static void announcePlayerJoin(int id, Player player) {
        String msgOr = MessageManager.getMessageYml().getString("Arena.PlayerJoin");
        String msgRep = msgOr.replaceAll("%player%", player.getDisplayName());
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announcePlayerQuit(int id, Player player) {
        String msgOr = MessageManager.getMessageYml().getString("Arena.PlayerQuit");
        String msgRep = msgOr.replaceAll("%player%", player.getDisplayName());
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announcePlayerOut(int id, Player player) {
        String msgOr = MessageManager.getMessageYml().getString("Arena.PlayerOut");
        String msgRep = msgOr.replaceAll("%player%", player.getDisplayName());
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announcePlayerKilled(int id, Player player, Player killer) {
        String msgOr = MessageManager.getMessageYml().getString("Arena.PlayerKilled");
        String msgRep = msgOr.replaceAll("%player%", player.getDisplayName()).replaceAll("%killer%", killer.getDisplayName());
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announceWaitDelay(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        Integer waitDelay = ArenaDataGetters.getWaitDelay(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.Waiting");
        String msgRep = msgOr.replace("%time%", String.valueOf(waitDelay));
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announceWaitCancel(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Arena.WaitCancelled"));
        }
    }
    public static void announceStartDelay(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        Integer startDelay = ArenaDataGetters.getStartDelay(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.Starting");
        String msgRep = msgOr.replaceAll("%time%", String.valueOf(startDelay));
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announceStartCancel(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Arena.StartCancelled"));
        }
    }
    public static void announceSecondsUntilStart(int id, int secondsLeft) {
        Arena arena = ArenaManager.getManager().getArena(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.StartCountdown");
        String msgRep = msgOr.replaceAll("%time%", String.valueOf(secondsLeft));
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announceGameStart(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.GameStart");
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgOr));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgOr));
        }
    }
    public static void announceWinner(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        if (arena.getWinType().equalsIgnoreCase("Single")) {
            String msgOr = MessageManager.getMessageYml().getString("Arena.Win.Single");
            String msgRep = msgOr.replaceAll("%player%", arena.getWinner());
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
            }
            for (UUID uuid : arena.getSpectators()) {
                Player p = Bukkit.getPlayer(uuid);
                p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
            }
        } else if (arena.getWinType().equalsIgnoreCase("Tie")) {
            String msgOr = MessageManager.getMessageYml().getString("Arena.Win.Tie");
            String msgRep = msgOr.replaceAll("%players%", arena.getWinner());
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
            }
            for (UUID uuid : arena.getSpectators()) {
                Player p = Bukkit.getPlayer(uuid);
                p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
            }
        } else if (arena.getWinType().equalsIgnoreCase("None")) {
            String msg = MessageManager.getMessage("Arena.Win.None");
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                p.sendMessage(MessageManager.getPrefix() + msg);
            }
            for (UUID uuid : arena.getSpectators()) {
                Player p = Bukkit.getPlayer(uuid);
                p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
            }
        }
    }
    public static void announceSafeTimeStart(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.SafeTime.Start");
        Integer safeTime = ArenaDataGetters.getSafeTime(id);
        String msgRep = msgOr.replaceAll("%time%", String.valueOf(safeTime));
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announceSafeTimeEnd(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.SafeTime.End");
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgOr));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgOr));
        }
    }
    public static void announceDeathMatch(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.DeathMatch");
        Integer deathMatchTime = ArenaDataGetters.getTieEndLength(id);
        String msgRep = msgOr.replaceAll("%time$", String.valueOf(deathMatchTime));
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msgRep));
        }
    }
    public static void announcePlayerSpectating(Player player, int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        String msgOr = MessageManager.getMessageYml().getString("Arena.Spectating");
        String msg = msgOr.replaceAll("%player%", player.getDisplayName());
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        }
        for (UUID uuid : arena.getSpectators()) {
            Player p = Bukkit.getPlayer(uuid);
            p.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', msg));
        }
    }
}