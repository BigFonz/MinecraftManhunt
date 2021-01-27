package com.foenixe.manhunt;

import com.foenixe.manhunt.test.RoleCommand;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.foenixe.manhunt.lobbyHandler.*;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public final class MinecraftManhunt extends JavaPlugin {

    private GameManager manager;
    private ChatFormatter chat;

    @Override
    public void onEnable() {
        saveDefaultConfig();

        manager = new GameManager();
        chat = new ChatFormatter(this);

        getServer().getPluginManager().registerEvents(new TrackerCompass(this), this);
        loadLobby();

        //test
        getCommand("role").setExecutor(new RoleCommand(this));

    }

    public GameManager getManager() {
        return manager;
    }

    public ChatFormatter getChat() {
        return chat;
    }

    private void loadLobby() {
        File lobbySchematic = new File(getDataFolder() + "/" + getConfig().getString("lobby"));

        ClipboardFormat format = ClipboardFormats.findByFile(lobbySchematic);
        try (ClipboardReader reader = format.getReader(new FileInputStream(lobbySchematic))) {
            Clipboard clipboard = reader.read();

            try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(BukkitAdapter.adapt(Bukkit.getWorlds().get(0)), 69420)) {
                Operation operation = new ClipboardHolder(clipboard)
                        .createPaste(editSession)
                        .to(BlockVector3.at(0, 175, 0))
                        .ignoreAirBlocks(false)
                        .build();
                Operations.complete(operation);
            }

        } catch (IOException | WorldEditException e) {
            e.printStackTrace();
        }

        getServer().getPluginManager().registerEvents(new QuitListener(this), this);
        getServer().getPluginManager().registerEvents(new PunchListener(this), this);
        getServer().getPluginManager().registerEvents(new HungerListener(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new SelectorListener(this), this);
        getServer().getPluginManager().registerEvents(new BreakListener(this), this);
        getServer().getPluginManager().registerEvents(new StartListener(this), this);
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);

    }

}
