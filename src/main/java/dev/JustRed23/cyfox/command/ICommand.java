package dev.JustRed23.cyfox.command;

public interface ICommand {
    void handle(CommandContext ctx);

    String getName();

    String getInfo();

    CommandCategory getCategory();
}
