package me.nothappy.airdrops;

import lombok.Getter;
import me.nothappy.airdrops.listeners.BlockBreak;
import me.nothappy.airdrops.listeners.PlayerInteract;
import me.nothappy.airdrops.runnables.AirDropping;
import me.nothappy.airdrops.utils.RandomInt;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Airdrops extends JavaPlugin {

    @Getter
    public static Airdrops instance;

    @Override
    public void onEnable() {
        this.instance = this;

        Bukkit.getPluginManager().registerEvents(new PlayerInteract(), this);
        Bukkit.getPluginManager().registerEvents(new BlockBreak(), this);

        BukkitTask task = new BukkitRunnable() {
            @Override
            public void run() {
                World world = Bukkit.getWorld("world");
                if (world == null) {
                    return;
                }
                int x;
                int z;
                x = RandomInt.getRandomInt(0, 1000);
                z = RandomInt.getRandomInt(0, 1000);
                new AirDropping(new Location(world, x, world.getHighestBlockYAt(x, z), z));
                x = RandomInt.getRandomInt(0, 1000);
                z = RandomInt.getRandomInt(0, 1000);
                new AirDropping(new Location(world, x, world.getHighestBlockYAt(x, -z), -z));
                x = RandomInt.getRandomInt(0, 1000);
                z = RandomInt.getRandomInt(0, 1000);
                new AirDropping(new Location(world, -x, world.getHighestBlockYAt(-x, -z), -z));
                x = RandomInt.getRandomInt(0, 1000);
                z = RandomInt.getRandomInt(0, 1000);
                new AirDropping(new Location(world, -x, world.getHighestBlockYAt(-x, z), z));
            }
        }.runTaskTimer(this, 0L, 6000L);
    }
}