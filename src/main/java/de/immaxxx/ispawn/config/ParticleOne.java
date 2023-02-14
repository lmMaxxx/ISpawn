package de.immaxxx.ispawn.config;

import de.immaxxx.ispawn.ISpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ParticleOne {
    public static int ID = -1;

    public static void load() {
        if (ID != -1) {
            Bukkit.getScheduler().cancelTask(ID);
        }

        if (ISpawn.config.getString("particleName").equalsIgnoreCase("one")) {
            if (ISpawn.config.getBoolean("enableSpawnParticles") && SpawnConfig.configfile.exists()) {
                Location spawn = ISpawn.spawn;
                one(spawn, null, Particle.valueOf(ISpawn.config.getString("particleType")), 1);
            }
        }


    }

    public static void one(final Location location, String id, final Particle particle, int amount) {
        if (location.getWorld() != null) {
            ID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ISpawn.getPlugin(ISpawn.class), new Runnable() {
                final Location l = location.add(0.0D, 0.0D, 0.0D);
                int time = 16;

                public void run() {
                    if (location.getWorld().getNearbyEntities(location.clone(), 20.0D, 20.0D, 20.0D).stream().filter((e) -> {
                        return e instanceof Player;
                    }).collect(Collectors.toList()).size() >= 0) {
                        switch (this.time) {
                            case 1:
                                this.shortHandParticle(0.65D, 1.6D, -0.65D);
                                break;
                            case 2:
                                this.shortHandParticle(0.43D, 1.5D, -0.75D);
                                break;
                            case 3:
                                this.shortHandParticle(0.0D, 1.4D, -0.8D);
                                break;
                            case 4:
                                this.shortHandParticle(-0.43D, 1.3D, -0.75D);
                                break;
                            case 5:
                                this.shortHandParticle(-0.65D, 1.2D, -0.65D);
                                break;
                            case 6:
                                this.shortHandParticle(-0.75D, 1.1D, -0.43D);
                                break;
                            case 7:
                                this.shortHandParticle(-0.8D, 1.0D, 0.0D);
                                break;
                            case 8:
                                this.shortHandParticle(-0.75D, 0.9D, 0.43D);
                                break;
                            case 9:
                                this.shortHandParticle(-0.65D, 0.8D, 0.65D);
                                break;
                            case 10:
                                this.shortHandParticle(-0.43D, 0.7D, 0.75D);
                                break;
                            case 11:
                                this.shortHandParticle(0.0D, 0.6D, 0.8D);
                                break;
                            case 12:
                                this.shortHandParticle(0.43D, 0.5D, 0.75D);
                                break;
                            case 13:
                                this.shortHandParticle(0.65D, 0.4D, 0.65D);
                                break;
                            case 14:
                                this.shortHandParticle(0.75D, 0.3D, 0.43D);
                                break;
                            case 15:
                                this.shortHandParticle(0.8D, 0.2D, 0.0D);
                                break;
                            default:
                                this.shortHandParticle(0.75D, 1.7D, -0.43D);
                                this.time = 16;
                        }

                        --this.time;
                    }
                }

                private void shortHandParticle(double offsetX, double offsetY, double offsetZ) {
                    this.spawnParticle(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(offsetX, offsetY, offsetZ), 100.0D);
                }

                private void spawnParticle(float offsetX, float offsetY, float offsetZ, float speed, int count, Location center, double range) {
                    center.getWorld().spawnParticle(particle, center.getX(), center.getY(), center.getZ(), count, offsetX, offsetY, offsetZ, range > 256.0D ? 1.0D : 0.0D, null);
                }
            }, 0L, 2L);
        }
    }
}
