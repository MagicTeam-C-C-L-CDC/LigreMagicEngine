package ru.littleligr.magic.engine.utils;

import com.google.gson.*;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.storage.Adapters;

import java.lang.reflect.Type;

public class AdapterDataDeserializer implements JsonDeserializer<AdapterData> {
    @Override
    public AdapterData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = (JsonObject) jsonElement;
        Identifier adapter = jsonDeserializationContext.deserialize(object.get("adapter"), Identifier.class);
        var data = jsonDeserializationContext.deserialize(jsonElement, Adapters.getAdapaterDataType(adapter));
        LigreMagicEngine.LOGGER.info(data.getClass().toString());
        return (AdapterData) data;
    }
}
