package ru.littleligr.magic.engine.adapter;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.common.SpellCallback;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class Adapter<T extends AdapterData> implements AdapterCallback<T> {
    public Type getAdapterDataClass() {
        ParameterizedType superclass =
                (ParameterizedType) getClass().getGenericSuperclass();
        return superclass.getActualTypeArguments()[0];
    }
}
