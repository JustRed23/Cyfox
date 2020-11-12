package dev.JustRed23.cyfox;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.internal.entities.EntityBuilder;

import javax.security.auth.login.LoginException;

public class Bot {

    private Bot() throws LoginException {

        JDABuilder.createDefault("",
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS
        )
                .addEventListeners(new Listener())
                .setActivity(EntityBuilder.createActivity("Debugging...", null, Activity.ActivityType.CUSTOM_STATUS))
                .build();

    }

    public static void main(String[] args) throws LoginException {
        new Bot();
    }
}
