package com.cloudcraftgaming.survivalgamesreloaded.utils;

import com.cloudcraftgaming.survivalgamesreloaded.Arena.Arena;
import com.cloudcraftgaming.survivalgamesreloaded.Arena.ArenaManager;
import com.cloudcraftgaming.survivalgamesreloaded.getters.ArenaDataGetters;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.UUID;

/**
 * Created by Nova Fox on 3/23/2016.
 * Website: www.cloudcraftgaming.com
 */
public class GameBoardManager {
    private static org.bukkit.scoreboard.ScoreboardManager manager = Bukkit.getScoreboardManager();

    public static void createScoreboard(int id) {
        Scoreboard board = manager.getNewScoreboard();
        Objective boardObj = board.registerNewObjective("Board", "dummy");
        boardObj.setDisplayName(ArenaDataGetters.getArenaName(id));
        boardObj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Team players = board.registerNewTeam("players");
        players.setDisplayName(ChatColor.DARK_RED + "Tributes");
        players.setAllowFriendlyFire(true);
        Team spectators = board.registerNewTeam("spectators");
        spectators.setDisplayName(ChatColor.GOLD + "Spectators");
        spectators.setAllowFriendlyFire(false);
        Score specScoreFake = boardObj.getScore(ChatColor.GOLD + "Spectators");
        specScoreFake.setScore(600);
        Score playScoreFake = boardObj.getScore(ChatColor.RED + "Tributes");
        playScoreFake.setScore(300);
        Arena arena = ArenaManager.getManager().getArena(id);
        arena.setScoreboard(board);
    }
    public static void addPlayers(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        if (!(arena.getScoreboard() == null)) {
            for (UUID uuid : arena.getPlayers()) {
                Player p = Bukkit.getPlayer(uuid);
                Scoreboard gameBoard = arena.getScoreboard();
                Objective object = gameBoard.getObjective("Board");
                Team team = gameBoard.getTeam("players");
                team.addPlayer(p);
                Score pScore = object.getScore(ChatColor.RED + p.getName());
                pScore.setScore(0);
                arena.setScoreboard(gameBoard);
            }
            for (UUID uuid : arena.getSpectators()) {
                Player p = Bukkit.getPlayer(uuid);
                Scoreboard gameBoard = arena.getScoreboard();
                Objective object = gameBoard.getObjective("Board");
                Team team = gameBoard.getTeam("spectators");
                team.addPlayer(p);
                Score pScore = object.getScore(ChatColor.GOLD + p.getName());
                pScore.setScore(0);
                arena.setScoreboard(gameBoard);
            }
        }
    }
    public static void removePlayers(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            Player player = Bukkit.getPlayer(uuid);
            player.setScoreboard(manager.getNewScoreboard());
        }
        for (UUID uuid : arena.getSpectators()) {
            Player player = Bukkit.getPlayer(uuid);
            player.setScoreboard(manager.getNewScoreboard());
        }
    }
    public static void updateBoards(int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        for (UUID uuid : arena.getPlayers()) {
            if (!(arena.getScoreboard() == null)) {
                Player p = Bukkit.getPlayer(uuid);
                p.setScoreboard(arena.getScoreboard());
            }
        }
        for (UUID uuid : arena.getSpectators()) {
            if (!(arena.getScoreboard() == null)) {
                Player p = Bukkit.getPlayer(uuid);
                p.setScoreboard(arena.getScoreboard());
            }
        }
    }
    public static void addPlayer(Player player, int id, String type) {
        Arena arena = ArenaManager.getManager().getArena(id);
        Scoreboard board = arena.getScoreboard();
        if (!(board == null)) {
            Objective object = board.getObjective("Board");
            if (type.equalsIgnoreCase("Player")) {
                Team team = board.getTeam("players");
                team.addPlayer(player);
                Score pScore = object.getScore(ChatColor.RED + player.getName());
                pScore.setScore(0);
                arena.setScoreboard(board);
            } else if (type.equalsIgnoreCase("Spectator")) {
                Team team = board.getTeam("spectators");
                team.addPlayer(player);
                Score pScore = object.getScore(ChatColor.GOLD + player.getName());
                pScore.setScore(599);
                arena.setScoreboard(board);
            }
            updateBoards(id);
        }
    }
    public static void removePlayer(Player player, int id, String type) {
        Arena arena = ArenaManager.getManager().getArena(id);
        Scoreboard board = arena.getScoreboard();
        if (!(board == null)) {
            if (type.equalsIgnoreCase("Player")) {
                board.resetScores(ChatColor.RED + player.getName());
                Team team = board.getTeam("players");
                team.removePlayer(player);
                arena.setScoreboard(board);

            } else if (type.equalsIgnoreCase("Spectator")) {
                board.resetScores(ChatColor.GOLD + player.getName());
                Team team = board.getTeam("spectators");
                team.removePlayer(player);
                arena.setScoreboard(board);
            }
            updateBoards(id);
        }
        player.setScoreboard(manager.getNewScoreboard());
    }
    public static void setScore(Player player, int id) {
        Arena arena = ArenaManager.getManager().getArena(id);
        Scoreboard board = arena.getScoreboard();
        if (!(board == null)) {
            Objective object = board.getObjective("Board");
            Score score = object.getScore(ChatColor.RED + player.getName());
            score.setScore(arena.getKills(player));
            updateBoards(id);
        }
    }
}
