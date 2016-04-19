package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.getters.ArenaDataGetters;
import com.cloudcraftgaming.survivalgamesreloaded.utils.ArenaStatus;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nova Fox on 3/13/2016.
 * Website: www.cloudcraftgaming.com
 */
public class ArenaManager {
    private static ArenaManager am;

    private final List<Arena> arenas = new ArrayList<>();

    private ArenaManager() {} //Prevent initialization.

    public static ArenaManager getManager() {
        if (am == null) {
            am = new ArenaManager();
        }
        return am;
    }
    public void loadArena(int id) {
        if (!(this.arenaExists(id))) {
            if (ArenaFileManager.arenaExists(id)) {
                int minPlayers = ArenaDataGetters.getMinPlayers(id);
                int maxPlayers = ArenaDataGetters.getMaxPlayers(id);
                World world = ArenaDataGetters.getWorld(id);
                Location lobby = ArenaDataGetters.getLobbyPosition(id);
                Location end = ArenaDataGetters.getEndPosition(id);
                Location quit = ArenaDataGetters.getQuitPosition(id);
                Location spectate = ArenaDataGetters.getSpectatePosition(id);
                Arena arena = new Arena(id, minPlayers, maxPlayers, world, lobby, end, quit, spectate);
                this.arenas.add(arena);
                this.addSpawnsToArena(id);
                arena.setWaitId(0);
                arena.setStartId(0);
                arena.setGameId(0);
                arena.setStatus(ArenaStatus.EMPTY);
                arena.setPlayerCount(0);
                arena.setMovingAllowed(true);
                arena.setJoinable(true);
                if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                    Main.plugin.getLogger().info("Successfully loaded arena Id: " + String.valueOf(id));
                }
            }
        }
    }
    public void safeLoadArena(int id) {
        if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
            Main.plugin.getLogger().info("Safe enabling arena Id: " + String.valueOf(id));
        }
        if (ArenaFileManager.canEnableArena(id)) {
            this.loadArena(id);
            Regenerator.regenArena(id);
        } else {
            if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                Main.plugin.getLogger().info("Unable to enable arena Id: " + String.valueOf(id) + ", because it is missing a needed setting!");
            }
        }
    }
    public void reloadArena(int id) {
        if (this.arenaExists(id)) {
            this.unloadArena(id);
            this.loadArena(id);
        }
    }
    public void unloadArena(int id) {
        if (this.arenaExists(id)) {
            Arena arena = this.getArena(id);
            if (arena.getStatus().equals(ArenaStatus.INGAME)) {
                GameManager.endGame(id);
            } else {
                PlayerHandler.removeAllPlayers(id);
            }
            this.arenas.remove(arena);
            if (Main.plugin.getConfig().getString("Console.Verbose").equalsIgnoreCase("True")) {
                Main.plugin.getLogger().info("Successfully unloaded arena Id: " + String.valueOf(id));
            }
        }
    }
    public Arena getArena(int i) {
        for (Arena a : this.arenas) {
            if (a.getId() == i) {
                return a;
            }
        }
        return null;
    }
    public Arena getArena(Player player) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(player.getUniqueId())) {
                return a;
            }
        }
        return null;
    }
    public Arena getSpectatingArena(Player player) {
        for (Arena a : this.arenas) {
            if (a.getSpectators().contains(player.getUniqueId())) {
                return a;
            }
        }
        return null;
    }
    public boolean arenaExists(int i) {
        for (Arena a : this.arenas) {
            if (a.getId() == i) {
                return true;
            }
        }
        return false;
    }
    public boolean isInGame(Player player) {
        for (Arena a : this.arenas) {
            if (a.getPlayers().contains(player.getUniqueId())) {
                return true;
            }
        }
        return false;
    }
    public boolean isSpectating(Player player) {
        for (Arena a : this.arenas) {
            if (a.getSpectators().contains(player.getUniqueId())) {
                return true;
            }
        }
        return false;
    }
    public void checkPlayerCount(int id) { //Checks if arena needs the game to end or start on join/quit.
        Arena arena = this.getArena(id);
        if (arena.getStatus().equals(ArenaStatus.EMPTY)) {
            if (arena.getPlayerCount() > 0) {
                arena.setStatus(ArenaStatus.WAITING_FOR_PLAYERS);
                this.checkPlayerCount(id); //Check again to see if anything else needs to be done.
            }
        } else if (arena.getStatus().equals(ArenaStatus.WAITING_FOR_PLAYERS)) {
            if (arena.getPlayerCount() == 0 || arena.getPlayerCount() < 0) {
                this.reloadArena(id); //Arena empty. Reset without regen.
            } else if (arena.getPlayerCount() == arena.getMinPlayers()) {
                TimeManager.getManager().startWaitDelay(id); //Start wait delay
            } else if (arena.getPlayerCount() < arena.getMinPlayers()) {
                if (!(arena.getWaitId() == 0)) { //Check to see if it is waiting or not. (To avoid spam on join)
                    TimeManager.getManager().cancelWaitDelay(id); //Not enough players. Cancel player wait.
                }
            }
        } else if (arena.getStatus().equals(ArenaStatus.STARTING)) {
            if (arena.getPlayerCount() == 0) {
                this.reloadArena(id); //Arena empty. Game not started. Reset without Regen.
            } else if (arena.getPlayerCount() < arena.getMinPlayers()) {
                TimeManager.getManager().cancelStartDelay(id); //Not enough players, cancel start.
                arena.setJoinable(true);
                arena.setStatus(ArenaStatus.WAITING_FOR_PLAYERS);
            }
        } else if (arena.getStatus().equals(ArenaStatus.INGAME)) {
            if (arena.getPlayerCount() == 1) {
                GameManager.endGame(id); //End game, declare last player winner.
            } else if (arena.getPlayerCount() <= 0) {
                GameManager.endGame(id); //End game, arena empty (should not reach this point), regen, reset.
            }
        }
    }
    public void addSpawnsToArena(int id) {
        if (this.arenaExists(id)) {
            Arena arena = this.getArena(id);
            ArrayList<Location> spawns = ArenaDataGetters.getSpawns(id);
            for (Location spawn : spawns) {
                arena.addSpawn(spawn, false);
            }
        }
    }
}