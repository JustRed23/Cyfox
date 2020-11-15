package dev.JustRed23.cyfox.command.commands;

import dev.JustRed23.cyfox.CommandManager;
import dev.JustRed23.cyfox.command.CommandCategory;
import dev.JustRed23.cyfox.command.CommandContext;
import dev.JustRed23.cyfox.command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

public class HelpCommand implements ICommand {

    public void handle(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        EmbedBuilder builder = new EmbedBuilder();
        builder.setColor(Color.ORANGE);

        if (args.isEmpty()) {
            builder.setTitle("Showing list of all commands");

            for (CommandCategory category : CommandCategory.values()) {
                List<ICommand> categoryCommands = CommandManager.getInstance().getCommandsByCategory(category);
                if (category.equals(CommandCategory.INTERNAL)) continue;
                String escape = !category.equals(CommandCategory.values()[CommandCategory.values().length - 1]) ? "\u200E" : "";

                StringBuilder strBuilder = new StringBuilder();

                for (ICommand command : categoryCommands)
                    strBuilder.append("> " + command.getName()).append("\n");

                builder.addField(category.name(), strBuilder.toString() + escape, false);
            }

            channel.sendMessage(builder.build()).queue();
            return;
        }

        String search = args.get(0);
        for (CommandCategory category : CommandCategory.values()) {
            if (category.equals(CommandCategory.INTERNAL)) continue;
            if (search.equalsIgnoreCase(category.name())) {
                builder.setTitle("Showing commands of category '" + category.toString().toLowerCase() + "'");

                List<ICommand> categoryCommands = CommandManager.getInstance().getCommandsByCategory(category);

                StringBuilder strBuilder = new StringBuilder();

                for (ICommand command : categoryCommands)
                    strBuilder.append("> " + command.getName()).append("\n");

                builder.addField(category.name(), strBuilder.toString(), false);
                channel.sendMessage(builder.build()).queue();
                return;
            }
        }

        ICommand command = CommandManager.getInstance().getCommand(search);

        if (command == null) {
            builder.setColor(Color.RED);
            builder.setTitle("No command or category found for '" + search + "'");
            channel.sendMessage(builder.build()).queue();
            return;
        }

        builder.setTitle("Showing help for command '" + command.getName().toLowerCase() + "'");
        builder.setDescription(command.getInfo());

        channel.sendMessage(builder.build()).queue();
    }

    public String getName() {
        return "help";
    }

    public String getInfo() {
        return "Shows a list of available commands";
    }

    public CommandCategory getCategory() {
        return CommandCategory.USEFUL;
    }
}
