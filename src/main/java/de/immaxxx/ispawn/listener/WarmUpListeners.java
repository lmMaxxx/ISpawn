package de.immaxxx.ispawn.listener;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.commands.SpawnCommand;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class WarmUpListeners implements Listener {

    @EventHandler(ignoreCancelled = true)
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (SpawnCommand.inWarmup.contains(event.getPlayer().getUniqueId())) {
            SpawnCommand.inWarmup.remove(event.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (SpawnCommand.inWarmup.contains(event.getPlayer().getUniqueId())) {

            if (event.getFrom().getX() != event.getTo().getX() || event.getFrom().getY() != event.getTo().getY() || event.getFrom().getZ() != event.getTo().getZ()) {
                SpawnCommand.inWarmup.remove(event.getPlayer().getUniqueId());
                event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportWarmupMessageCanceled")));
            }
        }
    }
}
