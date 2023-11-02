package ru.littleligr.magic.engine.storage.lme;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.handler.AdapterHandler;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.utils.Consumers;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Adapters {
    private final HashMap<Identifier, AdapterHandler> adapters = new HashMap<>();
    private final HashMap<Identifier, Class<? extends AdapterData>> adapterDataType = new HashMap<>();

    public <T extends AdapterData> void register(Identifier identifier, Class<T> adapterDataClass, Consumer<AdapterHandler<T>> consumer) {
        AdapterHandler<T> handler = new AdapterHandler<>();
        consumer.accept(handler);
        adapters.put(identifier, handler);
        adapterDataType.put(identifier, adapterDataClass);
    }

    public <T extends AdapterData> void register(Identifier identifier, Class<T> adapterDataClass, Supplier<AdapterHandler<T>> supplier) {
        adapters.put(identifier, supplier.get());
        adapterDataType.put(identifier, adapterDataClass);
    }

    public <E, T extends Target<E>, F extends AdapterData> void accept(Stream<F> stream, WizardInfo spellOwner, T target) {
        stream.forEach(data -> this.tryAcceptHandler(spellOwner, target, data));
    }

    private  <E, T extends Target<E>, F extends AdapterData> void tryAcceptHandler (WizardInfo spellOwner, T target, F data) {
        AdapterHandler<F> handler = adapters.get(data.adapter);
        if (handler != null) {
            Consumers.TripleConsumer<WizardInfo, T, F> handlerForType = handler.get(target.getClass());
            if (handlerForType != null)
                handlerForType.accept(spellOwner, target, data);
        }
    }

    public Class<? extends AdapterData> getAdapaterDataType(Identifier key) {
        return adapterDataType.get(key);
    }
}
