package org.lushplugins.regrowthfunctions.function;

import org.bukkit.configuration.file.YamlConfiguration;
import org.lushplugins.regrowthfunctions.RegrowthFunctions;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;

public class FunctionManager {
    private static final Path FUNCTIONS_DIR = RegrowthFunctions.getInstance().getDataPath().resolve("functions");

    private final HashMap<String, Function> functions = new HashMap<>();

    public FunctionManager() {
        if (!Files.exists(FUNCTIONS_DIR)) {
            RegrowthFunctions.getInstance().saveDefaultResource("functions/example.yml");
        }
    }

    public void reload() {
        unregisterAll();

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(FUNCTIONS_DIR)) {
            stream.forEach(entry -> {
                File functionFile = entry.toFile();
                YamlConfiguration config = YamlConfiguration.loadConfiguration(functionFile);

                String id = functionFile.getName().split("\\.")[0];
                Function function = new Function(id, config);
                registerFunction(id, function);
            });
        } catch (IOException e) {
            RegrowthFunctions.getInstance().log(Level.SEVERE, "Something went wrong whilst reading function files", e);
        }
    }

    public void registerFunction(String name, Function function) {
        functions.put(name, function);
    }

    public void unregisterFunction(Function function) {
        unregisterFunction(function.getId());
    }

    public void unregisterFunction(String name) {
        functions.remove(name);
    }

    public void unregisterAll() {
        functions.clear();
    }

    public Set<String> getFunctionNames() {
        return functions.keySet();
    }

    public Function getFunction(String name) {
        return functions.get(name);
    }
}
