package ru.littleligr.magic.engine.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.spell.Spell;
import ru.littleligr.magic.engine.utils.AdapterDataDeserializer;
import ru.littleligr.magic.engine.utils.IdentifierSerializer;

import java.io.InputStreamReader;
import java.util.HashMap;

public abstract class Spells {
    public static final HashMap<Identifier, Spell> spells = new HashMap<>();

    public static void loadSpells(ResourceManager resourceManager) {
        LigreMagicEngine.LOGGER.info("REGISTER SPELLS");

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(Identifier.class, new IdentifierSerializer())
                .registerTypeAdapter(AdapterData.class, new AdapterDataDeserializer())
                .create();
        String directory = "spells";

        LigreMagicEngine.LOGGER.info("RESOURCES " + resourceManager.findResources(directory, fileName -> fileName.getPath().endsWith(".json")).entrySet());

        for (var entry : resourceManager.findResources(directory, fileName -> fileName.getPath().endsWith(".json")).entrySet()) {
            Identifier identifier = entry.getKey();
            Resource resource = entry.getValue();
            try {
                JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()));
                Spell container = gson.fromJson(reader, Spell.class);

                String newIdentifierPath = identifier.getPath().replace(directory + "/", "");
                newIdentifierPath = newIdentifierPath.substring(0, newIdentifierPath.lastIndexOf("."));

                Spells.spells.put(new Identifier(identifier.getNamespace(), newIdentifierPath), container);
            } catch (Exception e) {
                System.err.println("Failed to parse spell: " + identifier);
                e.printStackTrace();
            }
        }

        spells.entrySet().forEach(e -> LigreMagicEngine.LOGGER.info(e.getKey().toTranslationKey() + " " + e.getValue().toString()));
    }
}
