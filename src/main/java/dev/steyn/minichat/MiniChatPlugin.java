package dev.steyn.minichat;

import io.papermc.paper.chat.ChatRenderer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniChatPlugin extends JavaPlugin {


    private LuckPerms luckPerms;

    private ChatRenderer renderer;
    public static MiniChatPlugin getInstance() {
        return JavaPlugin.getPlugin(MiniChatPlugin.class);
    }

    public ChatRenderer getRenderer() {
        return renderer;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    @Override
    public void onEnable() {
        luckPerms = LuckPermsProvider.get();
        this.renderer = new MiniChatRenderer(luckPerms);
        Bukkit.getPluginManager().registerEvents(new MiniChatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
