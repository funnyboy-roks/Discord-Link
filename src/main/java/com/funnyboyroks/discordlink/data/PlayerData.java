package com.funnyboyroks.discordlink.data;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerData {

    public final UUID uuid;

    private long discordId = -1;
    private boolean verified = false;

    private final List<Long> listening;

    public PlayerData(UUID uuid) {
        this.uuid = uuid;
        listening = new ArrayList<>();
    }

    public PlayerData() {
        this((UUID) null);
    }

    public PlayerData(Player player) {
        this(player.getUniqueId());
    }


    public long getDiscordId() {
        return discordId;
    }

    public void setDiscordId(long discordId) {
        this.discordId = discordId;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public List<Long> getListening() {
        return listening;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(uuid);
    }

    public String getUsername() {
        return getOfflinePlayer().getName();
    }
}
