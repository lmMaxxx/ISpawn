package de.immaxxx.ispawn.commands;

import de.immaxxx.ispawn.ISpawn;
import de.immaxxx.ispawn.config.SpawnConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class WarpGUIListener implements Listener {
    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.translateAlternateColorCodes('&', ISpawn.messages.getString("warpsGUITitle")))) {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) return;
            if (!event.getCurrentItem().hasItemMeta()) return;
            if (!event.getCurrentItem().getItemMeta().hasLocalizedName()) return;
            World world = Bukkit.getWorld(event.getCurrentItem().getItemMeta().getLocalizedName().split(",")[0]);
            double x = Double.parseDouble(event.getCurrentItem().getItemMeta().getLocalizedName().split(",")[1]);
            double y = Double.parseDouble(event.getCurrentItem().getItemMeta().getLocalizedName().split(",")[2]);
            double z = Double.parseDouble(event.getCurrentItem().getItemMeta().getLocalizedName().split(",")[3]);
            float pitch = Float.parseFloat(event.getCurrentItem().getItemMeta().getLocalizedName().split(",")[4]);
            float yaw = Float.parseFloat(event.getCurrentItem().getItemMeta().getLocalizedName().split(",")[5]);
            Location location = new Location(world, x, y, z, yaw, pitch);
            event.getWhoClicked().teleport(location);
        }
    }
}
