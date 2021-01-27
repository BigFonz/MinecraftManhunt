package lobbyHandler;

import com.foenixe.manhunt.MinecraftManhunt;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {
    private final MinecraftManhunt main;

    public QuitListener(MinecraftManhunt main) {
        this.main = main;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        main.getManager().getRunners().remove(event.getPlayer().getUniqueId());
        main.getManager().getRunners().remove(event.getPlayer().getUniqueId());
    }

}
