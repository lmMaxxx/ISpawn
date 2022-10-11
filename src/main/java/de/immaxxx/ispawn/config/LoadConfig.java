package de.immaxxx.ispawn.config;

import de.immaxxx.ispawn.ISpawn;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class LoadConfig {

    //Message
    public static File messagesfile = new File("plugins/ISpawn/imessages.yml");

    //Config
    public static File configfile = new File("plugins/ISpawn/iconfig.yml");

    //Load Configs
    public static void loadConfigs(){
        ISpawn.messages = YamlConfiguration.loadConfiguration(messagesfile);
        ISpawn.config = YamlConfiguration.loadConfiguration(configfile);
    }

    //ReloadConfigs
    public static void reloadConfigs() {
        ISpawn.messages = YamlConfiguration.loadConfiguration(messagesfile);
        ISpawn.config = YamlConfiguration.loadConfiguration(configfile);
    }

}
