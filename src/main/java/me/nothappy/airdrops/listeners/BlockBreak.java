package me.nothappy.airdrops.listeners;

import me.nothappy.airdrops.runnables.AirDropping;
import me.nothappy.airdrops.utils.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreak implements Listener {

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        AirDropping airDrop = AirDropping.getLocation(e.getBlock().getLocation());
        if(airDrop == null) { return; }
        if(e.getBlock().getType() == Material.BEACON || e.getBlock().getType() == Material.ENDER_CHEST) {
            p.sendMessage(Color.translate("&CYou can't destroy airdrops."));
            e.setCancelled(true);
        }
    }
}
