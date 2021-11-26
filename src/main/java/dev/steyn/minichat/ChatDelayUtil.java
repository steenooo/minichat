package dev.steyn.minichat;

import java.util.Objects;
import java.util.Optional;
import net.luckperms.api.cacheddata.CachedMetaData;
import net.luckperms.api.model.user.User;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class ChatDelayUtil {

    public static final String META_KEY = "delay";
    public static final String PERMISSION = "minichat.delay.ignore";


    public static long getDelaySeconds(Player p) {
        try {
            User user = MiniChatPlugin.getInstance().getLuckPerms().getUserManager()
                .getUser(p.getUniqueId());
            CachedMetaData meta = Objects.requireNonNull(user).getCachedData().getMetaData();
            String value = meta.getMetaValue("chatdelay");
            if (value == null) {
                return 0;
            }
            return Long.parseLong(value);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static Optional<Long> getLastMessage(Player player) {
        if (player.hasPermission(PERMISSION)) {
            return Optional.empty();
        }
        if (player.hasMetadata(META_KEY)) {
            return Optional.empty();
        }
        return Optional.of(player.getMetadata(META_KEY).get(0).asLong());
    }

    public static void setLastMessage(Player player, long last) {
        removeLastMessage(player);
        if (player.hasPermission(PERMISSION)) {
            return;
        }
        if (last < 1) {
            return;
        }
        player.setMetadata(META_KEY, new FixedMetadataValue(MiniChatPlugin.getInstance(), last));
    }

    public static void removeLastMessage(Player player) {
        player.removeMetadata(META_KEY, MiniChatPlugin.getInstance());
    }


}
