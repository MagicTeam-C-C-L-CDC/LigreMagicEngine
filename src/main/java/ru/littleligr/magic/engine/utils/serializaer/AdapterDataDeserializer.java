package ru.littleligr.magic.engine.utils.serializaer;

import com.google.gson.*;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.storage.lme.Adapters;

import java.lang.reflect.Type;

public class AdapterDataDeserializer implements JsonDeserializer<AdapterData> {
    @Override
    public AdapterData deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = (JsonObject) jsonElement;
        Identifier adapter = jsonDeserializationContext.deserialize(object.get(AdapterData.ADAPTER_FIELD_KEY), Identifier.class);
        return jsonDeserializationContext.deserialize(jsonElement, LigreMagicEngine.ADAPTERS.getAdapaterDataType(adapter));
    }
}
