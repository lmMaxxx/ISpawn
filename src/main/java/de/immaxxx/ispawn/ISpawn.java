package de.immaxxx.ispawn;

import de.immaxxx.ispawn.commands.*;
import de.immaxxx.ispawn.config.*;
import de.immaxxx.ispawn.listener.DeathListener;
import de.immaxxx.ispawn.listener.JoinListener;
import de.immaxxx.ispawn.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class ISpawn extends JavaPlugin {

    public static FileConfiguration config;
    public static File messagesfile;
    public static YamlConfiguration messages;
    public static Location spawn;


    @Override
    public void onEnable() {
        messagesfile = new File("plugins/ISpawn/messages.yml");
        if (!messagesfile.exists()) {
            saveResource("messages.yml", false);
        }
        messages = YamlConfiguration.loadConfiguration(messagesfile);

        saveDefaultConfig();
        config = getConfig();

        //Metrics
        int pluginId = 12513;
        new Metrics(this, pluginId);

        //Commands
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
        this.getCommand("setwarp").setExecutor(new SetWarpCommand());
        this.getCommand("removewarp").setExecutor(new RemoveWarpCommand());
        this.getCommand("warp").setExecutor(new WarpCommand());

        //Listener
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new DeathListener(), this);

        //Load Spawn
        if (SpawnConfig.configfile.exists()) {
            World world = Bukkit.getWorld(SpawnConfig.config.getString("Spawn.World"));
            double x = SpawnConfig.config.getDouble("Spawn.X");
            double y = SpawnConfig.config.getDouble("Spawn.Y");
            double z = SpawnConfig.config.getDouble("Spawn.Z");
            float yaw = (float) SpawnConfig.config.getDouble("Spawn.Yaw");
            float pitch = (float) SpawnConfig.config.getDouble("Spawn.Pitch");
            Location location = new Location(world, x, y, z, yaw, pitch);
            spawn = location;
        }

        //Particles
        if (SpawnConfig.configfile.exists()) {
            ParticleOne.load();
            ParticleTwo.load();
        }
        if (WarpConfig.configfile.exists()) {
            if (WarpConfig.config.getKeys(false).size() == 0) {
            } else {
                WarpParticle.load();
            }
        }
        Bukkit.getConsoleSender().sendMessage("\n" +
                                              "§b██╗███████╗██████╗  █████╗ ██╗    ██╗███╗   ██╗\n" +
                                              "§b██║██╔════╝██╔══██╗██╔══██╗██║    ██║████╗  ██║\n" +
                                              "§b██║███████╗██████╔╝███████║██║ █╗ ██║██╔██╗ ██║\n" +
                                              "§b██║╚════██║██╔═══╝ ██╔══██║██║███╗██║██║╚██╗██║\n" +
                                              "§b██║███████║██║     ██║  ██║╚███╔███╔╝██║ ╚████║\n" +
                                              "§b╚═╝╚══════╝╚═╝     ╚═╝  ╚═╝ ╚══╝╚══╝ ╚═╝  ╚═══╝" +
                                              "\n" +
                                              "§bISpawn §7By ImMaxxx!");
    }

    @Override
    public void onDisable() {
    }
}
