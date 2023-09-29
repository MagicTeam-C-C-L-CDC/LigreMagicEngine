package ru.littleligr.magic.engine.spell.common;

import ru.littleligr.magic.engine.spell.target.Target;

public interface SpellCallback {
    <E, T extends Target<E>>void call(T target);
}