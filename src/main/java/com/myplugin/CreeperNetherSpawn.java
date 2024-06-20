package com.myplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Random;

public class CreeperNetherSpawn extends JavaPlugin {

    @Override
    public void onEnable() {
        Bukkit.getLogger().info("CreeperNetherSpawn plugin has been enabled.");
        startCreeperSpawnTask();
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("CreeperNetherSpawn plugin has been disabled.");
    }

    public void startCreeperSpawnTask() {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.getWorld().getEnvironment() == World.Environment.NETHER) {
                        Location loc = getRandomLocationNear(player.getLocation());
                        if (loc.getBlock().getType() == Material.AIR) {
                            player.getWorld().spawnEntity(loc, EntityType.CREEPER);
                        }
                    }
                }
            }
        }.runTaskTimer(this, 0L, 600L); // 600L = 30 giây
    }

    public Location getRandomLocationNear(Location loc) {
        Random random = new Random();
        int x = loc.getBlockX() + random.nextInt(20) - 10;
        int y = loc.getBlockY() + random.nextInt(6) - 3;
        int z = loc.getBlockZ() + random.nextInt(20) - 10;
        return new Location(loc.getWorld(), x, y, z);
    }
}