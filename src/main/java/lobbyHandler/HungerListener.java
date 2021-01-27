package lobbyHandler;

import com.foenixe.manhunt.MinecraftManhunt;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HungerListener implements Listener {
    private final MinecraftManhunt main;

    public HungerListener(MinecraftManhunt main) {
        this.main = main;
    }

    @EventHandler
    public void onHunger(FoodLevelChangeEvent event) {
        if (!(main.getManager().isPlaying((Player) event.getEntity()) && main.getManager().isRunning()))
            event.setCancelled(true);
    }


}
