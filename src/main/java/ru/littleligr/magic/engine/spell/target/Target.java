package ru.littleligr.magic.engine.spell.target;

public abstract class Target<T> {
    public final T target;
    public Target(T target) {
        this.target = target;
    }
}
