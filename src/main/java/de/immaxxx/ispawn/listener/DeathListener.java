package de.immaxxx.ispawn.listener;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.SpawnConfig;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        if (ISpawn.config.getBoolean("onDeathTeleportToSpawn")) {
            if (SpawnConfig.configfile.exists()) {
                Location spawn = ISpawn.spawn;

                player.teleport(spawn);
                if (ISpawn.config.getBoolean("enablePlayerTeleportSound")) {
                    player.playSound(spawn, Sound.valueOf(ISpawn.config.getString("teleportSound").toUpperCase()), 100, 1);
                }
            }
        }
    }
}
