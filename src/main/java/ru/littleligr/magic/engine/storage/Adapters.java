package ru.littleligr.magic.engine.storage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.handler.AdapterHandler;
import ru.littleligr.magic.engine.spell.target.Target;

import java.util.HashMap;
import java.util.function.Consumer;

public abstract class Adapters {
    private static final HashMap<Identifier, AdapterHandler> adapters = new HashMap<>();
    private static final HashMap<Identifier, Class<? extends AdapterData>> adapterDataType = new HashMap<>();

    public static <T extends AdapterData> void register(Identifier identifier, Class<T> adapterDataClass, Consumer<AdapterHandler<T>> consumer) {
        AdapterHandler<T> handler = new AdapterHandler<>();
        consumer.accept(handler);
        adapters.put(identifier, handler);
        adapterDataType.put(identifier, adapterDataClass);
    }

    public static <E, T extends Target<E>, F extends AdapterData> void tryAcceptHandler (Identifier identifier, PlayerEntity spellOwner, T target, F data) {
        AdapterHandler<F> handler = adapters.get(identifier);
        if (handler != null) {
            var handlerForType = handler.get(target.getClass());
            if (handlerForType != null)
                handlerForType.accept(spellOwner, target, data);
        }
    }

    public static  Class<? extends AdapterData> getAdapaterDataType(Identifier key) {
        return adapterDataType.get(key);
    }
}
