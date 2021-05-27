package com.funnyboyroks.discordlink.listeners;

import com.funnyboyroks.discordlink.discord.markdown.MarkdownProcessor;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class PlayerHandlers implements Listener {

    @EventHandler
    public void onAsyncChat(AsyncChatEvent event) {
        String text = ((TextComponent) event.message()).content();
        String md =  MarkdownProcessor.fromComponent(event.message());
        event.getPlayer().sendMessage(md);
        Component cp = MarkdownProcessor.toComponent(text);
        event.getPlayer().sendMessage(cp);

    }


}
