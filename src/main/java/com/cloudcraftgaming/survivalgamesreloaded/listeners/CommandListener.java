package com.cloudcraftgaming.survivalgamesreloaded.listeners;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class CommandListener implements Listener {
    Main plugin;
    public CommandListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerCommand(PlayerCommandPreprocessEvent event) {
        if (!(event.isCancelled())) {
            Player player = event.getPlayer();
            if (ArenaManager.getManager().isInGame(player) || ArenaManager.getManager().isSpectating(player)) {
                String command = event.getMessage();
                for (String blockedCommand : plugin.getConfig().getStringList("Arena.BlockedCommands")) {
                    if (command.startsWith(blockedCommand) || command.contains(blockedCommand)) {
                        event.setCancelled(true);
                        event.setMessage("/");
                        player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Notifications.BlockedCommand"));
                    }
                }
            }
        }
    }
}
