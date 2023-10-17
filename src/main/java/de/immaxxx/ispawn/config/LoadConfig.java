package de.immaxxx.ispawn.config;

import de.immaxxx.ispawn.ISpawn;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

public class LoadConfig {

    //Message
    public static File messagesfile = new File("plugins/ISpawn/imessages.yml");

    //Config
    public static File configfile = new File("plugins/ISpawn/iconfig.yml");

    //Load Configs
    public static void loadConfigs(){
        addDefaults();
        addDefaults();
    }

    //ReloadConfigs
    public static void reloadConfigs() {
        ISpawn.messages = YamlConfiguration.loadConfiguration(messagesfile);
        ISpawn.config = YamlConfiguration.loadConfiguration(configfile);
    }

    private static void addDefaults () {

        ISpawn.messages = YamlConfiguration.loadConfiguration(messagesfile);
        ISpawn.config = YamlConfiguration.loadConfiguration(configfile);

        //Messages
        ISpawn.messages.addDefault("Prefix", "&b&lISpawn &7| ");
        ISpawn.messages.addDefault("noSpawnFound", "&7No &bSpawn &7found!");
        ISpawn.messages.addDefault("warpsGUITitle", "&b&lISpawn &7| &bWarps");
        ISpawn.messages.addDefault("warpGUIItemNameColor", "&b");
        ISpawn.messages.addDefault("maxWarps", "&7You can only create &b54 &7warps!");
        ISpawn.messages.addDefault("noWarps", "&7No &bWarps &7found!");
        ISpawn.messages.addDefault("teleportetToSpawn", "&7You have been teleported to the &bspawn&7!");
        ISpawn.messages.addDefault("spawnSet", "&7The &bSpawn &7was set successfully!");
        ISpawn.messages.addDefault("youWasTeleportet", "&7You have been teleported to the spawn by &b%player%&7!");
        ISpawn.messages.addDefault("youHaveTeleportet", "&7You teleported player &b%player% &7to the spawn!");
        ISpawn.messages.addDefault("playerNotFound", "&7The player &b%player% &7was not found!");
        ISpawn.messages.addDefault("useWarpCommand", "&7Please use &b/warp <warpname> &7to create a warp!");
        ISpawn.messages.addDefault("useRemoveWarpCommand", "&7Please use &b/removewarp <warpname> &7to remove a warp!");
        ISpawn.messages.addDefault("createdWarp", "&7You have successfully created the Warp &b%warp%&7!");
        ISpawn.messages.addDefault("warpNotFound", "&7The warp &b%warp% &7was not found!");
        ISpawn.messages.addDefault("warpRemoved", "&7The warp &b%warp% &7has been successfully removed!");
        ISpawn.messages.addDefault("useWarpTeleportCommand", "&7Use &b/warp <warpname> &7to teleport to a warp!");
        ISpawn.messages.addDefault("teleportetToWarp", "&7You have been teleported to Warp &b%warp%&7!");
        ISpawn.messages.addDefault("warpExists", "&7The warp &b%warp% &7already exists!");
        ISpawn.messages.addDefault("noRights", "&7You have &bno rights &7to this!");
        ISpawn.messages.addDefault("configReloaded", "&7The configs was &bsuccessfully &7reloaded!");
        ISpawn.messages.addDefault("updateAvailable", "&7A new version of &b&lISpawn &7is available! §bhttps://www.spigotmc.org/resources/ispawn.94789/");
        ISpawn.messages.addDefault("noMaterialFound", "&cNo item with this name was found. therefore the default item from the config is used.");
        ISpawn.messages.addDefault("teleportWarmupMessage", "&7You will be teleported in &b%seconds% &7seconds!");
        ISpawn.messages.addDefault("teleportWarmupMessageOther", "&7The player &b%player% &7will be teleported in &b%seconds% &7seconds!");
        ISpawn.messages.addDefault("teleportWarmupMessageCanceled", "&cThe teleportation was canceled because you moved!");

        ISpawn.messages.options().copyDefaults(true);
        try {
            ISpawn.messages.save(messagesfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Config
        ISpawn.config.addDefault("spawnPermission", false);
        ISpawn.config.addDefault("warpPermission", false);
        ISpawn.config.addDefault("activateWarpFunction", true);
        ISpawn.config.addDefault("onJoinTeleportToSpawn", true);
        ISpawn.config.addDefault("onFirstJoinTeleportToSpawn", true);
        ISpawn.config.addDefault("onDeathTeleportToSpawn", true);
        ISpawn.config.addDefault("enablePlayerTeleportSound", true);
        ISpawn.config.addDefault("enableUpdateCheckerMessageIngame", true);
        ISpawn.config.addDefault("showWarpsInGUI", true);
        ISpawn.config.addDefault("warpGUIItem", "PAPER");
        ISpawn.config.addDefault("teleportSound", "ENTITY_EXPERIENCE_ORB_PICKUP");
        ISpawn.config.setComments("teleportSound", Collections.singletonList("Here you can set what sound comes when a player is teleported in any way. If you don't know the names, here is the link: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Sound.html"));
        ISpawn.config.addDefault("enableSpawnParticles", true);
        ISpawn.config.setComments("enableSpawnParticles", Collections.singletonList("Particle Section"));
        ISpawn.config.addDefault("enableWarpParticles", true);
        ISpawn.config.addDefault("particleName", "one");
        ISpawn.config.setComments("particleName", Collections.singletonList("Here you can choose the style of the particle effect on the spawn. You can choose between 'one' and 'two'."));
        ISpawn.config.addDefault("particleType", "FIREWORKS_SPARK");
        ISpawn.config.setComments("particleType", Collections.singletonList("Here you can choose the type of the particle effect on the spawn. You can choose between all particles. If you don't know the names, here is the link: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Particle.html"));
        ISpawn.config.addDefault("particleTypeWarps", "FIREWORKS_SPARK");
        ISpawn.config.setComments("particleTypeWarps", Collections.singletonList("Here you can choose the type of the particle effect on the warps. You can choose between all particles. If you don't know the names, here is the link: https://hub.spigotmc.org/javadocs/spigot/org/bukkit/Particle.html"));
        ISpawn.config.addDefault("teleportWarmupSeconds", 3);
        ISpawn.config.setComments("teleportWarmupSeconds", Collections.singletonList("Here you can set how long the player has to wait until he is teleported to the spawn. (in seconds) If you don't want a warmup, set it to 0."));
        ISpawn.config.addDefault("enableTeleportWarmupOnTPOther", true);
        ISpawn.config.setComments("enableTeleportWarmupOnTPOther", Collections.singletonList("Here you can set whether the warmup should also be used when teleporting other players."));

        ISpawn.config.options().copyDefaults(true);
        try {
            ISpawn.config.save(configfile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ISpawn.config = YamlConfiguration.loadConfiguration(configfile);
        ISpawn.messages = YamlConfiguration.loadConfiguration(messagesfile);

    }

}
