package me.syuk.saenggang.commands;

import me.syuk.saenggang.db.Account;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.embed.EmbedBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpCommand implements Command {
    @Override
    public String name() {
        return "도움말";
    }

    @Override
    public Theme theme() {
        return Theme.UTILS;
    }

    @Override
    public void execute(Account account, String[] args, Message message) {
        EmbedBuilder builder = new EmbedBuilder().setTitle("생강이 도움말");
        Map<Command.Theme, List<String>> commandMap = new HashMap<>();
        for (Command command : Command.commands) {
            if (command.theme() == Theme.FOR_OWNER && !message.getAuthor().isBotOwner()) continue;

            commandMap.putIfAbsent(command.theme(), new ArrayList<>());
            List<String> list = commandMap.get(command.theme());
            list.add("생강아" + command.name());
            commandMap.put(command.theme(), list);
        }
        commandMap.forEach((theme, strings) -> builder.addInlineField(theme.name(), String.join(", ", strings)));
        message.reply(builder);
    }
}
