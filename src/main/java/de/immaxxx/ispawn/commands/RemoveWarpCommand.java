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

public class RemoveWarpCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("ispawn.removewarp")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("useRemoveWarpCommand")));
            } else {
                if (WarpConfig.config.get(args[0]) != null) {

                    WarpConfig.config.set(args[0], (Object) null);
                    try {
                        WarpConfig.config.save(WarpConfig.configfile);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    WarpParticle.load();
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("warpRemoved").replace("%warp%", args[0])));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("warpNotFound").replace("%warp%", args[0])));
                }
            }
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', (String) ISpawn.messages.getString("noRights")));
        }

        return true;
    }
}
