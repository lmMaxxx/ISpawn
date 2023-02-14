package de.immaxxx.ispawn.config;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class WarpConfig {
    public static File configfile = new File("plugins/ISpawn/Core/warps.yml");
    public static YamlConfiguration config = YamlConfiguration.loadConfiguration(configfile);
}
