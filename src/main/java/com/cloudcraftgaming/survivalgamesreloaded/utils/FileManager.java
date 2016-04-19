package com.cloudcraftgaming.survivalgamesreloaded.utils;

import com.cloudcraftgaming.survivalgamesreloaded.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.List;

/**
 * Created by Nova Fox on 1/26/2016.
 */
public class FileManager {
    public static void createConfig() {
        File file = new File(Main.plugin.getDataFolder() + "/config.yml");
        if (!(file.exists())) {
            Main.plugin.getLogger().info("Generating config.yml...");
            Main.plugin.getConfig().addDefault("DO NOT DELETE", "SurvivalGamesReloaded is developed and managed by Shades161.");
            Main.plugin.getConfig().addDefault("Config Version", Main.plugin.conVersion);
            Main.plugin.getConfig().addDefault("Check for Updates", false);
            Main.plugin.getConfig().addDefault("Language", "En");
            Main.plugin.getConfig().addDefault("Console.Verbose", true);

            Main.plugin.getConfig().addDefault("Chat.PerGame", true);
            Main.plugin.getConfig().addDefault("Chat.Prefix", "&4[&2SG_%ArenaName%&4]");
            Main.plugin.getConfig().addDefault("Chat.PerWorldChatPlus.CompatibilityMode", true);

            Main.plugin.getConfig().addDefault("Arena.Regen.Queue.Wait", 2);
            Main.plugin.getConfig().addDefault("Arena.Regen.Queue.Blocks", 2000);

            List<String> blockedCommands = Main.plugin.getConfig().getStringList("Arena.BlockedCommands");
            blockedCommands.add("warp");
            blockedCommands.add("spawn");
            blockedCommands.add("hub");
            blockedCommands.add("tpa");
            Main.plugin.getConfig().set("Arena.BlockedCommands", blockedCommands);

            Main.plugin.getConfig().addDefault("Arena.Items.Chest.Min", 0);
            Main.plugin.getConfig().addDefault("Arena.Items.Chest.Max", 8);

            List<String> itemList = Main.plugin.getConfig().getStringList("Arena.Items.Possible");
            itemList.add(Material.APPLE.name());
            itemList.add(Material.GOLDEN_APPLE.name());
            itemList.add(Material.BREAD.name());
            itemList.add(Material.BAKED_POTATO.name());
            itemList.add(Material.POISONOUS_POTATO.name());
            itemList.add(Material.COOKED_BEEF.name());
            itemList.add(Material.COOKED_CHICKEN.name());
            itemList.add(Material.COOKED_FISH.name());
            itemList.add(Material.COOKED_MUTTON.name());
            itemList.add(Material.MUSHROOM_SOUP.name());
            itemList.add(Material.CARROT.name());
            itemList.add(Material.ARROW.name());
            itemList.add(Material.BOW.name());
            itemList.add(Material.WOOD_SWORD.name());
            itemList.add(Material.STONE_SWORD.name());
            itemList.add(Material.GOLD_SWORD.name());
            itemList.add(Material.IRON_SWORD.name());
            itemList.add(Material.BOAT.name());
            itemList.add(Material.FLINT_AND_STEEL.name());
            itemList.add(Material.CHAINMAIL_HELMET.name());
            itemList.add(Material.CHAINMAIL_CHESTPLATE.name());
            itemList.add(Material.CHAINMAIL_LEGGINGS.name());
            itemList.add(Material.CHAINMAIL_BOOTS.name());
            itemList.add(Material.LEATHER_HELMET.name());
            itemList.add(Material.LEATHER_CHESTPLATE.name());
            itemList.add(Material.LEATHER_LEGGINGS.name());
            itemList.add(Material.LEATHER_BOOTS.name());
            itemList.add(Material.GOLD_HELMET.name());
            itemList.add(Material.GOLD_CHESTPLATE.name());
            itemList.add(Material.GOLD_LEGGINGS.name());
            itemList.add(Material.GOLD_BOOTS.name());
            itemList.add(Material.IRON_HELMET.name());
            itemList.add(Material.IRON_CHESTPLATE.name());
            itemList.add(Material.IRON_LEGGINGS.name());
            itemList.add(Material.IRON_BOOTS.name());

            Main.plugin.getConfig().set("Arena.Items.Possible", itemList);

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();

            Main.plugin.getConfig().options().copyDefaults(true);
            Main.plugin.saveConfig();
        }
    }
    public static void createPlayerCacheFile() {
        if (!(Main.plugin.playerCacheFile.exists())) {
            Main.plugin.getLogger().info("Generating playerCache.yml...");

            Main.plugin.playerCache.addDefault("DO NOT DELETE", "SurvivalGamesReloaded is developed and managed by Shades161");

            Main.plugin.playerCache.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(Main.plugin.playerCache, Main.plugin.playerCacheFile);

            Main.plugin.playerCache.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(Main.plugin.playerCache, Main.plugin.playerCacheFile);
        }
    }
    public static void createItemSettingsFile() {
        File file = new File(Main.plugin.getDataFolder() + "/itemSettings.yml");
        if (!(file.exists())) {
            YamlConfiguration settings = YamlConfiguration.loadConfiguration(file);
            Main.plugin.getLogger().info("Generating itemSettings.yml...");
            Main.plugin.getConfig().addDefault("DO NOT DELETE", "SurvivalGamesReloaded is developed and managed by Shades161.");

            settings.addDefault("Items." + Material.APPLE.name() + ".Chance", 45);
            settings.addDefault("Items." + Material.APPLE.name() + ".Amount.Max", 3);
            settings.addDefault("Items." + Material.GOLDEN_APPLE.name() + ".Chance", 5);
            settings.addDefault("Items." + Material.GOLDEN_APPLE.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.BREAD.name() + ".Chance", 45);
            settings.addDefault("Items." + Material.BREAD.name() + ".Amount.Max", 4);
            settings.addDefault("Items." + Material.BAKED_POTATO.name() + ".Chance", 45);
            settings.addDefault("Items." + Material.BAKED_POTATO.name() + ".Amount.Max", 3);
            settings.addDefault("Items." + Material.POISONOUS_POTATO.name() + ".Chance", 45);
            settings.addDefault("Items." + Material.POISONOUS_POTATO.name() + ".Amount.Max", 6);
            settings.addDefault("Items." + Material.COOKED_BEEF.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.COOKED_BEEF.name() + ".Amount.Max", 2);
            settings.addDefault("Items." + Material.COOKED_CHICKEN.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.COOKED_CHICKEN.name() + ".Amount.Max", 2);
            settings.addDefault("Items." + Material.COOKED_FISH.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.COOKED_FISH.name() + ".Amount.Max", 2);
            settings.addDefault("Items." + Material.COOKED_MUTTON.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.COOKED_MUTTON.name() + ".Amount.Max", 2);
            settings.addDefault("Items." + Material.MUSHROOM_SOUP.name() + ".Chance", 65);
            settings.addDefault("Items." + Material.MUSHROOM_SOUP.name() + ".Amount.Max", 5);
            settings.addDefault("Items." + Material.CARROT.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.CARROT.name() + ".Amount.Max", 3);
            settings.addDefault("Items." + Material.ARROW.name() + ".Chance", 35);
            settings.addDefault("Items." + Material.ARROW.name() + ".Amount.Max", 10);
            settings.addDefault("Items." + Material.BOW.name() + ".Chance", 15);
            settings.addDefault("Items." + Material.BOW.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.WOOD_SWORD.name() + ".Chance", 45);
            settings.addDefault("Items." + Material.WOOD_SWORD.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.STONE_SWORD.name() + ".Chance", 35);
            settings.addDefault("Items." + Material.STONE_SWORD.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.GOLD_SWORD.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.GOLD_SWORD.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.IRON_SWORD.name() + ".Chance", 30);
            settings.addDefault("Items." + Material.IRON_SWORD.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.BOAT.name() + ".Chance", 30);
            settings.addDefault("Items." + Material.BOAT.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.FLINT_AND_STEEL.name() + ".Chance", 20);
            settings.addDefault("Items." + Material.FLINT_AND_STEEL.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.CHAINMAIL_HELMET.name() + ".Chance", 30);
            settings.addDefault("Items." + Material.CHAINMAIL_HELMET.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.CHAINMAIL_CHESTPLATE.name() + ".Chance", 30);
            settings.addDefault("Items." + Material.CHAINMAIL_CHESTPLATE.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.CHAINMAIL_LEGGINGS.name() + ".Chance", 30);
            settings.addDefault("Items." + Material.CHAINMAIL_LEGGINGS.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.CHAINMAIL_BOOTS.name() + ".Chance", 30);
            settings.addDefault("Items." + Material.CHAINMAIL_BOOTS.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.LEATHER_HELMET.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.LEATHER_HELMET.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.LEATHER_CHESTPLATE.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.LEATHER_CHESTPLATE.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.LEATHER_LEGGINGS.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.LEATHER_LEGGINGS.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.LEATHER_BOOTS.name() + ".Chance", 40);
            settings.addDefault("Items." + Material.LEATHER_BOOTS.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.GOLD_HELMET.name() + ".Chance", 20);
            settings.addDefault("Items." + Material.GOLD_HELMET.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.GOLD_CHESTPLATE.name() + ".Chance", 20);
            settings.addDefault("Items." + Material.GOLD_CHESTPLATE.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.GOLD_LEGGINGS.name() + ".Chance", 20);
            settings.addDefault("Items." + Material.GOLD_LEGGINGS.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.GOLD_BOOTS.name() + ".Chance", 20);
            settings.addDefault("Items." + Material.GOLD_BOOTS.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.IRON_HELMET.name() + ".Chance", 10);
            settings.addDefault("Items." + Material.IRON_HELMET.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.IRON_CHESTPLATE.name() + ".Chance", 10);
            settings.addDefault("Items." + Material.IRON_CHESTPLATE.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.IRON_LEGGINGS.name() + ".Chance", 10);
            settings.addDefault("Items." + Material.IRON_LEGGINGS.name() + ".Amount.Max", 1);
            settings.addDefault("Items." + Material.IRON_BOOTS.name() + ".Chance", 10);
            settings.addDefault("Items." + Material.IRON_BOOTS.name() + ".Amount.Max", 1);
            settings.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(settings, file);

            settings.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(settings, file);
        }
    }
    public static void createPluginCacheFile() {
        if (!(Main.plugin.pluginCacheFile.exists())) {
            Main.plugin.getLogger().info("Generating pluginCache.yml...");

            Main.plugin.pluginCache.addDefault("DO NOT DELETE", "SurvivalGamesReloaded is developed and managed by Shades161");
            Main.plugin.pluginCache.addDefault("DoNotEdit.NextId", 1);

            Main.plugin.pluginCache.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(Main.plugin.pluginCache, Main.plugin.pluginCacheFile);

            Main.plugin.pluginCache.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(Main.plugin.pluginCache, Main.plugin.pluginCacheFile);
        }
    }
    public static void checkFileVersions() {
        if (!(Main.plugin.getConfig().getDouble("Config Version") == Main.plugin.conVersion)) {
            Main.plugin.getLogger().severe("Config.yml Outdated! Please stop the server and delete the config.yml" +
                    "Then restart the server and set the settings to your preferred settings!");
            Main.plugin.getLogger().info("Disabling plugin to prevent further errors...");
            Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
        } else if (!(MessageManager.getMessageYml().getDouble("Message Version") == Main.plugin.messageVersion)) {
            Main.plugin.getLogger().severe("Your message files are outdated! Please delete them and restart the server!");
            Main.plugin.getLogger().info("Disabling plugin to prevent further errors...");
            Main.plugin.getServer().getPluginManager().disablePlugin(Main.plugin);
        }
    }
    public static File getItemSettingsFile() {
        return new File(Main.plugin.getDataFolder() + "/itemSettings.yml");
    }
    public static YamlConfiguration getItemSettingsYml() {
        File file = getItemSettingsFile();
        return YamlConfiguration.loadConfiguration(file);
    }
}