package com.foenixe.manhunt.lobbyHandler;

import com.foenixe.manhunt.MinecraftManhunt;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class PunchListener implements Listener {
    private final MinecraftManhunt main;

    public PunchListener(MinecraftManhunt main) {
        this.main = main;
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        if (!main.getManager().isRunning())
            event.setDamage(0);
    }

}
