package com.cloudcraftgaming.survivalgamesreloaded.utils;

import com.cloudcraftgaming.survivalgamesreloaded.Main;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Nova Fox on 3/18/2016.
 * Website: www.cloudcraftgaming.com
 */
public class ItemManager {
    public static ArrayList<ItemStack> getItemsForChest() {
        ArrayList<ItemStack> itemsForChest = new ArrayList<>();
        Integer itemsInChest = itemInChest();
        if (itemsInChest > 0) {
            for (int i = 0; i < itemsInChest + 1; i++) {
                Material itemMat = getNextItem();
                ItemStack item = getItem(itemMat);
                if (!(itemsForChest.contains(item))) {
                    itemsForChest.add(item);
                }
            }
        }
        return itemsForChest;
    }
    private static Material getNextItem() {
        List<String> itemNames = Main.plugin.getConfig().getStringList("Arena.Items.Possible");
        Collections.shuffle(itemNames);
        String itemName = itemNames.get(itemNames.size() - 1);
        return Material.getMaterial(itemName);
    }
    private static Integer itemInChest() {
        Integer min = Main.plugin.getConfig().getInt("Arena.Items.Chest.Min");
        Integer max = Main.plugin.getConfig().getInt("Arena.Items.Chest.Max");
        Random rn = new Random();
        return rn.nextInt((max - min) + 1) + min;
    }
    private static ItemStack getItem(Material itemMat) {
        YamlConfiguration settings = FileManager.getItemSettingsYml();
        if (settings.contains("Items." + itemMat.name())) {
            Integer chance = settings.getInt("Items." + itemMat.name() + ".Chance");
            Random rn = new Random();
            Integer ran = rn.nextInt(100 + 1);
            if (chance <= ran) {
                Integer amount = getItemAmount(itemMat);
                if (amount > 0) {
                    return new ItemStack(itemMat, amount);
                } else {
                    return  new ItemStack(Material.AIR, 1);
                }
            } else {
                return new ItemStack(Material.AIR, 1);
            }
        } else {
            return new ItemStack(Material.AIR, 1);
        }
    }

    private static Integer getItemAmount(Material itemMat) {
        YamlConfiguration settings = FileManager.getItemSettingsYml();
        if (settings.contains("Items." + itemMat.name())) {
            Integer max = settings.getInt("Items." + itemMat.name() + ".Amount.Max");
            Random rn = new Random();
            return rn.nextInt(max + 1);
        } else {
            return 0;
        }
    }
}
