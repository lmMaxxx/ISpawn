package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.WarpConfig;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class WarpCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;

        if (ISpawn.config.getBoolean("showWarpsInGUI")){

            if (WarpConfig.config.getKeys(false).size() == 0){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noWarps")));
                return false;
            }
            ArrayList<ItemStack> items = new ArrayList<>();

            for (String warp : WarpConfig.config.getKeys(false)) {

                Material material = Material.valueOf(ISpawn.config.getString("warpGUIItem").toUpperCase());

                if (WarpConfig.config.get(warp + ".Material") != null){
                    material = Material.valueOf(WarpConfig.config.getString(warp + ".Material").toUpperCase());
                }

                ItemStack warpGUIItem = new ItemStack(material);
                ItemMeta warpGUIItemMeta = warpGUIItem.getItemMeta();
                warpGUIItemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpGUIItemNameColor") + warp));
                warpGUIItemMeta.setLocalizedName(WarpConfig.config.get(warp + ".World") + "," + WarpConfig.config.get(warp + ".X") + "," + WarpConfig.config.get(warp + ".Y") + "," + WarpConfig.config.get(warp + ".Z") + "," + WarpConfig.config.get(warp + ".Pitch") + "," + WarpConfig.config.get(warp + ".Yaw"));
                warpGUIItemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DYE, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_UNBREAKABLE);
                warpGUIItem.setItemMeta(warpGUIItemMeta);
                items.add(warpGUIItem);
            }

            Inventory inv = null;
            
            if (items.size() <= 9){
                inv = Bukkit.createInventory(null, 9, ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpsGUITitle")));
            } else if (items.size() <= 18){
                inv = Bukkit.createInventory(null, 18, ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpsGUITitle")));
            } else if (items.size() <= 27) {
                inv = Bukkit.createInventory(null, 27, ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpsGUITitle")));
            } else if (items.size() <= 36) {
                inv = Bukkit.createInventory(null, 36, ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpsGUITitle")));
            } else if (items.size() <= 45) {
                inv = Bukkit.createInventory(null, 45, ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpsGUITitle")));
            } else if (items.size() <= 54) {
                inv = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpsGUITitle")));
            }

            for (ItemStack item : items) {
                inv.addItem(item);
            }

            player.openInventory(inv);

            return false;
        }

        if (args.length == 0) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("useWarpTeleportCommand")));
        } else {
            if (ISpawn.config.getBoolean("warpPermission")) {
                if (player.hasPermission("ispawn.usewarp")) {
                    if (WarpConfig.config.get(args[0]) != null) {
                        World world = Bukkit.getWorld(WarpConfig.config.getString(args[0] + ".World"));
                        double x = WarpConfig.config.getDouble(args[0] + ".X");
                        double y = WarpConfig.config.getDouble(args[0] + ".Y");
                        double z = WarpConfig.config.getDouble(args[0] + ".Z");
                        float yaw = (float) WarpConfig.config.getDouble(args[0] + ".Yaw");
                        float pitch = (float) WarpConfig.config.getDouble(args[0] + ".Pitch");
                        Location warp = new Location(world, x, y, z, yaw, pitch);
                        player.teleport(warp);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportetToWarp").replace("%warp%", args[0])));
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpNotFound").replace("%warp%", args[0])));
                    }
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("noRights")));
                }
            } else {
                if (WarpConfig.config.get(args[0]) != null) {
                    World world = Bukkit.getWorld(WarpConfig.config.getString(args[0] + ".World"));
                    double x = WarpConfig.config.getDouble(args[0] + ".X");
                    double y = WarpConfig.config.getDouble(args[0] + ".Y");
                    double z = WarpConfig.config.getDouble(args[0] + ".Z");
                    float yaw = (float) WarpConfig.config.getDouble(args[0] + ".Yaw");
                    float pitch = (float) WarpConfig.config.getDouble(args[0] + ".Pitch");
                    Location warp = new Location(world, x, y, z, yaw, pitch);
                    player.teleport(warp);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("teleportetToWarp").replace("%warp%", args[0])));
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("Prefix")) + ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpNotFound").replace("%warp%", args[0])));
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (ISpawn.config.getBoolean("showWarpsInGUI")){
            return null;
        }
        if (ISpawn.config.getBoolean("warpPermission")) {
            if (sender.hasPermission("ispawn.usewarp")) {
                if(args.length == 1) {
                    ArrayList<String> completions = new ArrayList<>();

                    ArrayList<String> warpNames = new ArrayList<>(WarpConfig.config.getKeys(false));

                    StringUtil.copyPartialMatches(args[0], warpNames, completions);

                    Collections.sort(completions);
                    return completions;
                }
            }
        } else {
            if(args.length == 1) {
                ArrayList<String> completions = new ArrayList<>();

                ArrayList<String> warpNames = new ArrayList<>(WarpConfig.config.getKeys(false));

                StringUtil.copyPartialMatches(args[0], warpNames, completions);

                Collections.sort(completions);
                return completions;
            }
        }
        return new ArrayList<>();
    }

}
