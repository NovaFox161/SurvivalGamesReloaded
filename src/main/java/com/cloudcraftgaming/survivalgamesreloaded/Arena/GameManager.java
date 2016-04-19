package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.utils.ArenaStatus;
import com.cloudcraftgaming.survivalgamesreloaded.utils.GameBoardManager;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

/**
 * Created by Nova Fox on 3/14/2016.
 * Website: www.cloudcraftgaming.com
 */
public class GameManager {
    public static void startGame(int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            arena.setStatus(ArenaStatus.INGAME);
            arena.setJoinable(false);
            GameBoardManager.createScoreboard(id);
            GameBoardManager.addPlayers(id);
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                Location pSpawn = arena.getNextSpawn();
                arena.useSpawn(pSpawn);
                p.teleport(pSpawn);
                p.setGameMode(GameMode.SURVIVAL);
                p.getInventory().setHelmet(null);
                p.getInventory().setChestplate(null);
                p.getInventory().setLeggings(null);
                p.getInventory().setBoots(null);
                p.getInventory().clear();
                p.setExp(0);
                p.setLevel(0);
                p.setHealth(20);
                p.setFoodLevel(20);
                p.setFireTicks(0);
                p.setExhaustion(20);
                arena.setKills(p, 0);
                arena.setDeaths(p, 0);
            }
            GameBoardManager.updateBoards(id);
            arena.setMovingAllowed(false);
            TimeManager.getManager().startGameTimer(id);
            TimeManager.getManager().startStartCountdown(id, arena.getGameId(), 25);
        }
    }
    public static void endGame(int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            determineWinners(id);
            GameBoardManager.removePlayers(id);
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                p.setExhaustion(20f);
                p.getInventory().clear();
                p.setFoodLevel(20);
                p.setHealth(20);
                p.setFireTicks(0);
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(arena.getEndLocation());
            }
            for (UUID uuid : arena.getSpectators()) {
                Player p = Bukkit.getPlayer(uuid);
                p.setGameMode(GameMode.SURVIVAL);
                p.teleport(arena.getQuitLocation());
            }
            GameMessages.announceWinner(id);
            arena.getPlayers().clear();
            arena.setPlayerCount(0);
            Regenerator.regenArena(id);
        }
    }
    private static void determineWinners(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        Integer mostKills = 0;
        String winType = "None";
        String winners = "";
        for (UUID uuid : arena.getPlayers()) {
            Player p = Bukkit.getPlayer(uuid);
            Integer kills = arena.getKills().get(uuid);
            Integer deaths = arena.getDeaths().get(uuid);
            if (deaths < 1 || deaths == 0) {
                if (kills.equals(mostKills)) {
                    winType = "Tie";
                    winners = winners + " ," + p.getDisplayName();
                } else if (kills > mostKills) {
                    mostKills = kills;
                    winType = "Single";
                    winners = p.getDisplayName();
                }
            }
        }
        arena.setWinType(winType);
        arena.setWinner(winners);
    }
    public static void startDeathMatch(int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            arena.getSpawns().clear();
            ArenaManager.getManager().addSpawnsToArena(id);

            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                Location pSpawn = arena.getNextSpawn();
                arena.useSpawn(pSpawn);
                p.teleport(pSpawn);
            }
        }
    }
}