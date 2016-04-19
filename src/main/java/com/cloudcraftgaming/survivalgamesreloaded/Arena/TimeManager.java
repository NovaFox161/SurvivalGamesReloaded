package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.Main;
import com.cloudcraftgaming.survivalgamesreloaded.getters.ArenaDataGetters;
import com.cloudcraftgaming.survivalgamesreloaded.utils.ArenaStatus;
import org.bukkit.Bukkit;

import java.util.Random;

/**
 * Created by Nova Fox on 3/14/2016.
 * Website: www.cloudcraftgaming.com
 */
public class TimeManager {
    private static TimeManager tm;

    private TimeManager() {} //Prevent initialization.

    public static TimeManager getManager() {
        if (tm == null) {
            tm = new TimeManager();
            return tm;
        } else {
            return tm;
        }
    }

    public void startWaitDelay(final int id) {
        if (ArenaManager.getManager().arenaExists(id))  {
            Arena arena = ArenaManager.getManager().getArena(id);
            Random rn = new Random();
            final Integer waitId = rn.nextInt(99999998) + 1;
            arena.setWaitId(waitId);
            arena.setJoinable(true);
            arena.setStatus(ArenaStatus.WAITING_FOR_PLAYERS);
            Integer waitDelay = ArenaDataGetters.getWaitDelay(id);
            GameMessages.announceWaitDelay(id);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (ArenaManager.getManager().arenaExists(id)) {
                        Arena arena = ArenaManager.getManager().getArena(id);
                        if (arena.getWaitId().equals(waitId)) {
                            startStartDelay(id);
                        }
                    }
                }
            }, 20L * waitDelay);
        }
    }
    public void cancelWaitDelay(int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            arena.setWaitId(0);
            arena.setJoinable(true);
            arena.setStatus(ArenaStatus.WAITING_FOR_PLAYERS);
            GameMessages.announceWaitCancel(id);
        }
    }
    public void startStartDelay(final int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            Random rn = new Random();
            final Integer startId = rn.nextInt(99999998) + 1;
            arena.setStartId(startId);
            arena.setJoinable(false);
            arena.setStatus(ArenaStatus.STARTING);
            Integer startDelay = ArenaDataGetters.getStartDelay(id);
            GameMessages.announceStartDelay(id);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (ArenaManager.getManager().arenaExists(id)) {
                        Arena arena = ArenaManager.getManager().getArena(id);
                        if (arena.getStartId().equals(startId)) {
                            GameManager.startGame(id);
                        }
                    }
                }
            }, 20L * startDelay);
        }
    }
    public void cancelStartDelay(int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            arena.setStartId(0);
            arena.setJoinable(true);
            arena.setStatus(ArenaStatus.WAITING_FOR_PLAYERS);
            GameMessages.announceStartCancel(id);
        }
    }
    public void startGameTimer(final int id) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            Random rn = new Random();
            final Integer gameId = rn.nextInt(99999998) + 1;
            arena.setGameId(gameId);
            Integer gameLength = ArenaDataGetters.getGameLength(id);
            startCountdownToDeathMatch(id, gameId);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                @Override
                public void run() {
                    if (ArenaManager.getManager().arenaExists(id)) {
                        Arena arena = ArenaManager.getManager().getArena(id);
                        if (arena.getGameId().equals(gameId)) {
                            GameManager.endGame(id);
                        }
                    }
                }
            }, 20L * 60 * gameLength);
        }
    }
    public void startStartCountdown(final int id, final Integer gameId, final Integer timeLeft) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            if (arena.getGameId().equals(gameId)) {
                GameMessages.announceSecondsUntilStart(id, timeLeft);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (ArenaManager.getManager().arenaExists(id)) {
                            Arena arena = ArenaManager.getManager().getArena(id);
                            if (arena.getGameId().equals(gameId)) {
                                if (timeLeft > 0) {
                                    startStartCountdown(id, gameId, timeLeft - 1);
                                } else {
                                    arena.setMovingAllowed(true);
                                    GameMessages.announceGameStart(id);
                                    startSafeTime(id, gameId);
                                }
                            }
                        }
                    }
                }, 20L);
            }
        }
    }
    public void startSafeTime(final int id, final Integer gameId) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            if (arena.getGameId().equals(gameId)) {
                Integer safeTime = ArenaDataGetters.getSafeTime(id);
                if (safeTime > 0) {
                    GameMessages.announceSafeTimeStart(id);
                    arena.setSafeTime(true);
                    Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                        @Override
                        public void run() {
                            if (ArenaManager.getManager().arenaExists(id)) {
                                Arena arena = ArenaManager.getManager().getArena(id);
                                if (arena.getGameId().equals(gameId)) {
                                    arena.setSafeTime(false);
                                    GameMessages.announceSafeTimeEnd(id);
                                }
                            }
                        }
                    }, 20L * 60 * safeTime);
                }
            }
        }
    }
    public void startCountdownToDeathMatch(final int id, final Integer gameId) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            if (arena.getGameId().equals(gameId)) {
                Integer gameTime = ArenaDataGetters.getGameLength(id);
                Integer deathMatchTime = ArenaDataGetters.getTieEndLength(id);
                Integer timeUntilDeathMatch = gameTime - deathMatchTime;
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (ArenaManager.getManager().arenaExists(id)) {
                            Arena arena = ArenaManager.getManager().getArena(id);
                            if (arena.getGameId().equals(gameId)) {
                                startDeathMatch(id, gameId);
                            }
                        }
                    }
                }, 20L * 60 * timeUntilDeathMatch);
            }
        }
    }
    public void startDeathMatch(final int id, final Integer gameId) {
        if (ArenaManager.getManager().arenaExists(id)) {
            Arena arena = ArenaManager.getManager().getArena(id);
            if (arena.getGameId().equals(gameId)) {
                Integer deathMatchTime = ArenaDataGetters.getTieEndLength(id);
                GameMessages.announceDeathMatch(id);
                GameManager.startDeathMatch(id);
                Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                    @Override
                    public void run() {
                        if (ArenaManager.getManager().arenaExists(id)) {
                            Arena arena = ArenaManager.getManager().getArena(id);
                            if (arena.getGameId().equals(id)) {
                                GameManager.endGame(id);
                            }
                        }
                    }
                }, 20L * 60 * deathMatchTime);
            }
        }
    }
}