package com.foenixe.manhunt.lobbyHandler;

import com.foenixe.manhunt.MinecraftManhunt;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakListener implements Listener {
    private final MinecraftManhunt main;

    public BreakListener(MinecraftManhunt main) {
        this.main = main;
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
/*
        if (!(main.isRunning() && main.getManager().isPlaying(event.getPlayer())) {
            event.setCancelled(true);
        }

        if (event.getPlayer().getGameMode() != GameMode.CREATIVE || !main.isRunning() || main.getManager().isPlaying(event.getPlayer())) {
            event.setCancelled(true);
        }

         */
    }

}
