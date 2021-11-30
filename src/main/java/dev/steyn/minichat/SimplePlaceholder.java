package dev.steyn.minichat;

import dev.steyn.minichat.api.Placeholder;
import dev.steyn.minichat.api.Resolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class SimplePlaceholder implements Placeholder {

    private final Plugin plugin;
    private final String key;
    private final Resolver resolver;
    private final Collection<String> aliases;

    public SimplePlaceholder(Plugin plugin, String key,
        Resolver resolver, Collection<String> aliases) {
        this.plugin = plugin;
        this.key = key;
        this.resolver = resolver;
        this.aliases = aliases;
    }

    @Override
    public @NotNull Plugin getPlugin() {
        return plugin;
    }

    @Override
    public @NotNull String getKey() {
        return key;
    }

    @Override
    public @NotNull Resolver getResolver() {
        return resolver;
    }

    @NotNull
    @Override
    public Collection<String> getAliases() {
        return aliases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimplePlaceholder that = (SimplePlaceholder) o;
        return plugin.equals(that.plugin) && key.equals(that.key) && aliases.equals(that.aliases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(plugin, key, aliases);
    }

    public static class Builder implements Placeholder.Builder {

        private Plugin plugin;
        private String key;
        private Resolver resolver;
        private List<String> aliases = new ArrayList<>();
        private String prefix;
        private boolean prefixAliasEnabled = true;


        @Override
        public Placeholder.@NotNull Builder plugin(@NotNull Plugin plugin) {
            this.plugin = plugin;
            return this;
        }

        @Override
        public Placeholder.@NotNull Builder prefixedAlias(String prefix) {
            if (prefix == null) {
                prefixAliasEnabled = false;
            }
            this.prefix = prefix;
            return this;
        }

        @Override
        public Placeholder.@NotNull Builder key(@NotNull String key) {
            this.key = key;
            return this;
        }

        @Override
        public Placeholder.@NotNull Builder resolver(@NotNull Resolver resolver) {
            this.resolver = resolver;
            return this;
        }

        @Override
        public Placeholder.@NotNull Builder aliases(@NotNull String... aliases) {
            this.aliases.addAll(Arrays.asList(aliases));
            return this;
        }

        @Override
        public @NotNull Placeholder build() {
            if (prefixAliasEnabled && !key.contains(":")) {
                if (prefix == null) {
                    prefix = plugin.getName();
                }
                aliases.add(prefix + ":" + key);
            }
            return new SimplePlaceholder(plugin, key, resolver,
                Collections.unmodifiableList(this.aliases));
        }
    }
}
