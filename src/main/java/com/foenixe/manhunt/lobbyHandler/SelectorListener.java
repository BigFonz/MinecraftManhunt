package com.foenixe.manhunt.lobbyHandler;

import com.foenixe.manhunt.MinecraftManhunt;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SelectorListener implements Listener {
    private final MinecraftManhunt main;

    public SelectorListener(MinecraftManhunt main) {
        this.main = main;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!(event.getView().getTitle().equals("Select team") && event.getCurrentItem() != null))
            return;

            if (event.getCurrentItem().getType() == Material.LIME_DYE) {
                if (!main.getManager().getRunners().contains(event.getWhoClicked().getUniqueId()))
                    main.getManager().getRunners().add(event.getWhoClicked().getUniqueId());
                event.getWhoClicked().closeInventory();
                main.getChat().send((Player) event.getWhoClicked(), "&aJoined the runner team!");
            } else if (event.getCurrentItem().getType() == Material.RED_DYE) {
                if (!main.getManager().getHunters().contains(event.getWhoClicked().getUniqueId()))
                    main.getManager().getHunters().add(event.getWhoClicked().getUniqueId());
                main.getManager().getHunters().add(event.getWhoClicked().getUniqueId());
                event.getWhoClicked().closeInventory();
                main.getChat().send((Player) event.getWhoClicked(), "&aJoined the hunter team!");
            }

    }

}

