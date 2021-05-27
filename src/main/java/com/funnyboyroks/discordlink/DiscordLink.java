package com.funnyboyroks.discordlink;

import com.funnyboyroks.discordlink.command.CommandDiscord;
import com.funnyboyroks.discordlink.data.DataHandler;
import com.funnyboyroks.discordlink.discord.DiscordHandler;
import com.funnyboyroks.discordlink.listeners.PlayerHandlers;
import com.funnyboyroks.discordlink.utils.ConfigUpdater;
import com.funnyboyroks.discordlink.utils.UpdateChecker;
import com.funnyboyroks.discordlink.utils.Utils;
import com.google.gson.Gson;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;

public final class DiscordLink extends JavaPlugin {

    private static final String CONFIG_FILE   = "config.yml";
    private static final String MESSAGES_FILE = "messages.yml";
    private static final String SPIGOT_URL    = "";
    private static final int    SPIGOT_ID     = 0;

    public static DiscordLink  instance;

    private static JDA         jda;
    private static DataHandler dataHandler;
    private static Gson        gson;

    public static YamlConfiguration messages;

    public static FileConfiguration config() {
        return instance.getConfig();
    }

    public DiscordLink() {
        instance = this;
        dataHandler = new DataHandler();
        gson = new Gson();
    }

    @Override
    public void onEnable() {

        // Update/Create config if needed
        instance.saveDefaultConfig();
        File configFile = new File(getDataFolder(), CONFIG_FILE);
        try {
            ConfigUpdater.update(instance, CONFIG_FILE, configFile, Collections.emptyList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Load messages
        messages = YamlConfiguration.loadConfiguration(new File(instance.getDataFolder(), MESSAGES_FILE));

        // Register Commands
        Bukkit.getPluginCommand("discord").setExecutor(new CommandDiscord());

        // Register Listeners
        Bukkit.getPluginManager().registerEvents(new PlayerHandlers(), instance);

        // Check for updates
        if ((new UpdateChecker(instance, SPIGOT_ID)).isBehindSpigot()) {
            Utils.log(Level.INFO, "A new release is available at " + SPIGOT_URL);
        }

        // Load data
        dataHandler.loadData();

        // Discord Bot Initialisation
        String token = config().getString("discord-token", null);
        jda = DiscordHandler.initialise(token);
        if (jda == null) { // If invalid token or bad JDA launch, disable the plugin as to not break anything
            Bukkit.getPluginManager().disablePlugin(instance);
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        // Save data
        dataHandler.saveData();
    }

    public static void reload() {
        instance.reloadConfig();
        messages = YamlConfiguration.loadConfiguration(new File(instance.getDataFolder(), MESSAGES_FILE));
    }

    public static DataHandler getDataHandler() {
        return dataHandler;
    }

    public static Gson getGson() {
        return gson;
    }

    public static JDA getJda() {
        return jda;
    }
}
