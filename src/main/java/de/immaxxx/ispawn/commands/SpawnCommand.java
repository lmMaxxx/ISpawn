package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.SpawnConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (args.length == 0) {
            if (ISpawn.config.getBoolean("spawnPermission")) {
                if (player.hasPermission("ispawn.use")) {

                    if (SpawnConfig.configfile.exists()) {

                        if (isWarmUpTeleport()) {
                            warmUpTeleport(player);
                            return true;
                        }

                        player.teleport(ISpawn.spawn);
                        if (ISpawn.config.getBoolean("enablePlayerTeleportSound")) {
                            player.playSound(ISpawn.spawn, Sound.valueOf(ISpawn.config.getString("teleportSound").toUpperCase()), 100, 1);
                        }
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportetToSpawn")));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noSpawnFound")));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noRights")));
                }
            } else {
                if (SpawnConfig.configfile.exists()) {

                    if (isWarmUpTeleport()) {
                        warmUpTeleport(player);
                        return true;
                    }

                    player.teleport(ISpawn.spawn);
                    if (ISpawn.config.getBoolean("enablePlayerTeleportSound")) {
                        player.playSound(ISpawn.spawn, Sound.valueOf(ISpawn.config.getString("teleportSound").toUpperCase()), 100, 1);
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportetToSpawn")));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noSpawnFound")));
                }
            }
        } else {
            Player target = Bukkit.getPlayer(args[0]);
            if (player.hasPermission("ispawn.tpother")) {
                if (target != null && target.isOnline()) {
                    if (SpawnConfig.configfile.exists()) {

                        if (isWarmUpTeleport() && isWarumupTPOtherEnabled()) {
                            int seconds = ISpawn.config.getInt("teleportWarmupSeconds");
                            warmUpTeleport(target);
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportWarmupMessageOther").replace("%player%", target.getName())).replace("%seconds%", String.valueOf(seconds)));
                            return true;
                        }

                        target.teleport(ISpawn.spawn);
                        target.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("youWasTeleportet").replace("%player%", player.getName())));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("youHaveTeleportet").replace("%player%", target.getName())));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noSpawnFound")));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("playerNotFound").replace("%player%", args[0])));
                }
            }
        }

        return true;
    }

    public static final ArrayList<UUID> inWarmup = new ArrayList<>();

    private static void warmUpTeleport(Player player) {

        int seconds = ISpawn.config.getInt("teleportWarmupSeconds");
        inWarmup.add(player.getUniqueId());

        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportWarmupMessage").replace("%seconds%", String.valueOf(seconds))));

        Bukkit.getScheduler().runTaskLater(ISpawn.getPlugin(ISpawn.class), () -> {
            if (!inWarmup.contains(player.getUniqueId())) {
                return;
            }
            inWarmup.remove(player.getUniqueId());
            player.teleport(ISpawn.spawn);
            if (ISpawn.config.getBoolean("enablePlayerTeleportSound")) {
                player.playSound(ISpawn.spawn, Sound.valueOf(ISpawn.config.getString("teleportSound").toUpperCase()), 100, 1);
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportetToSpawn")));
        }, seconds * 20L);

    }

    private static boolean isWarumupTPOtherEnabled() {
        if (ISpawn.config.getBoolean("enableTeleportWarmupOnTPOther")) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isWarmUpTeleport() {
        if (ISpawn.config.getInt("teleportWarmupSeconds") > 0) {
            return true;
        } else {
            return false;
        }
    }

}
