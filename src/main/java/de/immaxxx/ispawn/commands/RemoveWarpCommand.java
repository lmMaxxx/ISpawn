package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.WarpConfig;
import de.immaxxx.ispawn.config.WarpParticle;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RemoveWarpCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("ispawn.removewarp")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("useRemoveWarpCommand")));
            } else {
                if (WarpConfig.config.get(args[0]) != null) {

                    WarpConfig.config.set(args[0], null);
                    try {
                        WarpConfig.config.save(WarpConfig.configfile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    WarpParticle.load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpRemoved").replace("%warp%", args[0])));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpNotFound").replace("%warp%", args[0])));
                }
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noRights")));
        }

        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
            if (sender.hasPermission("ispawn.removewarp")) {
                if(args.length == 1) {
                    ArrayList<String> completions = new ArrayList<>();

                    ArrayList<String> warpNames = new ArrayList<>(WarpConfig.config.getKeys(false));

                    StringUtil.copyPartialMatches(args[0], warpNames, completions);

                    Collections.sort(completions);
                    return completions;
                }
            }
        return new ArrayList<>();
    }

}
