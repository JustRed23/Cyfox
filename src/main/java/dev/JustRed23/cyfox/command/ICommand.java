package dev.JustRed23.cyfox.command;

import java.util.List;

import static dev.JustRed23.cyfox.BotLogger.*;

public interface ICommand {
    void handle(CommandContext ctx);

    String getName();

    default List<String> getAliases() {
        return List.of();
    }
}
