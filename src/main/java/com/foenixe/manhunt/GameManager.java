package com.foenixe.manhunt;

import org.bukkit.*;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public final class GameManager {

    private final List<UUID> runners;
    private final List<UUID> hunters;
    private final Map<UUID, UUID> selected;

    private boolean running;

    public GameManager() {
        runners = new ArrayList<>();
        hunters = new ArrayList<>();
        selected = new HashMap<>();
    }

    public void announce(String msg) {
        for (UUID id : runners) {
            Bukkit.getPlayer(id).sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }

        for (UUID id : runners) {
            Bukkit.getPlayer(id).sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;

        List<UUID> players = new ArrayList<>();
        players.addAll(runners);
        players.addAll(hunters);

        Random random = new Random();
        for (UUID id : players) {
            Location location = new Location(Bukkit.getWorlds().get(0), -50, 256, -50);
            location.setX(location.getX() + random.nextInt(100));
            location.setZ(location.getZ() + random.nextInt(100));

            for (int i = 0; i < 256; i++) {
                if (Bukkit.getWorlds().get(0).getBlockAt(location.getBlockX(), i, location.getBlockZ()).getType() != Material.AIR) {
                    location.setY(i + 1);
                }
            }
            Player p = Bukkit.getPlayer(id);
            p.teleport(location);
            p.setGameMode(GameMode.SURVIVAL);
            p.setHealth(20);
            p.setFoodLevel(20);
            p.getInventory().clear();
            p.setLevel(0);
            if (hunters.contains(id))
                p.getInventory().addItem(new ItemStack(Material.COMPASS));

        }

    }

    public boolean isPlaying(Player player) {
        return runners.contains(player.getUniqueId()) || hunters.contains(player.getUniqueId());
    }

    public List<UUID> getHunters() {
        return hunters;
    }

    public void addHunter(Player hunter) {
        hunters.add(hunter.getUniqueId());
    }

    public List<UUID> getRunners() {
        return runners;
    }

    public void addRunner(Player runner) {
        runners.add(runner.getUniqueId());
    }

    public Player getSelectedRunner(Player hunter) {
        return Bukkit.getPlayer(selected.getOrDefault(hunter.getUniqueId(), runners.get(0)));
    }

    public void setSelectedRunner(Player hunter, Player runner) {
        selected.put(hunter.getUniqueId(), runner.getUniqueId());
    }

}
