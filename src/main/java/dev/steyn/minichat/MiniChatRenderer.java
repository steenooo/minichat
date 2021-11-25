package dev.steyn.minichat;

import io.papermc.paper.chat.ChatRenderer;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.Template;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.cacheddata.CachedMetaData;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MiniChatRenderer implements ChatRenderer {


    private final static MiniMessage MINI_MESSAGE = MiniMessage.get();
    private final static LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer.legacy('&');
    private final LuckPerms luckPerms;

    public MiniChatRenderer(LuckPerms luckPerms) {
        this.luckPerms = luckPerms;

    }

    @Override
    public @NotNull Component render(@NotNull Player source, @NotNull Component sourceDisplayName,
        @NotNull Component message, @NotNull Audience viewer) {

        CachedMetaData metaData = Objects.requireNonNull(
                luckPerms.getUserManager().getUser(source.getUniqueId())).getCachedData()
            .getMetaData();
        String format = metaData.getMetaValue("format");
        if(format == null) {
            return Component.join(Component.text(": "), sourceDisplayName, message);
        }
        Component prefix = Optional.ofNullable(metaData.getPrefix()).map(SERIALIZER::deserialize).orElse(Component.empty());
        Component suffix = Optional.ofNullable(metaData.getSuffix()).map(SERIALIZER::deserialize).orElse(Component.empty());

        return MINI_MESSAGE.parse(format,
            Arrays.asList(
                Template.of("name", sourceDisplayName),
                Template.of("prefix", prefix),
                Template.of("suffix", suffix)
            ));
    }
}
