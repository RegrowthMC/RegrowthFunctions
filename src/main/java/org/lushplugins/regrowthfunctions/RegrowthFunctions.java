package org.lushplugins.regrowthfunctions;

import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import org.lushplugins.regrowthfunctions.command.FunctionCommand;
import org.lushplugins.regrowthfunctions.function.Function;
import org.lushplugins.regrowthfunctions.function.FunctionManager;
import revxrsal.commands.bukkit.BukkitLamp;

public final class RegrowthFunctions extends SpigotPlugin {
    private static RegrowthFunctions plugin;

    private FunctionManager functionManager;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        this.functionManager = new FunctionManager();
        functionManager.reload();

        BukkitLamp.builder(this)
            .parameterTypes(types -> types
                .addParameterType(Function.class, (input, _) -> {
                    return functionManager.getFunction(input.readString());
                }))
            .suggestionProviders(providers -> providers
                .addProvider(Function.class, (_) -> {
                    return functionManager.getFunctionNames();
                }))
            .build()
            .register(new FunctionCommand());
    }

    public FunctionManager getFunctionManager() {
        return functionManager;
    }

    public static RegrowthFunctions getInstance() {
        return plugin;
    }
}
