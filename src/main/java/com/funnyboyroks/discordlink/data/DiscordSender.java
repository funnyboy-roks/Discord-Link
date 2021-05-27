package com.funnyboyroks.discordlink.data;

import com.funnyboyroks.discordlink.DiscordLink;
import com.funnyboyroks.discordlink.discord.DiscordHandler;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.UUID;

public class DiscordSender implements CommandSender {

    private final TextChannel channel;
    private final User user;
    private final Role[] roles;
    private final PlayerData player;

    private boolean operator;

    public DiscordSender(TextChannel channel, User user) {
        this.channel = channel;
        this.user = user;
        this.roles = DiscordHandler.getGuild().getMember(user).getRoles().toArray(new Role[0]);

        this.player = DiscordLink.config().getBoolean("general.verification") ?
            DiscordLink.getDataHandler().getPlayer(user.getIdLong()) :
            null;

        operator = false;
    }

    @Override
    public void sendMessage(@NotNull String message) {
        channel.sendMessage(message).queue();
    }

    @Override
    public void sendMessage(@NotNull String[] messages) {
        channel.sendMessage(String.join("\n", messages)).queue();
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String message) {
        sendMessage(message);
    }

    @Override
    public void sendMessage(@Nullable UUID sender, @NotNull String[] messages) {
        sendMessage(messages);
    }

    @Override
    public @NotNull Server getServer() {
        return Bukkit.getServer();
    }

    @Override
    public @NotNull String getName() {
        return user.getName();
    }

    @Override
    public @NotNull Spigot spigot() {
        return null;
    }

    @Override
    public boolean isPermissionSet(@NotNull String name) {
        return false;
    }

    @Override
    public boolean isPermissionSet(@NotNull Permission perm) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull String name) {
        return false;
    }

    @Override
    public boolean hasPermission(@NotNull Permission perm) {
        return false;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
        return null;
    }

    @Override
    public @NotNull PermissionAttachment addAttachment(@NotNull Plugin plugin) {
        return null;
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
        return null;
    }

    @Override
    public @Nullable PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
        return null;
    }

    @Override
    public void removeAttachment(@NotNull PermissionAttachment attachment) {

    }

    @Override
    public void recalculatePermissions() {

    }

    @Override
    public @NotNull Set<PermissionAttachmentInfo> getEffectivePermissions() {
        return null;
    }

    @Override
    public boolean isOp() {
        return operator;
    }

    @Override
    public void setOp(boolean value) {
        operator = value;
    }
}
