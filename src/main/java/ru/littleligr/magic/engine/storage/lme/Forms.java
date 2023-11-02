package ru.littleligr.magic.engine.storage.lme;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.spell.form.SpellForm;
import ru.littleligr.magic.engine.spell.form.SpellFormData;
import ru.littleligr.magic.engine.storage.DataLoader;
import ru.littleligr.magic.engine.storage.info.FormInfo;
import ru.littleligr.magic.engine.utils.DataFolders;
import ru.littleligr.magic.engine.utils.serializaer.AdapterDataDeserializer;
import ru.littleligr.magic.engine.utils.serializaer.IdentifierDeserializer;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Optional;
import java.util.function.Supplier;

public class Forms implements DataLoader {
    private final HashMap<Identifier, SpellForm> forms = new HashMap<>();
    private final HashMap<Identifier, SpellFormData> formsData = new HashMap<>();

    public Optional<FormInfo> getSpellFormInfo(Identifier identifier) {
        if (forms.containsKey(identifier) && formsData.containsKey(identifier))
            return Optional.of(new FormInfo(forms.get(identifier), formsData.get(identifier)));
        else return Optional.empty();
    }

    public Optional<SpellFormData> getSpellFormData(Identifier identifier) {
        return Optional.ofNullable(formsData.get(identifier));
    }

    public Optional<SpellForm> getSpellForm(Identifier identifier) {
        if (forms.containsKey(identifier))
            return Optional.of(forms.get(identifier));
        else return Optional.empty();
    }

    public void register(Identifier identifier, Supplier<SpellForm> form) {
        forms.put(identifier, form.get());
    }

    @Override
    public void load(ResourceManager resourceManager, String folder, GsonBuilder builder) {
        Gson gson = builder.create();

        for (var entry : resourceManager.findResources(folder, fileName -> fileName.getPath().endsWith(".json")).entrySet()) {
            Identifier identifier = entry.getKey();
            Resource resource = entry.getValue();
            try {
                JsonReader reader = new JsonReader(new InputStreamReader(resource.getInputStream()));
                SpellFormData container = gson.fromJson(reader, SpellFormData.class);

                formsData.put(container.identifier, container);
            } catch (Exception e) {
                System.err.println("Failed to parse spell: " + identifier);
                e.printStackTrace();
            }
        }
    }
}
