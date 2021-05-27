package com.funnyboyroks.discordlink.data;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PluginData {

    private Map<UUID, PlayerData> playerMap;

    public PluginData() {
        playerMap = new HashMap<>();
    }

    public Map<UUID, PlayerData> getPlayerMap() {
        return playerMap;
    }
}
