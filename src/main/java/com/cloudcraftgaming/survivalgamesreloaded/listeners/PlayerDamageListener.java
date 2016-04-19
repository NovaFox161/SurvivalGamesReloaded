package com.cloudcraftgaming.survivalgamesreloaded.listeners;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.Arena;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class PlayerDamageListener implements Listener {
    Main plugin;
    public PlayerDamageListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player) {
            Player player = (Player)event.getEntity();
            if (ArenaManager.getManager().isInGame(player)) {
                if (event.getDamager() instanceof Player) {
                    Player damager = (Player)event.getDamager();
                    Arena arena = ArenaManager.getManager().getArena(player);
                    if (arena.isSafeTime()) {
                        event.setCancelled(true);
                        damager.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Player.Damage.Damager"));
                        damager.setLastDamage(1.0);
                    }
                }
            }
        }
    }
}
