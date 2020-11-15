package dev.JustRed23.cyfox;

import me.duncte123.botcommons.BotCommons;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

import static dev.JustRed23.cyfox.BotLogger.*;

public class Bot {

    private Bot() throws Exception {

        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            error("An uncaught exception has occurred in thread " + t.getName() + ": " + e.getMessage());
            e.printStackTrace();
        });

        JDABuilder.createDefault(Config.get("token"),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS
        )
                .addEventListeners(new Listener())
                .setActivity(Activity.listening(Config.get("prefix")))
                .build();
    }

    public static void main(String[] args) throws Exception {
        new Bot();
    }

    public static void shutdown(GuildMessageReceivedEvent event) {
        info("Shutting down...");
        event.getJDA().shutdown();
        BotCommons.shutdown(event.getJDA());
    }
}
