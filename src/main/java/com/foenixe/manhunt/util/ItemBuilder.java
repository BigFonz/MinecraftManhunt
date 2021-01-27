package com.foenixe.manhunt.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class ItemBuilder {
    private ItemStack item;
   
    public ItemBuilder(Material type) {
        this(type, 1);
    }

    public ItemBuilder(Material type, int amount) {
        item = new ItemStack(type, amount);
    }

    public ItemBuilder setName(String name) {
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setSkullOwner(Player owner) {
        if (item.getType() == Material.PLAYER_HEAD) {
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwningPlayer(owner);
            item.setItemMeta(meta);
        }
        return this;
    }
    
    public ItemBuilder setLore(String... lore){
        ItemMeta meta = item.getItemMeta();
        for (int i = 0; i < lore.length; i++) {
            lore[i] = ChatColor.translateAlternateColorCodes('&', lore[i]);
        }
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return this;
    }
    
    public ItemBuilder setLore(List<String> lore) {
        ItemMeta meta = item.getItemMeta();
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }
    
    public ItemBuilder addLoreLine(String line){
        ItemMeta meta = item.getItemMeta();
        List<String> lore = new ArrayList<>();
        lore.add(line);
        meta.setLore(lore);
        item.setItemMeta(meta);
        return this;
    }
    
    public ItemStack build(){
        return item;
    }
    
}
