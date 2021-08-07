package com.funnyboyroks.discordlink.data;

import net.dv8tion.jda.api.entities.MessageChannel;

import java.util.HashMap;
import java.util.Map;

public class DiscordChannels {

    private static final Map<Long, DiscordChannel> channels = new HashMap<>();

    public static DiscordChannel get(long id) {
        return channels.get(id);
    }

    public static DiscordChannel get(MessageChannel id) {
        return channels.get(id.getIdLong());
    }

}
