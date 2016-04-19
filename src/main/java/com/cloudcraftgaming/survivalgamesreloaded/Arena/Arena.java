package com.cloudcraftgaming.survivalgamesreloaded.Arena;

import com.cloudcraftgaming.survivalgamesreloaded.utils.ArenaStatus;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Created by Nova Fox on 3/13/2016.
 * Website: www.cloudcraftgaming.com
 *
 */

public class Arena {
    private final int id;
    private final List<UUID> players = new ArrayList<>();
    private final List<UUID> spectators = new ArrayList<>();
    private final HashMap<Location, Boolean> spawns = new HashMap<>();
    private final HashMap<UUID, Integer> kills = new HashMap<>();
    private final HashMap<UUID, Integer> deaths = new HashMap<>();
    private final int minPlayers;
    private final int maxPlayers;
    private final World world;
    private final Location lobbyLocation;
    private final Location endLocation;
    private final Location quitLocation;
    private final Location spectateLocation;


    private ArenaStatus status;
    private boolean joinable;
    private Integer playerCount;
    private Integer waitId;
    private Integer startId;
    private Integer gameId;
    private boolean movingAllowed;
    private boolean safeTime;
    private String winType;
    private String winner;
    private Scoreboard scoreboard;


    public Arena(int id, int minPlayers, int maxPlayers, World world, Location lobby, Location end, Location quit, Location spectate) {
        this.id = id;
        this.minPlayers = minPlayers;
        this.maxPlayers = maxPlayers;
        this.world = world;
        this.lobbyLocation = lobby;
        this.endLocation = end;
        this.quitLocation = quit;
        this.spectateLocation = spectate;
    }

    public void addSpawn(Location spawn, Boolean status) {
        this.spawns.put(spawn, status);
    }
    public void useSpawn(Location spawn) {
        this.spawns.remove(spawn);
        this.spawns.put(spawn, true);
    }

    //Setters
    public void setStatus(ArenaStatus status) {
        this.status = status;
    }
    public void setJoinable(Boolean value) {
        this.joinable = value;
    }
    public void setPlayerCount(Integer count) {
        this.playerCount = count;
    }
    public void setWaitId(Integer i) {
        this.waitId = i;
    }
    public void setStartId(Integer i) {
        this.startId = i;
    }
    public void setGameId(Integer i) {
        this.gameId = i;
    }
    public void setMovingAllowed(Boolean value) {
        this.movingAllowed = value;
    }
    public void setSafeTime(Boolean value) {
        this.safeTime = value;
    }
    public void setWinType(String type) {
        this.winType = type;
    }
    public void setWinner(String winner) {
        this.winner = winner;
    }
    public void setKills(Player player, Integer kills) {
        this.kills.remove(player.getUniqueId());
        this.kills.put(player.getUniqueId(), kills);
    }
    public void setDeaths(Player player, Integer deaths) {
        this.deaths.remove(player.getUniqueId());
        this.deaths.put(player.getUniqueId(), deaths);
    }
    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    //Getters
    public int getId() {
        return this.id;
    }
    public List<UUID> getPlayers() {
        return this.players;
    }
    public List<UUID> getSpectators() {
        return this.spectators;
    }
    public Integer getPlayerCount() {
        return this.playerCount;
    }
    public int getMinPlayers() {
        return this.minPlayers;
    }
    public int getMaxPlayers() {
        return this.maxPlayers;
    }
    public World getWorld() {
        return this.world;
    }
    public Location getLobbyLocation() {
        return this.lobbyLocation;
    }
    public Location getEndLocation() {
        return this.endLocation;
    }
    public Location getQuitLocation() {
        return this.quitLocation;
    }
    public Location getSpectateLocation() {
        return this.spectateLocation;
    }
    public Location getNextSpawn() {
        for (Location spawn : spawns.keySet()) {
            if (!(spawns.get(spawn))) {
                return spawn;
            }
        }
        return null;
    }
    public HashMap<Location, Boolean> getSpawns() {
        return this.spawns;
    }
    public ArenaStatus getStatus() {
        return this.status;
    }
    public boolean isJoinable() {
        return this.joinable;
    }
    public Integer getWaitId() {
        return this.waitId;
    }
    public Integer getStartId() {
        return this.startId;
    }
    public Integer getGameId() {
        return this.gameId;
    }
    public boolean isMovingAllowed() {
        return this.movingAllowed;
    }
    public boolean isSafeTime() {
        return this.safeTime;
    }
    public HashMap<UUID, Integer> getKills() {
        return this.kills;
    }
    public Integer getKills(Player player) {
        return this.kills.get(player.getUniqueId());
    }
    public HashMap<UUID, Integer> getDeaths() {
        return this.deaths;
    }
    public Integer getDeaths(Player player) {
        return this.deaths.get(player.getUniqueId());
    }
    public String getWinType() {
        return this.winType;
    }
    public String getWinner() {
        return this.winner;
    }
    public Scoreboard getScoreboard() {
        return this.scoreboard;
    }
}