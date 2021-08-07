package com.funnyboyroks.discordlink.listeners;

import com.funnyboyroks.discordlink.DiscordLink;
import com.funnyboyroks.discordlink.data.DiscordSender;
import com.funnyboyroks.discordlink.data.PlayerData;
import com.funnyboyroks.discordlink.data.struct.CustomChannel;
import com.funnyboyroks.discordlink.discord.markdown.MarkdownProcessor;
import com.funnyboyroks.discordlink.utils.ConfigUtils;
import com.funnyboyroks.discordlink.utils.LangUtils;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.util.Map;

public class DiscordListeners extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        MessageChannel channel = event.getChannel();
        DiscordSender sender = new DiscordSender(event);

        Map<Long, CustomChannel> channels = ConfigUtils.customChannels;

        CustomChannel customChannel = channels.get(channel.getIdLong());

//        PlayerData playerData = DiscordLink.getDataHandler().getPlayer(event.getMessageIdLong());

        if(customChannel != null) {
            customChannel.getListeningPlayers()
                .stream()
                .forEach(p -> LangUtils.sendMessage(
                    p,
                    "ingame-format",
                    "USERNAME",
                    p.getName(),
                    "DISPLAYNAME",
                    LegacyComponentSerializer.legacySection().serialize(p.displayName()),
                    "MESSAGE",
                    MarkdownProcessor.markdownToMC(event.getMessage().getContentRaw()),
                    "PLAIN_MESSAGE",
                    ChatColor.stripColor(MarkdownProcessor.markdownToMC(event.getMessage().getContentRaw()))
                ));
        }

        if (channel.getIdLong() == DiscordLink.config().getLong("ingame-channel")) {
            Bukkit.getOnlinePlayers().forEach(p -> LangUtils.sendMessage(
                p,
                "ingame-format",
                "USERNAME",
                p.getName(),
                "DISPLAYNAME",
                LegacyComponentSerializer.legacySection().serialize(p.displayName()),
                "MESSAGE",
                MarkdownProcessor.markdownToMC(event.getMessage().getContentRaw()),
                "PLAIN_MESSAGE",
                ChatColor.stripColor(MarkdownProcessor.markdownToMC(event.getMessage().getContentRaw()))
            ));
        }
    }
}
