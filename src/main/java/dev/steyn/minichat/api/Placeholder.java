package dev.steyn.minichat.api;

import dev.steyn.minichat.SimplePlaceholder;
import java.util.Collection;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

public interface Placeholder {

    static Builder builder() {
        return new SimplePlaceholder.Builder();
    }

    /**
     * The owner of this Placeholder
     *
     * @return Plugin instance
     */
    @NotNull
    Plugin getPlugin();

    /**
     * The key for this placeholder
     *
     * @return key
     */
    @NotNull
    String getKey();

    /**
     * the resolver used to fill in the placeholder
     *
     * @return Resolver
     */
    @NotNull
    Resolver getResolver();

    /**
     * The aliases registered for this placeholder
     *
     * @return Aliases
     */
    @NotNull
    @Unmodifiable
    Collection<String> getAliases();

    interface Builder {

        /**
         * @param plugin the plugin instance
         * @return Builder
         */
        @NotNull
        Builder plugin(
            @NotNull Plugin plugin);

        /**
         * The key for this placeholder.
         *
         * @param key the key.
         * @return Builder
         * <blue><name></blue>
         */
        @NotNull
        Builder key(@NotNull String key);

        /**
         * The resolver for the placeholder.
         *
         * @param resolver the resolver
         * @return Builder
         */
        @NotNull
        Builder resolver(@NotNull Resolver resolver);

        /**
         * Aliases for the placeholder
         *
         * @param aliases
         * @return Builder
         */
        @NotNull
        Builder aliases(@NotNull String... aliases);

        /**
         * Specify the prefix MiniChat should register a prefixed alias with. Use null to disable
         * this extra alias;
         *
         * @param prefix the specified prefix. it defaults to the plugin name.
         * @return
         */
        @NotNull
        Builder prefixedAlias(@Nullable String prefix);


        /**
         * Build the Placeholder
         *
         * @return Placeholder
         */

        @NotNull
        Placeholder build();

    }


}
