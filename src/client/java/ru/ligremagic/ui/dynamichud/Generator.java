package ru.ligremagic.ui;

@FunctionalInterface
public interface Generator<T> {
    T get();
}
