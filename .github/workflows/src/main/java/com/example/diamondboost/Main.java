package com.example.diamondboost;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DiamondBoost extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("DiamondBoost has been enabled!");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        // Only check if the player actually moved from one block to another to save performance
        if (event.getFrom().getBlockX() == event.getTo().getBlockX() &&
            event.getFrom().getBlockZ() == event.getTo().getBlockZ() &&
            event.getFrom().getBlockY() == event.getTo().getBlockY()) {
            return;
        }

        Player player = event.getPlayer();
        // Get the block the player is standing on (the block at feet level)
        Block blockUnder = player.getLocation().getBlock().getRelative(0, -1, 0);

        if (blockUnder.getType() == Material.DIAMOND_BLOCK) {
            // Give Speed II for 10 seconds (200 ticks)
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, 1));
            // Give Jump Boost II for 10 seconds (200 ticks)
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 200, 1));
        }
    }
}
