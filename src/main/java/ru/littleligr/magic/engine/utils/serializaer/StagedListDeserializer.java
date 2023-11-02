package ru.littleligr.magic.engine.utils.serializaer;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.utils.StagedList;

import java.lang.reflect.Type;
import java.util.List;

public class StagedListDeserializer implements JsonDeserializer<StagedList> {
    @Override
    public StagedList deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<JsonElement> list = jsonElement.getAsJsonArray().asList();
        List<AdapterData> adapterDataList = list.stream().map(e -> (AdapterData) jsonDeserializationContext.deserialize(e, AdapterData.class)).toList();
        return new StagedList(adapterDataList);
    }
}
