package com.cloudcraftgaming.survivalgamesreloaded.listeners;

import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.UpdateChecker;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class JoinListener implements Listener {
    Main plugin;
    public JoinListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void notifyUpdate(PlayerJoinEvent event) {
        if (plugin.getConfig().getString("Check for Updates").equalsIgnoreCase("True")) {
            Player player = event.getPlayer();
            if (player.hasPermission("SGR.notify.update")) {
                plugin.updateChecker = new UpdateChecker(plugin, "http://dev.bukkit.org/bukkit-plugins/survivalgames-reloaded/files.rss");
                if (plugin.updateChecker.UpdateNeeded()) {
                    player.sendMessage("A new update for SurvivalGamesReloaded is available! Version: " + plugin.updateChecker.getVersion());
                    player.sendMessage("Download now at: " + plugin.updateChecker.getLink());
                }
            }
        }
    }
    @EventHandler(priority = EventPriority.MONITOR)
    public void updatePlayerCache(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (player.hasPermission("SGR.use.command.set")) {
            plugin.playerCache.set("Players." + player.getUniqueId().toString() + ".ArenaTool.Enabled", false);
            plugin.saveCustomConfig(plugin.playerCache, plugin.playerCacheFile);
        }
    }
}
