package com.funnyboyroks.discordlink.data;

public class DiscordChannel {

    private final long id;
    private boolean pluginMade;

    public DiscordChannel(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
