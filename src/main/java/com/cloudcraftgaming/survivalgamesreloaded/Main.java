package com.cloudcraftgaming.survivalgamesreloaded;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.Arena;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.GameManager;
import com.cloudcraftgaming.survivalgamesreloaded.commands.BaseCommand;
import com.cloudcraftgaming.survivalgamesreloaded.listeners.*;
import com.cloudcraftgaming.survivalgamesreloaded.utils.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

/**
 * Created by Nova Fox on 1/24/2016.
 * Website: www.cloudcraftgaming.com
 * For Project: SurvivalGamesReloaded.
 */
public class Main extends JavaPlugin {
    public static Main plugin;
    public UpdateChecker updateChecker;

    public Double conVersion = 1.0;
    public Double messageVersion = 1.0;

    public File playerCacheFile = new File(getDataFolder() + "/Cache/playerCache.yml");
    public YamlConfiguration playerCache = YamlConfiguration.loadConfiguration(playerCacheFile);
    public File pluginCacheFile = new File(getDataFolder() + "/Cache/pluginCache.yml");
    public YamlConfiguration pluginCache = YamlConfiguration.loadConfiguration(pluginCacheFile);
    public boolean hasStartedArenas;

    public void onDisable() {
        //Safely end all games and unload arenas.
        if (pluginCache.contains("Arenas.Enabled")) {
            for (String idString : playerCache.getStringList("Arenas.Enabled")) {
                Integer id = Integer.valueOf(idString);
                if (ArenaManager.getManager().arenaExists(id)) {
                    Arena arena = ArenaManager.getManager().getArena(id);
                    if (arena.getStatus().equals(ArenaStatus.INGAME)) {
                        GameManager.endGame(id);
                    } else {
                        ArenaManager.getManager().unloadArena(id);
                    }
                }
            }
        }
    }
    public void onEnable() {
        plugin = this;

        getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        getServer().getPluginManager().registerEvents(new BlockPlaceListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new SignChangeListener(this), this);
        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathListener(this), this);
        getServer().getPluginManager().registerEvents(new PlayerDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new RespawnListener(this), this);
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getCommand("survivalgames").setExecutor(new BaseCommand(this));
        getCommand("sg").setExecutor(new BaseCommand(this));

        FileManager.createConfig();
        FileManager.createItemSettingsFile();
        FileManager.createPluginCacheFile();
        FileManager.createPlayerCacheFile();
        MessageManager.generateEnglishMessages();

        FileManager.checkFileVersions();

        PluginChecker.checkForPerWorldChatPlus();
        hasStartedArenas = false;

        if (getConfig().getString("Check for Updates").equalsIgnoreCase("True")) {
            this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/bukkit-plugins/survivalgames-reloaded/files.rss");
            if (this.updateChecker.UpdateNeeded()) {
                getLogger().info("A new update for SurvivalGamesReloaded is available! Version: " + updateChecker.getVersion());
                getLogger().info("Download now at: " + updateChecker.getLink());
            }
        }
        loadArenasStartUp();
    }
    public void saveCustomConfig(FileConfiguration ymlConfig, File ymlFile) {
        try {
            ymlConfig.save(ymlFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadArenasStartUp() {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (pluginCache.contains("Arenas.Enabled")) {
                    if (getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                        getLogger().info("Loading all enabled arenas...");
                    }
                    for (String idString : pluginCache.getStringList("Arenas.Enabled")) {
                        Integer id = Integer.valueOf(idString);
                        if (!(ArenaManager.getManager().arenaExists(id))) {
                            ArenaManager.getManager().safeLoadArena(id);
                        }
                    }
                    if (getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                        getLogger().info("All enabled arenas loaded!");
                    }
                }
            }
        }, 20L * 5);
    }
}