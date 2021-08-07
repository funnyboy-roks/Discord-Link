package com.funnyboyroks.discordlink.data;

import com.funnyboyroks.discordlink.DiscordLink;
import com.funnyboyroks.discordlink.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.UUID;
import java.util.logging.Level;

public class DataHandler implements Listener {

    private static final String DATA_FILE_NAME = "data.json";

    private final File dataFile;

    private PluginData pluginData;

    public DataHandler() {
        dataFile = new File(
            DiscordLink.instance.getDataFolder(),
            DATA_FILE_NAME
        );
    }

    public void loadData() {
        Utils.log(Level.INFO, "Loading plugin data...");
        pluginData = loadJson();
    }

    public void saveData() {
        Utils.log(Level.INFO, "Saving plugin data...");
        saveJson();
    }

    public static boolean createFile(File file) throws IOException {
        if (file.exists()) {
            return false;
        }
        file = file.getAbsoluteFile();
        file.getParentFile().mkdirs();
        return file.createNewFile();
    }

    public static void writeUTF8(String data, File file) throws IOException {
        if (!file.exists()) {
            createFile(file);
        }
        FileOutputStream fostream = new FileOutputStream(file);
        fostream.write(data.getBytes(StandardCharsets.UTF_8));
        fostream.flush();
        fostream.close();
    }

    public static String readUTF8(File file) throws IOException {
        if (!file.exists())
            return "";
        return new String(
            Files.readAllBytes(file.toPath()),
            StandardCharsets.UTF_8
        );
    }


    public PluginData loadJson() {
        try {
            if (!dataFile.exists()) {
                createFile(dataFile);
                return new PluginData();
            }
            PluginData obj = DiscordLink.getGson().fromJson(readUTF8(dataFile), PluginData.class);
            return obj == null ? new PluginData() : obj;
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            return new PluginData();
        }
    }

    public void saveJson() {
        try {
            if (!dataFile.exists()) {
                createFile(dataFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // If, for some reason, the data is missing, don't overwrite the file as to not lose any data
        if (pluginData == null) {
            return;
        }

        try {
            writeUTF8(
                DiscordLink.getGson().toJson(pluginData),
                dataFile
            );
        } catch (IOException e) {
            Utils.log(Level.SEVERE, "Unable to save plugin data!");
            e.printStackTrace();
        }

    }

    public PlayerData getPlayer(long discordId) {
        return pluginData
            .getPlayerMap()
            .values()
            .stream()
            .filter(p -> p.getDiscordId() == discordId)
            .findFirst()
            .orElse(null);
    }

    public PlayerData getPlayer(UUID uuid) {
        return pluginData.getPlayerMap().get(uuid);
    }

    public PlayerData getPlayer(Player player) {
        return getPlayer(player.getUniqueId());
    }

}
