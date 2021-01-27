package com.foenixe.manhunt.lobbyHandler;

import com.foenixe.manhunt.Countdown;
import com.foenixe.manhunt.MinecraftManhunt;
import com.foenixe.manhunt.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

public class StartListener implements Listener {
    private final MinecraftManhunt main;

    private final Inventory teamSelector;
    private Countdown countdown;

    public StartListener(MinecraftManhunt main) {
        this.main = main;

        teamSelector = Bukkit.createInventory(null, 9, "Select team");
        teamSelector.setItem(0, new ItemBuilder(Material.LIME_DYE).setName("&a&lRunner").build());
        teamSelector.setItem(1, new ItemBuilder(Material.RED_DYE).setName("&c&lHunter").build());

        countdown = new Countdown(main, 10);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getItem() == null) return;

        if (!main.getManager().isRunning()) {
            if (event.getItem().getType() == Material.BOW) {
                event.getPlayer().openInventory(teamSelector);
                event.setCancelled(true);
            } else if (event.getItem().getType() == Material.GREEN_WOOL) {

                if (countdown != null && countdown.hasStarted()) {
                    main.getChat().send(event.getPlayer(), "&cThe timer has already started!");
                    return;
                }

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!main.getManager().isPlaying(player)) {
                        main.getChat().send(event.getPlayer(), "&cNot all the players have selected a team!");
                        return;
                    }
                }

                countdown = new Countdown(main, 10);
                countdown.start();
            } else if (event.getItem().getType() == Material.RED_WOOL) {
                if (countdown.hasStarted()) {
                    countdown.stop();
                    return;
                }
                main.getChat().send(event.getPlayer(), "&cThe timer has not started yet!");
            }

        }
    }

}
