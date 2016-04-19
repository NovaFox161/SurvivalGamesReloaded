package com.cloudcraftgaming.survivalgamesreloaded.utils;

        import com.cloudcraftgaming.survivalgamesreloaded.Main;
        import org.bukkit.ChatColor;
        import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

/**
 * Created by Nova Fox on 1/26/2016.
 */
public class MessageManager {
    public static File getMessageFile() {
        String messageFileLang = Main.plugin.getConfig().getString("Language");
        return new File(Main.plugin.getDataFolder() + "/Messages/" + messageFileLang + ".yml");
    }
    public static YamlConfiguration getMessageYml() {
        File messageFile = getMessageFile();
        return (YamlConfiguration.loadConfiguration(messageFile));

    }
    public static void generateEnglishMessages() {
        File enFile = new File (Main.plugin.getDataFolder() + "/Messages/En.yml");
        FileConfiguration en = (YamlConfiguration.loadConfiguration(enFile));
        if (!(enFile.exists())) {
            Main.plugin.getLogger().info("Generating En.yml message file...");
            en.addDefault("DO NOT DELETE", "SurvivalGamesReloaded is developed and managed by Shades161");
            en.addDefault("Message Version", Main.plugin.messageVersion);

            en.addDefault("Prefix", "&5[SurvivalGames]");
            en.addDefault("Player.AlreadyInGame", "&4You cannot join more than 1 game!");
            en.addDefault("Player.NotInGame", "&4You can't quit any games because you aren't in one!");
            en.addDefault("Player.Spectate.InGame", "&4You cannot spectate a game while you are in game!");
            en.addDefault("Player.Spectate.Already", "&4You are already spectating a game!");
            en.addDefault("Player.Spectate.Cannot", "&4You cannot spectate that arena because it is not ingame!");
            en.addDefault("Player.Spectate.Spectate", "&2You are now spectating arena &6%id%&2! &3Use &6/sg quit &3to stop spectating!");
            en.addDefault("Player.Spectate.Stop", "&2You are no longer spectating arena &6%id%&2!");
            en.addDefault("Player.Out", "&4You have been eliminated from the game! Better luck next time!");
            en.addDefault("Player.InvNotEmpty", "&3Your inventory must be empty to join!");
            en.addDefault("Player.Rules.Block.Break", "&4You cannot break blocks while in the mini game!");
            en.addDefault("Player.Rules.Block.Place", "&4You cannot place blocks while in the mini game!");
            en.addDefault("Player.Death.Killed", "&4You were killed by &6%killer%&4!");
            en.addDefault("Player.Death.Killer", "&6You killed &4%player%&6!");
            en.addDefault("Player.Damage.Damager", "&4HEY! You cannot hurt other players while it is the safe time!");

            en.addDefault("Arena.InGame", "&4You cannot join that game while it is in game!");
            en.addDefault("Arena.Regenerating", "&4Arena regenerating! Please wait!");
            en.addDefault("Arena.PlayerJoin", "&2%player% &3has joined the mini game!");
            en.addDefault("Arena.PlayerQuit", "&2%player% &4has quit the mini game!");
            en.addDefault("Arena.PlayerOut", "&6%player% &4has been eliminated from the game!");
            en.addDefault("Arena.Spectating", "&6%player% &2is now spectating this game!");
            en.addDefault("Arena.PlayerKilled", "&4%player% has been killed by &6%killer%&4!");
            en.addDefault("Arena.Waiting", "&5Waiting for more players... &6Time: %time% seconds.");
            en.addDefault("Arena.WaitCancelled", "&4Wait delay cancelled! Not enough players to start game!");
            en.addDefault("Arena.Starting", "&5Starting game in &6%time% seconds&5!");
            en.addDefault("Arena.StartCancelled", "&4Game start cancelled! Not enough players to start game!");
            en.addDefault("Arena.StartCountdown", "&5Game starting in &6%time% seconds&5!");
            en.addDefault("Arena.GameStart", "&4Game started! Good luck tributes! And may the odds be ever in your favor!");
            en.addDefault("Arena.SafeTime.Start", "&4You have &6%time% minutes &4until you can kill other players!");
            en.addDefault("Arena.SafeTime.End", "&4Safe time over! &6Players can now kill you!");
            en.addDefault("Arena.DeathMatch", "&4Death match started! You have &6%time% minutes &4to kill remaining players!");
            en.addDefault("Arena.Win.Single", "&6%player% &5has won the Survival Games!");
            en.addDefault("Arena.Win.Tie", "&6%players% &5have tied the game! We dunno how, but they did!");
            en.addDefault("Arena.Win.None", "&6No one won the game!");

            en.addDefault("Command.Create", "&5Created new arena with Id: &6%id%");
            en.addDefault("Command.Tool.Enable", "&3Enabled arena tool!");
            en.addDefault("Command.Tool.Disable", "&3Disabled arena tool!");
            en.addDefault("Command.ReloadArena", "&3Reloaded arena &6%id%&3!");
            en.addDefault("Command.Set.Enable", "&3Enabled arena &6%id%&3!");
            en.addDefault("Command.Set.Disable", "&3Disabled arena &6%id%&3!");
            en.addDefault("Command.Set.End", "&3End position for arena &6%id% &3set!");
            en.addDefault("Command.Set.Quit", "&3Quit position for arena &6%id% &3set!");
            en.addDefault("Command.Set.Lobby", "&3Lobby position for arena &6%id% &3set!");
            en.addDefault("Command.Set.Spectate", "&3Spectating position for arena &6%id% &3set!");
            en.addDefault("Command.Set.Regen", "&3Regen area for arena &6%id% &3set!");
            en.addDefault("Command.Set.World", "&3World &6%world% &3for arena &6%id% set!");
            en.addDefault("Command.Set.Name", "&3Display name for arena &6%id% &3is now &6%name%&3!");
            en.addDefault("Command.Set.MinPlayers", "&3Minimum players for arena &6%id% &3set to &6%count%&3!");
            en.addDefault("Command.Set.MaxPlayers", "&3Maximum players for arena &6%id% &3set to &6%count%&3!");
            en.addDefault("Command.Set.Spawn", "&3Spawn Number &6%number% for arena &6%id% &3set!");
            en.addDefault("Command.Set.Time.Wait", "&3Wait delay for arena &6%id% &3set to &6%time% seconds&3!");
            en.addDefault("Command.Set.Time.Start", "&3Start delay for arena &6%id% &3set to &6%time% seconds&3!");
            en.addDefault("Command.Set.Time.Game", "&3Game length for arena &6%id% &3set to &6%time% minutes&3!");
            en.addDefault("Command.Set.Time.Safe", "&3Safe time for arena &6%id% &3set to &6%time% minutes&3!");
            en.addDefault("Command.Set.Time.DeathMatch", "&3Death match length for arena &6%id% &3set to &6%time% minutes&3!");
            en.addDefault("Command.Set.Loc.Loc1", "&3Location 1 set!");
            en.addDefault("Command.Set.Loc.Loc1Only", "&3Set location 2 (right click( and then finalize your selection!");
            en.addDefault("Command.Set.Loc.Loc2", "&3Location 2 set!");
            en.addDefault("Command.Set.Loc.Loc2Only", "&3Set location 1 (left click) and then finalize your selection!");
            en.addDefault("Command.Set.Loc.Both", "&3Locations 1 and 2 set! &6Use the correct command to finalize your selection!");
            en.addDefault("Command.Set.Loc.Need", "&4You need to set both positions 1 and 2 in order to set this!");

            en.addDefault("Sign.Place.Join", "&2Join sign placed! &3Right click to join, left click to view info!");
            en.addDefault("Sign.Place.Spectate", "&2Spectate sign placed! &3Right click to spectate the arena!");
            en.addDefault("Sign.Place.Quit", "&2Quit sign placed! &3Right click to quit the game you are in!");
            en.addDefault("Sign.Info.Heading", "&1-~- &6Arena %id% Info &1-~-");

            en.addDefault("Notifications.BlockedCommand", "&4You cannot use that command while in game! &2Use /sg quit to leave the game");
            en.addDefault("Notifications.ArenaDoesNotExist", "&4The specified arena does not exist or is not enabled!");
            en.addDefault("Notifications.AlreadyEnabled", "&4That arena is already enabled!");
            en.addDefault("Notifications.AlreadyDisabled", "&4That arena is already disabled!");
            en.addDefault("Notifications.NotJoinable", "&4The specified arena is currently not joinable!");
            en.addDefault("Notifications.Error", "&4There was some sort of error! Sorry!");
            en.addDefault("Notifications.Args.Few", "&4Too few arguments for this command!");
            en.addDefault("Notifications.Args.Many", "&4Too many arguments for this command!");
            en.addDefault("Notifications.Args.Invalid", "&4Invalid arguments for this command!");
            en.addDefault("Notifications.NoPerm", "&4You do not have permission to do that!");
            en.addDefault("Notifications.Int.Arena", "&4Arena Id must be a number!");
            en.addDefault("Notifications.Int.MinPlayers", "&4Minimum players must be a number!");
            en.addDefault("Notifications.Int.MaxPlayers", "&4Maximum players musts be a number!");
            en.addDefault("Notifications.Int.Time", "&4Time must be a number!");
            en.addDefault("Notifications.Int.Spawn", "&4Spawn value must be a number!");

            en.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(en, enFile);

            en.options().copyDefaults(true);
            Main.plugin.saveCustomConfig(en, enFile);
        }
    }
    public static String getPrefix() {
        String prefixOr = getMessageYml().getString("Prefix");
        return ChatColor.translateAlternateColorCodes('&', prefixOr) + " " + ChatColor.RESET;
    }
    public static String getMessage(String messageString) {
        String msgOr = getMessageYml().getString(messageString);
        return ChatColor.translateAlternateColorCodes('&', msgOr);
    }
}
