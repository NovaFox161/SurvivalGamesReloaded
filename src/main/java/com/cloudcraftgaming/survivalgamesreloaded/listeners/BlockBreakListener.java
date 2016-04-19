package com.cloudcraftgaming.survivalgamesreloaded.listeners;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.utils.MessageManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class BlockBreakListener implements Listener {
    Main plugin;
    public BlockBreakListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (ArenaManager.getManager().isInGame(player)) {
            event.setCancelled(true);
            player.sendMessage(MessageManager.getPrefix() + MessageManager.getMessage("Player.Rules.Block.Break"));
        }
    }
}
