package ru.littleligr.magic.engine.spell.common;

public interface ManaConsumer {
    default boolean destroyAfterUse(){ return false; }
}
