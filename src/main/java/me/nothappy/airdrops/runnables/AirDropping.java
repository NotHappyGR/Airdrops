package me.nothappy.airdrops.runnables;

import lombok.Getter;
import me.nothappy.airdrops.Airdrops;
import me.nothappy.airdrops.Items;
import me.nothappy.airdrops.utils.Color;
import me.nothappy.airdrops.utils.RandomInt;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AirDropping extends BukkitRunnable {

    @Getter
    private int minute = -1;

    public Location loc;

    public Inventory airDrop;

    private static Random random = new Random();

    public static List<AirDropping> airDrops = new ArrayList<AirDropping>();

    private int currentHits;

    public AirDropping(Location location) {
        this.loc = location;
        this.airDrop = Bukkit.createInventory(null, 27, Color.translate("Chest"));
        for(int i = 0; i < RandomInt.getRandomInt(3, 6); i++) {
            ItemStack item = Items.items.get(random.nextInt(Items.items.size()));
            int slot = random.nextInt(Items.items.size());
            this.airDrop.setItem(slot, item);
        }
        location.getBlock().setType(Material.BEACON);
        airDrops.add(this);
        this.runTaskTimer(Airdrops.getInstance(), 0L, 1200L);
    }

    public static AirDropping getLocation(Location location) {
        for(AirDropping airDrop : airDrops) {
            if(airDrop.loc.getWorld().getName().equals(location.getWorld().getName())
                    && airDrop.loc.getX() == location.getX()
                    && airDrop.loc.getY() == location.getY()
                    && airDrop.loc.getZ() == location.getZ())
                return airDrop;
        }
        return null;
    }

    public void hit() {
        currentHits++;
        this.loc.getWorld().playSound(loc, Sound.BLAZE_HIT, 0.5f, 0.5f);
        if(currentHits == 50) {
            this.loc.getWorld().playSound(loc, Sound.EXPLODE, 0.5f,0.5f);
            this.loc.getBlock().setType(Material.CHEST);
            Chest drop = (Chest) this.loc.getBlock().getState();
            drop.getInventory().setContents(this.airDrop.getContents());
            AirDropping.airDrops.remove(this);
        }
    }

    public void run() {
        this.minute++;
        if(this.minute == 4 && this.minute != 0) {
            for(Player online : Bukkit.getServer().getOnlinePlayers()) {
                // + +
                if(online.getLocation().getBlockX() >= 0 && online.getLocation().getBlockZ() >= 0) {
                    if(this.loc.getBlockX() >= 0 && this.loc.getBlockZ() >= 0) {
                       online.sendMessage(Color.translate("&aAn Airdrop will drop at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + " &ain 1 minute."));
                    }
                }
                // - +
                if(online.getLocation().getBlockX() < 0 && online.getLocation().getBlockZ() >= 0) {
                    if(this.loc.getBlockX() < 0 && this.loc.getBlockZ() >= 0) {
                        online.sendMessage(Color.translate("&aAn Airdrop will drop at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + " &ain 1 minute."));
                    }
                }
                // - -
                if(online.getLocation().getBlockX() < 0 && online.getLocation().getBlockZ() < 0) {
                    if(this.loc.getBlockX() < 0 && this.loc.getBlockZ() < 0) {
                        online.sendMessage(Color.translate("&aAn Airdrop will drop at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + " &ain 1 minute."));
                    }
                }
                // + -
                if(online.getLocation().getBlockX() >= 0 && online.getLocation().getBlockZ() < 0) {
                    if(this.loc.getBlockX() >= 0 && this.loc.getBlockZ() < 0) {
                        online.sendMessage(Color.translate("&aAn Airdrop will drop at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + " &ain 1 minute."));
                    }
                }
            }

        } else if (this.minute == 5) {
            for (Player online : Bukkit.getServer().getOnlinePlayers()) {
                // + +
                if(online.getLocation().getBlockX() >= 0 && online.getLocation().getBlockZ() >= 0) {
                    if(this.loc.getBlockX() >= 0 && this.loc.getBlockZ() >= 0) {
                        online.sendMessage(Color.translate("&aAn Airdrop has dropped at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + "&a."));
                    }
                }
                // - +
                if(online.getLocation().getBlockX() < 0 && online.getLocation().getBlockZ() >= 0) {
                    if(this.loc.getBlockX() < 0 && this.loc.getBlockZ() >= 0) {
                        online.sendMessage(Color.translate("&aAn Airdrop has dropped at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + "&a."));
                    }
                }
                // - -
                if(online.getLocation().getBlockX() < 0 && online.getLocation().getBlockZ() < 0) {
                    if(this.loc.getBlockX() < 0 && this.loc.getBlockZ() < 0) {
                        online.sendMessage(Color.translate("&aAn Airdrop has dropped at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + "&a."));
                    }
                }
                // + -
                if(online.getLocation().getBlockX() >= 0 && online.getLocation().getBlockZ() < 0) {
                    if(this.loc.getBlockX() >= 0 && this.loc.getBlockZ() < 0) {
                        online.sendMessage(Color.translate("&aAn Airdrop has dropped at&e " + this.loc.getBlockX() + ", " + this.loc.getBlockZ() + "&a."));
                    }
                }
            }
            this.loc.getBlock().setType(Material.ENDER_CHEST);
        }
    }
}
