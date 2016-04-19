package com.cloudcraftgaming.survivalgamesreloaded.listeners;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.Arena;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class PlayerMoveListener implements Listener {
    Main plugin;
    public PlayerMoveListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (ArenaManager.getManager().isInGame(player)) {
            Arena arena = ArenaManager.getManager().getArena(player);
            if (!(arena.isMovingAllowed())) {
                Location from = event.getFrom();
                Location to = event.getTo();
                if (!((to.getX() == from.getX()) && (to.getY() == from.getY()) && (to.getZ() == from.getZ()))) {
                    player.teleport(from);
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void setGameModeOnMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (ArenaManager.getManager().isInGame(player)) {
            if (!(player.getGameMode().equals(GameMode.SURVIVAL))) {
                player.setGameMode(GameMode.SURVIVAL);
            }
        } else if (ArenaManager.getManager().isSpectating(player)) {
            if (!(player.getGameMode().equals(GameMode.SPECTATOR))) {
                player.setGameMode(GameMode.SPECTATOR);
            }
        }
    }
}
