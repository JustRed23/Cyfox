package dev.JustRed23.cyfox.command.commands;

import dev.JustRed23.cyfox.Bot;
import dev.JustRed23.cyfox.Config;
import dev.JustRed23.cyfox.command.CommandContext;
import dev.JustRed23.cyfox.command.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class ShutdownCommand implements ICommand {

    public void handle(CommandContext ctx) {
        GuildMessageReceivedEvent event = ctx.getEvent();

        if (event.getAuthor().getId().equals(Config.get("owner_id"))) {
            ctx.getChannel().sendMessage("Shutting down...").queue();
            Bot.shutdown(event);
        } else
            ctx.getChannel().sendMessage("No.").queue();
    }

    public String getName() {
        return "shutdown";
    }
}
