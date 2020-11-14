package dev.JustRed23.cyfox.command.commands;

import dev.JustRed23.cyfox.command.CommandContext;
import dev.JustRed23.cyfox.command.ICommand;
import net.dv8tion.jda.api.JDA;

public class PingCommand implements ICommand {

    public void handle(CommandContext ctx) {
        JDA jda = ctx.getJDA();

        jda.getRestPing().queue(
                (ping) -> ctx.getChannel().sendMessageFormat("Rest ping: %sms\nWS ping: %sms", ping, jda.getGatewayPing()).queue()
        );
    }

    public String getName() {
        return "ping";
    }
}