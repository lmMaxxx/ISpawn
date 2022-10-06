package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.WarpConfig;
import de.immaxxx.ispawn.config.WarpParticle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("useWarpCommand")));
        } else {
            if (player.hasPermission("ispawn.setwarp")) {
                if (WarpConfig.config.get(args[0]) == null) {
                    WarpConfig.config.set(args[0] + ".World", player.getWorld().getName());
                    WarpConfig.config.set(args[0] + ".X", player.getLocation().getX());
                    WarpConfig.config.set(args[0] + ".Y", player.getLocation().getY());
                    WarpConfig.config.set(args[0] + ".Z", player.getLocation().getZ());
                    WarpConfig.config.set(args[0] + ".Pitch", player.getLocation().getPitch());
                    WarpConfig.config.set(args[0] + ".Yaw", player.getLocation().getYaw());
                    try {
                        WarpConfig.config.save(WarpConfig.configfile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    WarpParticle.load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("createdWarp").replace("%warp%", args[0])));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("warpExists").replace("%warp%", args[0])));
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("noRights")));
            }
        }

        return true;
    }
}
