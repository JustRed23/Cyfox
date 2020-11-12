package dev.JustRed23.cyfox;

import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static dev.JustRed23.cyfox.BotLogger.*;

public class Listener extends ListenerAdapter {

    public void onReady(@NotNull ReadyEvent event) {
        info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String prefix = Config.get("prefix");
        String messageRaw = event.getMessage().getContentRaw();

        if (messageRaw.equalsIgnoreCase(prefix + "shutdown") && event.getAuthor().getId().equals(Config.get("owner_id")))
            Bot.shutdown(event);
    }
}
