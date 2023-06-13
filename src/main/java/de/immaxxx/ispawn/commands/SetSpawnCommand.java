package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.ParticleOne;
import de.immaxxx.ispawn.config.ParticleTwo;
import de.immaxxx.ispawn.config.SpawnConfig;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("ispawn.setspawn")) {
            SpawnConfig.config.set("Spawn.World", player.getWorld().getName());
            SpawnConfig.config.set("Spawn.X", player.getLocation().getX());
            SpawnConfig.config.set("Spawn.Y", player.getLocation().getY());
            SpawnConfig.config.set("Spawn.Z", player.getLocation().getZ());
            SpawnConfig.config.set("Spawn.Pitch", player.getLocation().getPitch());
            SpawnConfig.config.set("Spawn.Yaw", player.getLocation().getYaw());
            try {
                SpawnConfig.config.save(SpawnConfig.configfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ISpawn.spawn = player.getLocation();
            ParticleOne.load();
            ParticleTwo.load();
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("spawnSet")));
        } else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noRights")));
        }

        return true;
    }
}
