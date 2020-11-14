package dev.JustRed23.cyfox;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import static dev.JustRed23.cyfox.BotLogger.*;

public class Listener extends ListenerAdapter {

    private final CommandManager manager = new CommandManager();

    public void onReady(@NotNull ReadyEvent event) {
        info("{} is ready", event.getJDA().getSelfUser().getAsTag());
    }

    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        User user = event.getAuthor();
        if (user.isBot() || event.isWebhookMessage()) return;

        String prefix = Config.get("prefix");
        String messageRaw = event.getMessage().getContentRaw();

        if (messageRaw.startsWith(prefix)) manager.handle(event);
    }
}
