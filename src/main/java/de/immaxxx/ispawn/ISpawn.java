package de.immaxxx.ispawn;

import de.immaxxx.ispawn.commands.*;
import de.immaxxx.ispawn.config.*;
import de.immaxxx.ispawn.listener.DeathListener;
import de.immaxxx.ispawn.listener.JoinListener;
import de.immaxxx.ispawn.metrics.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.CompletableFuture;

public final class ISpawn extends JavaPlugin {

    public static FileConfiguration config;
    public static YamlConfiguration messages;
    public static Location spawn;
    public static String prefix;


    @Override
    public void onEnable() {
        if (!LoadConfig.messagesfile.exists()) {
            saveResource("imessages.yml", false);
        }
        if (!LoadConfig.configfile.exists()) {
            saveResource("iconfig.yml", false);
        }
        LoadConfig.loadConfigs();
        prefix = getMessage("Prefix");

        //Metrics
        int pluginId = 12513;
        new Metrics(this, pluginId);

        //Commands
        this.getCommand("setspawn").setExecutor(new SetSpawnCommand());
        this.getCommand("spawn").setExecutor(new SpawnCommand());
        if (config.getBoolean("activateWarpFunction")){
            this.getCommand("setwarp").setExecutor(new SetWarpCommand());
            this.getCommand("removewarp").setExecutor(new RemoveWarpCommand());
            this.getCommand("warp").setExecutor(new WarpCommand());
        }
        this.getCommand("isreload").setExecutor(new ISReloadCommand());

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

        this.checkVersionFromSpigot(this, 94789).whenComplete((updateResult, throwable) -> {
            if (updateResult.equals(UpdateResult.UPDATE_AVAILABLE)) {
                Bukkit.getConsoleSender().sendMessage("§bISpawn §7| Update available! §bhttps://www.spigotmc.org/resources/ispawn.94789/");
            } else {
                Bukkit.getConsoleSender().sendMessage("§bISpawn §7| Up to Date!");
            }
        });
    }

    public static String getMessage(String path){
        return translateColorcodes(messages.getString(path));
    }

    public static String translateColorcodes(String message){
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    @Override
    public void onDisable() {
    }

    //Update

    private String newVersion;
    public CompletableFuture<UpdateResult> checkVersionFromSpigot(Plugin plugin, int resourceId) {
        return checkVersionFromSpigot(plugin, resourceId, 2000);
    }

    public CompletableFuture<UpdateResult> checkVersionFromSpigot(Plugin plugin, int resourceId, int timeout) {
        CompletableFuture<UpdateResult> completableFuture = new CompletableFuture<>();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () ->
                completableFuture.complete(checkVersionFromSpigotSync(plugin, resourceId, timeout)));
        return completableFuture;
    }
    private UpdateResult internalVersionCheck(Plugin plugin) {
        plugin.getDescription();
        if (newVersion == null) return UpdateResult.FAIL_NO_VERSION;
        return plugin.getDescription().getVersion().equalsIgnoreCase(newVersion) ? UpdateResult.NO_UPDATE : UpdateResult.UPDATE_AVAILABLE;
    }
    public UpdateResult checkVersionFromSpigotSync(Plugin plugin, int resourceId, int timeout) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(
                    "https://api.spigotmc.org/legacy/update.php?resource=" + resourceId)
                    .openConnection();
            connection.setConnectTimeout(timeout);
            connection.setReadTimeout(timeout);
            this.newVersion = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            connection.disconnect();
            return internalVersionCheck(plugin);
        } catch (IOException exception) {
            return UpdateResult.FAIL_SPIGOT;
        }
    }

}
