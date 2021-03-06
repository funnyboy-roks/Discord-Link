package com.funnyboyroks.discordlink.utils;

import com.funnyboyroks.discordlink.DiscordLink;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LangUtils {

    public static String getValue(String key) {
        return DiscordLink.messages.getString(key);
    }

    public static Component format(String text) {
        return MiniMessage.get().parse(ChatColor.translateAlternateColorCodes('&', text));
    }

    public static void sendMessage(CommandSender sender, Component message) {
        sender.sendMessage(message);
    }

    public static void sendMessage(CommandSender sender, String message) {
        sendMessage(sender, format(message));
    }

    public static void sendMessage(CommandSender sender, String message, String... replacements) {
        int state = 0;
        String key = "";
        for(String str : replacements) {
            switch (state) {
                case 0:
                    key = str;
                    state = 1;
                    break;
                case 1:
                    message = message.replace("{" + key + "}", str);
                    state = 0;
                    break;
            }
        }
        sendMessage(sender, message);
    }

    public static void sendMessage(CommandSender sender, String key, boolean embed) {
        sendMessage(sender, getValue(key));
    }

    public static void sendMessageRaw(CommandSender sender, String message) {
        sender.sendMessage(message);
    }

    public static void needIngame(CommandSender sender) {
        sendMessage(sender, "commands.must-be-ingame", false);
    }

}
