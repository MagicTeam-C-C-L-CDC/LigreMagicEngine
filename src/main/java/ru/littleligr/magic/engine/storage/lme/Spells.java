package ru.littleligr.magic.engine.storage.lme;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.spell.Spell;
import ru.littleligr.magic.engine.storage.DataLoader;
import ru.littleligr.magic.engine.storage.info.SpellInfo;
import ru.littleligr.magic.engine.utils.DataFolders;
import ru.littleligr.magic.engine.utils.serializaer.AdapterDataDeserializer;
import ru.littleligr.magic.engine.utils.serializaer.IdentifierDeserializer;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class Spells implements DataLoader {
    private final HashMap<Identifier, Spell> spells = new HashMap<>();

    public Optional<Spell> getSpell(Identifier identifier) {
        return Optional.ofNullable(spells.get(identifier));
    }

    public List<SpellInfo> getSpells() {
        return spells.entrySet().stream().map(e -> new SpellInfo(e.getKey(), e.getValue())).toList();
    }

    @Override
    public void load(ResourceManager resourceManager, String folder, GsonBuilder builder) {
        Gson gson = builder.create();

        for (var entry : resourceManager.findResources(folder, fileName -> fileName.getPath().endsWith(".json")).entrySet()) {
            Identifier identifier = entry.getKey();
            Resource resource = entry.getValue();
            try {
                JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()));
                Spell container = gson.fromJson(reader, Spell.class);

                String newIdentifierPath = identifier.getPath().replace(folder + "/", "");
                newIdentifierPath = newIdentifierPath.substring(0, newIdentifierPath.lastIndexOf("."));

                spells.put(new Identifier(identifier.getNamespace(), newIdentifierPath), container);
            } catch (Exception e) {
                System.err.println("Failed to parse spell: " + identifier);
                e.printStackTrace();
            }
        }
    }
}
