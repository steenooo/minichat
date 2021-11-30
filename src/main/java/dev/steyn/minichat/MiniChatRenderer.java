package dev.steyn.minichat;

import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import java.util.List;
import java.util.Objects;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.Template;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

public class MiniChatRenderer implements ChatRenderer, Listener {

    private final LuckPerms luckPerms;
    private final MiniChatPlugin plugin;

    private static final PlainTextComponentSerializer TEXT = PlainTextComponentSerializer.plainText();

    public MiniChatRenderer(LuckPerms luckPerms, MiniChatPlugin plugin) {
        this.luckPerms = luckPerms;
        this.plugin = plugin;
    }

    @Override
    public @NotNull Component render(@NotNull Player source, @NotNull Component sourceDisplayName,
        @NotNull Component message, @NotNull Audience viewer) {
        CachedMetaData metaData = Objects.requireNonNull(
                luckPerms.getUserManager().getUser(source.getUniqueId())).getCachedData()
            .getMetaData();
        String format = metaData.getMetaValue(plugin.getMetaKey());
        if (format == null) {
            format = plugin.getFallbackFormat();
        }
        List<Template> templates = plugin.getRegistry().handle(source, message, viewer);
        templates.add(Template.of("name", source.getName()));
        String msg = TEXT.serialize(message);
        templates.add(Template.of("msg", msg));
        templates.add(Template.of("message",
            msg)); // fancy tags such as rainbow and gradient don't work unless plain text.
        return MiniChatPlugin.MINI_MESSAGE.parse(format, templates);
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onChat(AsyncChatEvent event) {
        event.renderer(this);
    }

}
