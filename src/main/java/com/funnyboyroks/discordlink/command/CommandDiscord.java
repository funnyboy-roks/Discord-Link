package com.funnyboyroks.discordlink.command;

import com.funnyboyroks.discordlink.utils.LangUtils;
import com.funnyboyroks.discordlink.utils.Utils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CommandDiscord implements TabCompleter, CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(args.length == 0) { // `/discord`
//            LangUtils.sendMessage(sender, "commands.discord.link", false);
            return true;
        }

        String text = String.join(" ", List.of(args).subList(0, args.length));

        LangUtils.sendMessage(sender, text);

        Component c = MiniMessage.get().parse(text);

        StringBuilder sb = new StringBuilder();
        componentRecursion(sb, c);
        LangUtils.sendMessageRaw(sender, sb.toString());


        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        return null;
    }

    private void componentRecursion(StringBuilder sb, Component c) {
        sb.append(formatComponent(c)).append("|");
        System.out.println(c);
        c.children().forEach(com -> componentRecursion(sb, com));
    }

    private String formatComponent(Component com) {
        TextComponent c = (TextComponent) com;
        Style style = c.style();

        String str = c.content();
        if(style.hasDecoration(TextDecoration.BOLD)) {
            str = "**" + str + "**";
        }

        if(style.hasDecoration(TextDecoration.ITALIC)) {
            str = "_" + str + "_";
        }

        if(style.hasDecoration(TextDecoration.STRIKETHROUGH)) {
            str = "~~" + str + "~~";
        }

        if(style.hasDecoration(TextDecoration.UNDERLINED)) {
            str = "__" + str + "__";
        }
        return str;

    }

}
