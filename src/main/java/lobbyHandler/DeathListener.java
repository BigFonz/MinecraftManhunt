package lobbyHandler;

import com.foenixe.manhunt.MinecraftManhunt;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {
    private final MinecraftManhunt main;

    public DeathListener(MinecraftManhunt main) {
        this.main = main;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {

        if (main.getManager().getRunners().contains(event.getEntity().getUniqueId())) {
            event.getEntity().setGameMode(GameMode.SPECTATOR);
            main.getManager().getRunners().remove(event.getEntity().getUniqueId());
        }

    }

}
