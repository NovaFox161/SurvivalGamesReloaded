package com.cloudcraftgaming.survivalgamesreloaded.listeners;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.Arena;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.GameMessages;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.ArenaStatus;
import com.cloudcraftgaming.survivalgamesreloaded.utils.GameBoardManager;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class PlayerDeathListener implements Listener {
    Main plugin;
    public PlayerDeathListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (ArenaManager.getManager().isInGame(player)) {
            Arena arena = ArenaManager.getManager().getArena(player);
            if (arena.getStatus().equals(ArenaStatus.INGAME)) {
                if (!(player.getKiller() == null)) {
                    if (player.getKiller() instanceof Player) {
                        event.setDeathMessage(null);
                        Player killer = player.getKiller();
                        arena.setDeaths(player, arena.getDeaths(player) + 1);
                        arena.setKills(killer, arena.getKills(killer) + 1);
                        GameBoardManager.setScore(killer, arena.getId());
                        String killedMsgOr = MessageManager.getMessageYml().getString("Player.Death.Killed");
                        String killedMsg = killedMsgOr.replaceAll("%killer%", killer.getDisplayName());
                        String killerMsgOr = MessageManager.getMessageYml().getString("Player.Death.Killer");
                        String killerMsg = killerMsgOr.replaceAll("%player%", player.getDisplayName());
                        player.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', killedMsg));
                        killer.sendMessage(MessageManager.getPrefix() + ChatColor.translateAlternateColorCodes('&', killerMsg));
                        GameMessages.announcePlayerKilled(arena.getId(), player, killer);
                    }
                }
                GameMessages.announcePlayerOut(arena.getId(), player);
            }
        }
    }
}
