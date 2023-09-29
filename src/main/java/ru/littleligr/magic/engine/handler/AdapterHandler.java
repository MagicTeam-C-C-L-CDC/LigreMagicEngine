package ru.littleligr.magic.engine.handler;

import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.utils.Consumers;
import ru.littleligr.magic.engine.utils.Consumers.TupleConsumer;

import java.util.HashMap;

public class AdapterHandler<F extends AdapterData> {
    private final HashMap<Class, Consumers.TripleConsumer<PlayerEntity, ? extends Target<?>, F>> map = new HashMap<>();

    public <E, T extends Target<E>> void onTarget(Class<T> tClass,  Consumers.TripleConsumer<PlayerEntity, T, F> consumer) {
        map.put(tClass, consumer);
    }

    public <E, T extends Target<E>>  Consumers.TripleConsumer<PlayerEntity, ? extends Target<?>, F> get(Class<T> key) {
        if (map.containsKey(key))
            return map.get(key);

        return null;
    }
}
