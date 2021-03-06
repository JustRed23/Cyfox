package dev.JustRed23.cyfox;

import dev.JustRed23.cyfox.command.CommandCategory;
import dev.JustRed23.cyfox.command.CommandContext;
import dev.JustRed23.cyfox.command.ICommand;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import org.reflections.Reflections;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static dev.JustRed23.cyfox.BotLogger.*;

public class CommandManager {

    private static CommandManager instance;
    private final List<ICommand> commands = new ArrayList<>();

    public CommandManager() {
        instance = this;
        addCommands();
        commands.forEach((command) -> info("Loaded {} command", command.getName()));
    }

    private void addCommands() {
        Reflections reflections = new Reflections(getClass().getPackage().getName() + ".command.commands");
        reflections.getSubTypesOf(ICommand.class).forEach(aClass -> {
            try {
                ICommand command = aClass.getConstructor().newInstance();
                addCommand(command);
            } catch (Exception e) {
                warn("A command could not be loaded: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }

    private void addCommand(ICommand cmd) {
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound) throw new IllegalArgumentException("A command with this name is already present");

        commands.add(cmd);
    }

    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search) {
        String searchLower = search.toLowerCase();

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equals(searchLower)) return cmd;
        }

        return null;
    }

    public List<ICommand> getCommandsByCategory(CommandCategory category) {
        return getCommands().stream().filter(iCommand -> iCommand.getCategory().equals(category)).collect(Collectors.toList());
    }

    void handle(GuildMessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw().replaceFirst("(?i)" + Pattern.quote(Config.get("prefix")), "").split("\\s+");
        String invoke = split[0].toLowerCase();

        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            event.getChannel().sendTyping().queue();
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }
    }

    public static CommandManager getInstance() {
        return instance;
    }
}
