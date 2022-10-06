package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.WarpConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("useWarpTeleportCommand")));
        } else {
            if (ISpawn.config.getBoolean("warpPermission")) {
                if (player.hasPermission("ispawn.usewarp")) {
                    if (WarpConfig.config.get(args[0]) != null) {
                        World world = Bukkit.getWorld(WarpConfig.config.getString(args[0] + ".World"));
                        double x = WarpConfig.config.getDouble(args[0] + ".X");
                        double y = WarpConfig.config.getDouble(args[0] + ".Y");
                        double z = WarpConfig.config.getDouble(args[0] + ".Z");
                        float yaw = (float) WarpConfig.config.getDouble(args[0] + ".Yaw");
                        float pitch = (float) WarpConfig.config.getDouble(args[0] + ".Pitch");
                        Location warp = new Location(world, x, y, z, yaw, pitch);
                        player.teleport(warp);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("teleportetToWarp").replace("%warp%", args[0])));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("warpNotFound").replace("%warp%", args[0])));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("noRights")));
                }
            } else {
                if (WarpConfig.config.get(args[0]) != null) {
                    World world = Bukkit.getWorld(WarpConfig.config.getString(args[0] + ".World"));
                    double x = WarpConfig.config.getDouble(args[0] + ".X");
                    double y = WarpConfig.config.getDouble(args[0] + ".Y");
                    double z = WarpConfig.config.getDouble(args[0] + ".Z");
                    float yaw = (float) WarpConfig.config.getDouble(args[0] + ".Yaw");
                    float pitch = (float) WarpConfig.config.getDouble(args[0] + ".Pitch");
                    Location warp = new Location(world, x, y, z, yaw, pitch);
                    player.teleport(warp);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("teleportetToWarp").replace("%warp%", args[0])));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("warpNotFound").replace("%warp%", args[0])));
                }
            }
        }
        return true;
    }
}
