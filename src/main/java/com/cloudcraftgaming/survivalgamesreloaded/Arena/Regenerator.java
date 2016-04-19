package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.getters.ArenaDataGetters;
import com.cloudcraftgaming.survivalgamesreloaded.utils.ArenaStatus;
import com.cloudcraftgaming.survivalgamesreloaded.utils.Cuboid;
import com.cloudcraftgaming.survivalgamesreloaded.utils.ItemManager;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nova Fox on 3/16/2016.
 * Website: www.cloudcraftgaming.com
 */
public class Regenerator {
    public static void regenArena(int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                Main.plugin.getLogger().info("Starting regeneration for arena Id: " + String.valueOf(id));
                Main.plugin.getLogger().info("This may take some time depending on the arena size...");
            }
            Arena arena = ArenaManager.getManager().getArena(id);
            arena.setJoinable(false);
            arena.setStatus(ArenaStatus.REGENERATING);

            Cuboid regenArea = ArenaDataGetters.getRegenArea(id);

            clearGroundItems(id);
            regenChests(regenArea.getBlocks(), id);

            ArenaManager.getManager().reloadArena(id);
        }
    }
    private static void regenChests(List<Block> blocks, int id) {
        if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("Starting chest regen for arena Id: " + String.valueOf(id));
        }
        for (Block block : blocks) {
            if (block.getType().equals(Material.CHEST)) {
                Chest chest = (Chest) block.getState();
                if (!(chest.getInventory() == null)) {
                    chest.getInventory().clear();
                    ArrayList<ItemStack> items = ItemManager.getItemsForChest();
                    Integer itemSlot = 0;
                    for (ItemStack item : items) {
                        chest.getInventory().setItem(itemSlot, item);
                        itemSlot = itemSlot + 1;
                    }
                }
            }
        }
        if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("Successfully regenerated chests for arena Id: " + String.valueOf(id));
        }
    }
    private static void clearGroundItems(int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                Main.plugin.getLogger().info("Clearing ground items (regenerating) for arena Id: " + String.valueOf(id) + "...");
            }
            Cuboid regenArea = ArenaDataGetters.getRegenArea(id);
            Arena arena = ArenaManager.getManager().getArena(id);
            World world = arena.getWorld();
            List<Entity> entList = world.getEntities();

            for (Entity entity : entList) {
                if (entity instanceof Item) {
                    if (regenArea.contains(entity.getLocation())) {
                        entity.remove();
                    }
                }
            }
            if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                Main.plugin.getLogger().info("Successfully cleared all items for arena Id: " + String.valueOf(id));
            }
        }
    }
}