package ru.littleligr.magic.engine.storage;

import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import ru.littleligr.magic.engine.utils.Consumers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public abstract class ComponentsRegister {
    private static final List<Consumer<ItemComponentFactoryRegistry>> itemConsumers = new LinkedList<>();

    public static void registerItemComponent(Consumer<ItemComponentFactoryRegistry> consumer) {
        itemConsumers.add(consumer);
    }

    public static List<Consumer<ItemComponentFactoryRegistry>> getItemRegisterConsumers() {
        return itemConsumers;
    }

}
