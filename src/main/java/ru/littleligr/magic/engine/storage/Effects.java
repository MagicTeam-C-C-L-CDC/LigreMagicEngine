package ru.littleligr.magic.engine.storage;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.handler.EffectHandler;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.utils.Consumers;

import java.util.HashMap;
import java.util.function.Consumer;

public class Effects {

    public static final HashMap<Identifier, EffectHandler> map = new HashMap<>();

    public static void register(Identifier identifier, Consumer<EffectHandler> consumer) {
        EffectHandler register = new EffectHandler();
        consumer.accept(register);
        map.put(identifier, register);
    }

    public static <E, T extends Target<E>> void tryAcceptHandler (Identifier identifier, PlayerEntity spellOwner, T target) {
        EffectHandler handler = map.get(identifier);

        LigreMagicEngine.LOGGER.info("SEARCHING HANDLER " + handler);
        if (handler != null) {

            LigreMagicEngine.LOGGER.info("I FOUND HANDLER " + handler);
            Consumers.TupleConsumer<PlayerEntity, T> handlerForType = handler.get((Class<T>) target.getClass());
            if (handlerForType != null) {
                LigreMagicEngine.LOGGER.info("I FOUND TYPE HANDLER " + handlerForType);
                handlerForType.accept(spellOwner, target);
            }
        }
    }
}
