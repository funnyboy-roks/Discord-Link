package com.funnyboyroks.discordlink.discord;

import com.funnyboyroks.discordlink.DiscordLink;
import com.funnyboyroks.discordlink.utils.Utils;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.security.auth.login.LoginException;
import java.util.logging.Level;

public class DiscordHandler {

    public static JDA initialise(String token) {
        JDA jda;
        if (token == null || token.equals("YOUR TOKEN HERE")) {
            Utils.log(Level.SEVERE, "You MUST set your Discord token in the config.yml");
            return null;
        }

        try {

            jda = JDABuilder
                .createDefault(token)
                .build();

        } catch (LoginException e) {
            Utils.log(Level.SEVERE, "Invalid bot token!");
            return null;
        }

        jda.addEventListener(new DiscordListeners());

        return jda;
    }

    public static void sendMessage(TextChannel channel, String message) {
        channel.sendMessage(message).queue();
    }

    public static Guild getGuild() {
        return DiscordLink.getJda().getGuilds().get(0);
    }

}
