package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.LoadConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ISReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;
            if (player.hasPermission("ispawn.reload")){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("configReloaded")));
                LoadConfig.reloadConfigs();
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noRights")));
            }
        } else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("configReloaded")));
            LoadConfig.reloadConfigs();
        }

        return true;
    }
}
