package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.WarpConfig;
import de.immaxxx.ispawn.config.WarpParticle;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class SetWarpCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("useWarpCommand")));
        } else {
            if (player.hasPermission("ispawn.setwarp")) {
                if (WarpConfig.config.getKeys(false).size() == 54){
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("maxWarps")));
                    return true;
                }

                if (WarpConfig.config.get(args[0]) == null) {

                    Material material = null;

                    if (args.length > 1 && Arrays.stream(Material.values()).anyMatch(material1 -> material1.name().equalsIgnoreCase(args[1]))) {
                        Material.valueOf(args[1].toUpperCase());
                        material = Material.valueOf(args[1].toUpperCase());
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noMaterialFound")));
                    }

                    WarpConfig.config.set(args[0] + ".World", player.getWorld().getName());
                    WarpConfig.config.set(args[0] + ".X", player.getLocation().getX());
                    WarpConfig.config.set(args[0] + ".Y", player.getLocation().getY());
                    WarpConfig.config.set(args[0] + ".Z", player.getLocation().getZ());
                    WarpConfig.config.set(args[0] + ".Pitch", player.getLocation().getPitch());
                    WarpConfig.config.set(args[0] + ".Yaw", player.getLocation().getYaw());

                    if (material != null){
                        WarpConfig.config.set(args[0] + ".Material", material.name().toUpperCase());
                    }

                    try {
                        WarpConfig.config.save(WarpConfig.configfile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    WarpParticle.load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("createdWarp").replace("%warp%", args[0])));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpExists").replace("%warp%", args[0])));
                }
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noRights")));
            }
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 2) {
            ArrayList<String> materials = new ArrayList<>();
            for (Material material : Material.values()) {
                materials.add(material.name().toUpperCase());
            }
            return materials;
        }
        return null;
    }
}
