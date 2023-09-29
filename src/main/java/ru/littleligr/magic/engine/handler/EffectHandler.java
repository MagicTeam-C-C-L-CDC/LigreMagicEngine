package ru.littleligr.magic.engine.handler;

import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.utils.Consumers;

import java.util.HashMap;

public class EffectHandler {

    private final HashMap<Class<? extends Target<?>>, Consumers.TupleConsumer<PlayerEntity, ? extends Target<?>>> map = new HashMap<>();

    public <E, T extends Target<E>> void onTarget(Class<T> tClass, Consumers.TupleConsumer<PlayerEntity, T> consumer) {
        map.put(tClass, consumer);
    }

    public <E, T extends Target<E>> Consumers.TupleConsumer<PlayerEntity, T> get(Class<T> key) {
        if (map.containsKey(key))
            return (Consumers.TupleConsumer<PlayerEntity, T>) map.get(key);

        return null;
    }
}
