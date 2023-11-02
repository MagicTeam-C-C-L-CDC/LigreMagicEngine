package ru.littleligr.magic.engine.storage.lme;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.handler.EffectHandler;
import ru.littleligr.magic.engine.spell.common.EffectData;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.utils.Consumers;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Effects {

    public final HashMap<Identifier, EffectHandler> map = new HashMap<>();

    public void register(Identifier identifier, Consumer<EffectHandler> consumer) {
        EffectHandler register = new EffectHandler();
        consumer.accept(register);
        map.put(identifier, register);
    }

    public void register(Identifier identifier, Supplier<EffectHandler> handler) {
        map.put(identifier,  handler.get());
    }

    public <E, T extends Target<E>> void accept(Stream<EffectData> stream, WizardInfo spellOwner, T target) {
        stream.forEach(data -> this.tryAcceptHandler(data.effect, spellOwner, target));
    }

    private  <E, T extends Target<E>> void tryAcceptHandler (Identifier identifier, WizardInfo spellOwner, T target) {
        LigreMagicEngine.LOGGER.warn("treingAcceptHandler: " + identifier);
        EffectHandler handler = map.get(identifier);

        if (handler != null) {
            Consumers.TupleConsumer<WizardInfo, T> handlerForType = handler.get((Class<T>) target.getClass());
            if (handlerForType != null)
                handlerForType.accept(spellOwner, target);
        }
    }
}
