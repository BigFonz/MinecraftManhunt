package com.foenixe.manhunt;

import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

    private final MinecraftManhunt main;
    private int seconds;
    private boolean started;

    public Countdown(MinecraftManhunt main, int seconds) {
        this.main = main;
        this.seconds = seconds;
    }

    public void start() {
        runTaskTimer(main, 0, 20);
        main.getChat().announce("&a&lStarting game!");
        started = true;
    }

    public void stop() {
        cancel();
        main.getChat().announce("&c&lCancelled!");
        started = false;
    }

    @Override
    public void run() {
        if (seconds <= 0) {
            cancel();
            main.getManager().start();
            return;
        }

        if (seconds == 10 || seconds <= 5) {
            main.getChat().announce("&6" + seconds + " &esecond" + (seconds == 1 ? "" : "s") + " remaining!");
        }

        seconds--;

    }

    public boolean hasStarted() {
        return started;
    }

}
