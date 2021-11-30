package dev.steyn.minichat.api;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public interface Resolver {


    @NotNull Component resolve(@NotNull Player player, @NotNull Component message,
        @NotNull Audience audience);
}