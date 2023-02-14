package de.immaxxx.ispawn.listener;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.SpawnConfig;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (ISpawn.config.getBoolean("onJoinTeleportToSpawn")) {
            if (SpawnConfig.configfile.exists()) {
                if (player.hasPlayedBefore()) {
                    Location spawn = ISpawn.spawn;

                    player.teleport(spawn);
                    Bukkit.getScheduler().runTaskLater(ISpawn.getPlugin(ISpawn.class), () -> {
                        if (!player.getLocation().equals(spawn)) {
                            player.teleport(spawn);
                        }
                    }, 20);
                    if (ISpawn.config.getBoolean("enablePlayerTeleportSound")) {
                        player.playSound(spawn, Sound.valueOf(ISpawn.config.getString("teleportSound").toUpperCase()), 100, 1);
                    }
                }
            }
        }
        if (ISpawn.config.getBoolean("onFirstJoinTeleportToSpawn")) {
            if (SpawnConfig.configfile.exists()) {
                if (!player.hasPlayedBefore()) {
                    Location spawn = ISpawn.spawn;

                    player.teleport(spawn);
                    Bukkit.getScheduler().runTaskLater(ISpawn.getPlugin(ISpawn.class), () -> {
                        if (!player.getLocation().equals(spawn)) {
                            player.teleport(spawn);
                        }
                    }, 20);
                    if (ISpawn.config.getBoolean("enablePlayerTeleportSound")) {
                        player.playSound(spawn, Sound.valueOf(ISpawn.config.getString("teleportSound").toUpperCase()), 100, 1);
                    }
                }
            }
        }
        if (ISpawn.config.getBoolean("enableUpdateCheckerMessageIngame")) {
            if (!SpawnConfig.configfile.exists()) {
                if (player.hasPlayedBefore()) {
                    Location spawn = player.getWorld().getSpawnLocation();

                    player.teleport(spawn);
                    Bukkit.getScheduler().runTaskLater(ISpawn.getPlugin(ISpawn.class), () -> {
                        if (!player.getLocation().equals(spawn)) {
                            player.teleport(spawn);
                        }
                    }, 20);
                    if (ISpawn.config.getBoolean("enablePlayerTeleportSound")) {
                        player.playSound(spawn, Sound.valueOf(ISpawn.config.getString("teleportSound").toUpperCase()), 100, 1);
                    }
                }
            }
        }
    }
}
