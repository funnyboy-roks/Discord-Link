package com.funnyboyroks.discordlink.utils;

import com.funnyboyroks.discordlink.DiscordLink;
import com.funnyboyroks.discordlink.data.struct.CustomChannel;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

public class ConfigUtils {

    public static Map<Long, CustomChannel> customChannels = getCustomChannels();

    public static Map<Long, CustomChannel> getCustomChannels() {
        ConfigurationSection sec = DiscordLink.config().getConfigurationSection("channels");
        Map<Long, CustomChannel> channels = new HashMap<>();
        if (sec == null) return channels;
        for (String key : sec.getKeys(false)) {
            try {
                channels.put(Long.parseLong(key), sec.getObject(key, CustomChannel.class));
            } catch (NumberFormatException ex) {
                Bukkit.getLogger().log(Level.SEVERE, "Invalid channel id: " + key);
            }
        }
        return channels;
    }


}
