package lobbyHandler;

import com.foenixe.manhunt.MinecraftManhunt;
import com.foenixe.manhunt.util.ItemBuilder;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

public class JoinListener implements Listener {
    private final MinecraftManhunt main;

    private final ItemStack selectTeam;
    private final ItemStack start;
    private final ItemStack stop;

    public JoinListener(MinecraftManhunt main) {
        this.main = main;

        selectTeam = new ItemBuilder(Material.BOW).setName("&aSelect team").setLore("&7Right click to", "&7select team.").build();
        start = new ItemBuilder(Material.GREEN_WOOL).setName("&bStart game").setLore("&7Right click to", "&7start game.").build();
        stop = new ItemBuilder(Material.RED_WOOL).setName("&cStop timer").setLore("&7Right click to", "&7cancel timer.").build();
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player p = event.getPlayer();
        if (main.getManager().isPlaying(p)) return;

        p.setGameMode(GameMode.ADVENTURE);
        p.setHealth(20);
        p.setFoodLevel(20);
        p.getInventory().clear();
        p.setLevel(69);
        p.teleport(new Location(p.getWorld(), -15.5, 169, 15.5));

        if (!main.getManager().isRunning()) {
            p.getInventory().setItem(0, selectTeam);
            p.getInventory().setItem(7, start);
            p.getInventory().setItem(8, stop);
        }

    }

}
