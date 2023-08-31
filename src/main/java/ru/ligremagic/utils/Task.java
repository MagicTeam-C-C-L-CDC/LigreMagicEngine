package ru.ligremagic.utils;

import java.util.function.Supplier;

public class Task {
    public final Supplier<Boolean> runnable;
    public final int delay;
    public int currentDelay = 0;

    Task(Supplier<Boolean> runnable, int delay) {
        this.runnable = runnable;
        this.delay = delay;
    }
}
