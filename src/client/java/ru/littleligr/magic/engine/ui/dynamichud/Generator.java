package ru.littleligr.magic.engine.ui.dynamichud;

@FunctionalInterface
public interface Generator<T> {
    T get();
}
