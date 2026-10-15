package org.lushplugins.regrowthfunctions.command;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.lushplugins.regrowthfunctions.RegrowthFunctions;
import org.lushplugins.regrowthfunctions.function.Function;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.bukkit.annotation.CommandPermission;

@SuppressWarnings("unused")
public class FunctionCommand {

    @Command({"functions run", "function"})
    @CommandPermission("regrowthfunctions.run")
    public void function(CommandSender sender, Function function) {
        sender.sendMessage(Component.text()
            .content("Running function ")
            .color(TextColor.fromHexString("#b7faa2"))
            .append(Component.text()
                .content(function.getId())
                .color(TextColor.fromHexString("#66b04f"))));

        function.run();
    }

    @Command("functions reload")
    @CommandPermission("regrowthfunctions.reload")
    public void reload(CommandSender sender) {
        RegrowthFunctions.getInstance().getFunctionManager().reload();

        sender.sendMessage(Component.text()
            .content("RegrowthFunctions reloaded!")
            .color(TextColor.fromHexString("#b7faa2"))
            .build());
    }
}
