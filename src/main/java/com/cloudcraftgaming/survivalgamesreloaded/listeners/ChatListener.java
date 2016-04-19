package com.cloudcraftgaming.survivalgamesreloaded.listeners;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.Arena;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.getters.ArenaDataGetters;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.io.File;
import java.util.UUID;

/**
 * Created by Nova Fox on 3/19/2016.
 * Website: www.cloudcraftgaming.com
 */
public class ChatListener implements Listener {
    Main plugin;
    public ChatListener(Main instance) {
        plugin = instance;
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncPlayerChatEvent event) {
        if (!(event.isCancelled())) {
            Player player = event.getPlayer();
            UUID uuid = player.getUniqueId();
            if (ArenaManager.getManager().isInGame(player)) {
                Arena arena = ArenaManager.getManager().getArena(player);
                if (plugin.getConfig().getBoolean("Chat.PerGame")) {
                    if (plugin.getConfig().getString("Chat.PerWorldChatPlus.CompatibilityMode").equalsIgnoreCase("True")) {
                        File pwcDataFile = new File("plugins/PerWorldChatPlus/Data/data.yml");
                        FileConfiguration pwcData = YamlConfiguration.loadConfiguration(pwcDataFile);
                        if (pwcData.getString("Players." + uuid + ".Bypass").equalsIgnoreCase("False")) {
                            event.getRecipients().clear();
                            event.getRecipients().add(player);
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                UUID puuid = p.getUniqueId();
                                if (ArenaManager.getManager().isInGame(p)) {
                                    if (arena.getPlayers().contains(puuid)
                                            && !event.getRecipients().contains(p)) {
                                        event.getRecipients().add(p);
                                    }
                                } else {
                                    if (pwcData.getString("Players." + puuid + ".Spy").equalsIgnoreCase("True")
                                            && !event.getRecipients().contains(p)) {
                                        event.getRecipients().add(p);
                                    }
                                }
                            }
                            String prefixOr = plugin.getConfig().getString("Chat.Prefix");
                            String arenaName = ArenaDataGetters.getArenaName(arena.getId());
                            String prefix = prefixOr.replaceAll("%ArenaName%", arenaName) + ChatColor.RESET;
                            event.setFormat(ChatColor.translateAlternateColorCodes('&', prefix) + event.getFormat());
                            event.setMessage(event.getMessage());
                        }
                    } else {
                        event.getRecipients().clear();
                        event.getRecipients().add(player);
                        for (Player p : Bukkit.getOnlinePlayers()) {
                            if (ArenaManager.getManager().isInGame(p)) {
                                if (arena.getPlayers().contains(p.getUniqueId())) {
                                    event.getRecipients().add(p);
                                }
                            }
                        }
                        String prefixOr = plugin.getConfig().getString("Chat.Prefix");
                        String arenaName = ArenaDataGetters.getArenaName(arena.getId());
                        String prefix = prefixOr.replaceAll("%arenaName%", arenaName) + ChatColor.RESET;
                        event.setFormat(ChatColor.translateAlternateColorCodes('&', prefix) + event.getFormat());
                        event.setMessage(event.getMessage());
                    }
                }
            }
        }
    }
}
