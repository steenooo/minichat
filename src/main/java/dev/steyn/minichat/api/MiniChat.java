package dev.steyn.minichat.api;

import java.util.function.BiFunction;
import java.util.function.Function;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public interface MiniChat {


    /**
     * Unregister a placeholder
     *
     * @param placeholder the placeholder to remove.
     */
    void unregister(Placeholder placeholder);

    /**
     * Unregister all placeholders owned by a Plugin
     *
     * @param plugin the plugin
     */
    void unregister(Plugin plugin);

    /**
     * Register a placeholder built with the Placeholder.Builder
     *
     * @param placeholder the placeholder to register
     * @return
     */
    Placeholder registerPlaceholder(Placeholder placeholder);


    /**
     * Add a simple function placeholder
     *
     * @param plugin   the Owner
     * @param key      the Key
     * @param resolver the function
     * @param aliases  the aliases for this placeholder
     * @return
     */
    Placeholder addPlaceholder(@NotNull Plugin plugin, @NotNull String key,
        @NotNull Function<Player, Component> resolver, String... aliases);

    /**
     * Add a simple function placeholder
     *
     * @param plugin   the Owner
     * @param key      the Key
     * @param resolver the function
     * @param aliases  the aliases for this placeholder
     * @return
     */
    Placeholder addPlaceholder(@NotNull Plugin plugin, @NotNull String key,
        @NotNull BiFunction<Player, Audience, Component> resolver, String... aliases);

    /**
     * Add a placeholder
     *
     * @param plugin   the Owner
     * @param key      the Key
     * @param resolver the resoler
     * @param aliases  the aliases for this placeholder
     * @return
     */
    Placeholder addPlaceholder(@NotNull Plugin plugin, @NotNull String key,
        @NotNull Resolver resolver, String... aliases);


}
