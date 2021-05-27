package com.funnyboyroks.discordlink.data;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {

    private final UUID uuid;

    private long discordId = -1;
    private boolean verified = false;

    public PlayerData() {
        this.uuid = null;
    }

    public PlayerData(Player player) {
        this.uuid = player.getUniqueId();
    }



}
