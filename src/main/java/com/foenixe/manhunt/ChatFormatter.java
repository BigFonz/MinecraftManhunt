package com.foenixe.manhunt;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatFormatter {
    private final MinecraftManhunt main;

    public ChatFormatter(MinecraftManhunt main) {
        this.main = main;
    }

    public void send(Player player, String msg) {
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public void announce(String msg) {
        for (Player player : main.getServer().getOnlinePlayers()) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
        }
    }

}
