package com.foenixe.manhunt.test;

import com.foenixe.manhunt.BigMan;
import com.foenixe.manhunt.MinecraftManhunt;
import com.foenixe.manhunt.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RoleCommand implements CommandExecutor {
    private final GameManager manager;
    private MinecraftManhunt main;

    public RoleCommand(MinecraftManhunt main) {
        this.main = main;
        this.manager = main.getManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (args.length == 2) {

            BigMan role = BigMan.valueOf(args[0]);
            Player player = Bukkit.getPlayer(args[1]);

            if (role == BigMan.HUNTER) {
                manager.addHunter(player);
            } else {
                manager.addRunner(player);
            }

            sender.sendMessage(ChatColor.DARK_BLUE + "poggeerrsi did it no error yay");

        } else if (args.length == 1 && args[0].equalsIgnoreCase("start")) {
            sender.sendMessage(ChatColor.DARK_PURPLE + "started heh");
            main.getManager().start();
        } else {
            sender.sendMessage(ChatColor.RED + "what you doing lol idiot gey");
        }

        return true;
    }

}
