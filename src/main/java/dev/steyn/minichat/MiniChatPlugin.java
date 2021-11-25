package dev.steyn.minichat;

import java.nio.Buffer;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MiniChatPlugin extends JavaPlugin {


    private LuckPerms luckPerms;

    public static MiniChatPlugin getInstance() {
        return JavaPlugin.getPlugin(MiniChatPlugin.class);
    }


    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    @Override
    public void onEnable() {
        luckPerms = LuckPermsProvider.get();
        Bukkit.getPluginManager().registerEvents(new MiniChatListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
