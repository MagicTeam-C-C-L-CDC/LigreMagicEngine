package ru.littleligr.magic.engine.utils.serializaer;

import com.google.gson.*;
import net.minecraft.util.Identifier;

import java.lang.reflect.Type;

public class IdentifierDeserializer implements JsonDeserializer<Identifier> {
    @Override
    public Identifier deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return new Identifier(jsonElement.getAsString());
    }
}
