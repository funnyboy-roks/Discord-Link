package com.funnyboyroks.discordlink.utils;

import com.funnyboyroks.discordlink.DiscordLink;

import java.util.logging.Level;

public class Utils {

    public static void log(Level level, String message) {
        DiscordLink.instance.getLogger().log(level, message);
    }

}
