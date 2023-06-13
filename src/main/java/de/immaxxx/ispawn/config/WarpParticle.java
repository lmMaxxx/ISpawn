package de.immaxxx.ispawn.config;

import de.immaxxx.ispawn.ISpawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class WarpParticle {
    public static int ID = -1;

    public static void load() {
        if (ID != -1) {
            Bukkit.getScheduler().cancelTask(ID);
        }

        if (ISpawn.config.getBoolean("enableWarpParticles") && SpawnConfig.configfile.exists()) {
            ArrayList<Location> warpLocations = getWarps().stream().map(warps -> {
                World world = Bukkit.getWorld(Objects.requireNonNull(WarpConfig.config.getString(warps + ".World")));
                double x = WarpConfig.config.getDouble(warps + ".X");
                double y = WarpConfig.config.getDouble(warps + ".Y");
                double z = WarpConfig.config.getDouble(warps + ".Z");

                return new Location(world, x, y, z);
            }).collect(toArrayList());

            spawnParticle(warpLocations, Particle.valueOf(ISpawn.config.getString("particleTypeWarps")));
        }
    }

    public static void spawnParticle(final ArrayList<Location> locations, final Particle particle) {
        ID = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(ISpawn.getPlugin(ISpawn.class), new Runnable() {
            int time = 16;

            public void run() {
                for (Location location : locations) {
                    if (location.getWorld() == null) return;


                    Location l = location.clone().add(0, 0.1, 0);
                    if (location.getWorld().getNearbyEntities(location.clone(), 20.0D, 20.0D, 20.0D).stream().anyMatch(e -> e instanceof Player)) {
                        if (this.time == 15) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.8D, 0.0D, 0.0D), 100.0D);
                        }

                        if (this.time == 14) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.75D, 0.0D, 0.43D), 100.0D);
                        }

                        if (this.time == 13) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.65D, 0.0D, 0.65D), 100.0D);
                        }

                        if (this.time == 12) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.43D, 0.0D, 0.75D), 100.0D);
                        }

                        if (this.time == 11) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.0D, 0.0D, 0.8D), 100.0D);
                        }

                        if (this.time == 10) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(-0.43D, 0.0D, 0.75D), 100.0D);
                        }

                        if (this.time == 9) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(-0.65D, 0.0D, 0.65D), 100.0D);
                        }

                        if (this.time == 8) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(-0.75D, 0.0D, 0.43D), 100.0D);
                        }

                        if (this.time == 7) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(-0.8D, 0.0D, 0.0D), 100.0D);
                        }

                        if (this.time == 6) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(-0.75D, 0.0D, -0.43D), 100.0D);
                        }

                        if (this.time == 5) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(-0.65D, 0.0D, -0.65D), 100.0D);
                        }

                        if (this.time == 4) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(-0.43D, 0.0D, -0.75D), 100.0D);
                        }

                        if (this.time == 3) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.0D, 0.0D, -0.8D), 100.0D);
                        }

                        if (this.time == 2) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.43D, 0.0D, -0.75D), 100.0D);
                        }

                        if (this.time == 1) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.65D, 0.0D, -0.65D), 100.0D);
                        }

                        if (this.time == 0) {
                            this.spawnParticle2(0.0F, 0.0F, 0.0F, 0.0F, 1, l.clone().add(0.75D, 0.0D, -0.43D), 100.0D);
                        }
                    }
                }

                if(time == 0) {
                    this.time = 16;
                }
                --this.time;
            }

            private void spawnParticle2(float offsetX, float offsetY, float offsetZ, float speed, int count, Location center, double range) {
                center.getWorld().spawnParticle(particle, center.getX(), center.getY(), center.getZ(), count, offsetX, offsetY, offsetZ, range > 256.0D ? 1.0D : 0.0D, null);
            }
        }, 0L, 2L);
    }

    public static ArrayList<String> getWarps() {
        ArrayList<String> list = new ArrayList<>();

        for (String key : WarpConfig.config.getKeys(true)) {
            if (!key.contains(".")) {
                list.add(key);
            }
        }

        return list;
    }

    public static <T> Collector<T, ?, ArrayList<T>> toArrayList() {
        return Collectors.toCollection(ArrayList::new);
    }
}