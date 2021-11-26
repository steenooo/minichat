package dev.steyn.minichat;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class MiniChatListener implements Listener {

    private final MiniChatPlugin plugin;

    public MiniChatListener(MiniChatPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onChat(AsyncChatEvent event) {
        event.renderer(plugin.getRenderer());
    }


}
