package ru.littleligr.magic.engine.handler;

import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.utils.Consumers;

import java.util.HashMap;

public class EffectHandler {

    private final HashMap<Class<? extends Target<?>>, Consumers.TupleConsumer<WizardInfo, ? extends Target<?>>> map = new HashMap<>();

    public <E, T extends Target<E>> void onTarget(Class<T> tClass, Consumers.TupleConsumer<WizardInfo, T> consumer) {
        map.put(tClass, consumer);
    }

    public <E, T extends Target<E>> Consumers.TupleConsumer<WizardInfo, T> get(Class<T> key) {
        if (map.containsKey(key))
            return (Consumers.TupleConsumer<WizardInfo, T>) map.get(key);

        return null;
    }
}
