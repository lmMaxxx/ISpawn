package de.immaxxx.ispawn.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class SpawnConfig {
    public static File configfile = new File("plugins/ISpawn/spawn.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(configfile);
}
