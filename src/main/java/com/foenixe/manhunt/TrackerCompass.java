package com.foenixe.manhunt;

import com.foenixe.manhunt.util.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class TrackerCompass implements Listener {

    private final MinecraftManhunt main;

    public TrackerCompass(MinecraftManhunt main) {
        this.main = main;
    }

    @EventHandler
    public void onCompass(PlayerInteractEvent event) {
        if (event.getItem() == null || event.getItem().getType() != Material.COMPASS || !main.getManager().isPlaying(event.getPlayer()))
            return;

        if (event.getAction().toString().contains("RIGHT")) {
           Player selected = main.getManager().getSelectedRunner(event.getPlayer());

            event.getPlayer().setCompassTarget(selected.getLocation());
            main.getChat().send(event.getPlayer(), "&aSelected " + selected.getName() + " as compass location (" + (int) selected.getLocation().getX() + ", " + (int) selected.getLocation().getY() + ", " + (int) selected.getLocation().getZ() + ")");
        } else {
            Inventory selector = Bukkit.createInventory(null, 9, "Select runner");

            for (UUID id : main.getManager().getRunners()) {
                Player player = Bukkit.getPlayer(id);

                selector.setItem(selector.firstEmpty(), new ItemBuilder(Material.PLAYER_HEAD).setSkullOwner(player).setName("&e" + player.getName()).build());
            }
            event.getPlayer().openInventory(selector);
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {

        if (event.getView().getTitle().equals("Select runner") && event.getCurrentItem() != null) {
            Player selected = Bukkit.getPlayer(ChatColor.stripColor(event.getCurrentItem().getItemMeta().getDisplayName()));
            Location l = selected.getLocation();

            event.setCancelled(true);
            main.getManager().setSelectedRunner((Player) event.getWhoClicked(), selected);
            event.getWhoClicked().closeInventory();
            main.getChat().send((Player) event.getWhoClicked(), "&aSelected " + selected.getName() + " as compass location (" + (int) l.getX() + ", " + (int) l.getY() + ", " + (int) l.getZ() + ")");
            ((Player) event.getWhoClicked()).setCompassTarget(selected.getLocation());
        }

    }

}
