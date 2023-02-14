package de.immaxxx.ispawn.config;

import de.immaxxx.ispawn.ISpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class ParticleTwo {
    public static int ID = -1;

    public static void load() {
        if (ID != -1) {
            Bukkit.getScheduler().cancelTask(ID);
        }

        if (ISpawn.config.getString("particleName").equalsIgnoreCase("two")) {
            if (ISpawn.config.getBoolean("enableSpawnParticles") && SpawnConfig.configfile.exists()) {
                Location spawn = ISpawn.spawn;
                two(spawn, null, Particle.valueOf(ISpawn.config.getString("particleType")), 1);
            }
        }

    }

    public static void two(final Location location, String id, final Particle particle, int amount) {
        if (location.getWorld() != null) {
            ID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ISpawn.getPlugin(ISpawn.class), new Runnable() {
                final Location l = location.add(0.5D, 0.7D, 0.5D);
                int time = 16;

                public void run() {
                    if (location.getWorld().getNearbyEntities(location.clone(), 20.0D, 20.0D, 20.0D).stream().filter((e) -> {
                        return e instanceof Player;
                    }).collect(Collectors.toList()).size() >= 1) {
                        if (this.time == 15) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.8D, 0.0D, 0.0D), 100.0D);
                        }

                        if (this.time == 14) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.75D, 0.0D, 0.43D), 100.0D);
                        }

                        if (this.time == 13) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.65D, 0.0D, 0.65D), 100.0D);
                        }

                        if (this.time == 12) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.43D, 0.0D, 0.75D), 100.0D);
                        }

                        if (this.time == 11) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.0D, 0.0D, 0.8D), 100.0D);
                        }

                        if (this.time == 10) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(-0.43D, 0.0D, 0.75D), 100.0D);
                        }

                        if (this.time == 9) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(-0.65D, 0.0D, 0.65D), 100.0D);
                        }

                        if (this.time == 8) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(-0.75D, 0.0D, 0.43D), 100.0D);
                        }

                        if (this.time == 7) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(-0.8D, 0.0D, 0.0D), 100.0D);
                        }

                        if (this.time == 6) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(-0.75D, 0.0D, -0.43D), 100.0D);
                        }

                        if (this.time == 5) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(-0.65D, 0.0D, -0.65D), 100.0D);
                        }

                        if (this.time == 4) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(-0.43D, 0.0D, -0.75D), 100.0D);
                        }

                        if (this.time == 3) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.0D, 0.0D, -0.8D), 100.0D);
                        }

                        if (this.time == 2) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.43D, 0.0D, -0.75D), 100.0D);
                        }

                        if (this.time == 1) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.65D, 0.0D, -0.65D), 100.0D);
                        }

                        if (this.time == 0) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, this.l.clone().add(0.75D, 0.0D, -0.43D), 100.0D);
                            this.time = 16;
                        }

                        --this.time;
                    }
                }

                private void spawnParticle2(float offsetX, float offsetY, float offsetZ, float speed, int count, Location center, double range) {
                    center.getWorld().spawnParticle(particle, center.getX(), center.getY(), center.getZ(), count, offsetX, offsetY, offsetZ, range > 256.0D ? 1.0D : 0.0D, null);
                }
            }, 0L, 2L);
        }
    }
}