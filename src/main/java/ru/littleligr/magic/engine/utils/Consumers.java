package ru.littleligr.magic.engine.utils;

public abstract class Consumers {
    @FunctionalInterface
    public interface TupleConsumer<T, E> {
        void accept(T t, E e);
    }

    @FunctionalInterface
    public interface TripleConsumer<T, E, F> {
        void accept(T t, E e, F f);
    }

}
