package dev.steyn.minichat;

import io.papermc.paper.event.player.AsyncChatEvent;
import java.util.Optional;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ChatDelayListener implements Listener {

    @EventHandler
    public void onDelay(AsyncChatEvent e) {
        Player p = e.getPlayer();
        if (p.hasPermission("minichat.delay.ignore")) {
            return;
        }
        Optional<Long> last = ChatDelayUtil.getLastMessage(p);
        long delay = ChatDelayUtil.getDelaySeconds(p);
        if (delay == 0) {
            return;
        }
        if (last.isPresent()) {
            long lastMsg = last.get();
            long curr = System.currentTimeMillis();
            long next = lastMsg + (delay * 1000);
            long diff = (next - curr) / 1000;
            if (diff >= 1) {
                Component seconds;
                if (diff == 1) {
                    seconds = Component.text("second");
                } else {
                    seconds = Component.text("seconds");
                }
                p.sendMessage(Component.join(Component.space(), Component.text("Please wait"),
                    Component.text(diff).color(NamedTextColor.GREEN), seconds,
                    Component.text("before sending your next message.")).color(
                    NamedTextColor.GRAY));
            }
        }
        ChatDelayUtil.setLastMessage(p, System.currentTimeMillis());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        ChatDelayUtil.removeLastMessage(e.getPlayer());
    }

}
