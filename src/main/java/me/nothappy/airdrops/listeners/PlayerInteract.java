package me.nothappy.airdrops.listeners;

import me.nothappy.airdrops.runnables.AirDropping;
import me.nothappy.airdrops.utils.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerInteract implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        if(e.getClickedBlock() == null) { return; }
        AirDropping airDrop = AirDropping.getLocation(e.getClickedBlock().getLocation());
        Player p = e.getPlayer();
        if(airDrop == null) { return; }
        if(e.getClickedBlock().getType() == Material.BEACON) {
            p.sendMessage(Color.translate("&cYou can't open this airdrop yet."));
            e.setCancelled(true);
        }
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.ENDER_CHEST) {
            p.sendMessage(Color.translate("&cHit the airdrop 50 times to open it."));
            e.setCancelled(true);
        }
        if(e.getAction() == Action.LEFT_CLICK_BLOCK && e.getClickedBlock().getType() == Material.ENDER_CHEST) {
            airDrop.hit();
        }
    }
}