package com.funnyboyroks.discordlink.data.struct;

import com.funnyboyroks.discordlink.DiscordLink;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the class used for getting channel info from the config
 */
public class CustomChannel {
    private final String mcFormat;
    private final String discordFormat;
    private final String name;
    private final long id;

    public CustomChannel() {
        this.mcFormat = "";
        this.discordFormat = "";
        this.name = "";
        this.id = 0;
    }

    public List<Player> getListeningPlayers() {
        return Bukkit.getOnlinePlayers()
            .stream()
            .filter(this::isPlayerListening)
            .collect(Collectors.toList());
    }

    private boolean isPlayerListening(Player player) {
        return DiscordLink.getDataHandler().getPlayer(player).getListening().contains(id);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMcFormat() {
        return mcFormat;
    }

    public String getDiscordFormat() {
        return discordFormat;
    }
}
