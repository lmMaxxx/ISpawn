package de.immaxxx.ispawn.listener;

import de.immaxxx.ispawn.ISpawn;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;

public class TabCompleteListener implements Listener {
    @EventHandler
    public void onTabComplete(TabCompleteEvent event) {
        if (ISpawn.config.getBoolean("activateWarpFunction")) {
            if (event.getCompletions().contains("warp")){
                event.getCompletions().remove("warp");
            }
        }
    }
}
