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
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WarpCommand implements TabExecutor {
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (ISpawn.config.getBoolean("warpPermission")) {
            if (sender.hasPermission("ispawn.usewarp")) {
                if(args.length == 1) {
                    ArrayList<String> completions = new ArrayList<>();
                    ArrayList<String> warpNames = new ArrayList<>();

                    for(String warpName : WarpConfig.config.getKeys(false)) {
                        warpNames.add(warpName);
                    }

                    StringUtil.copyPartialMatches(args[0], warpNames, completions);

                    Collections.sort(completions);
                    return completions;
                }
            }
        } else {
            if(args.length == 1) {
                ArrayList<String> completions = new ArrayList<>();
                ArrayList<String> warpNames = new ArrayList<>();

                for(String warpName : WarpConfig.config.getKeys(false)) {
                    warpNames.add(warpName);
                }

                StringUtil.copyPartialMatches(args[0], warpNames, completions);

                Collections.sort(completions);
                return completions;
            }
        }
        return new ArrayList<>();
    }

}
