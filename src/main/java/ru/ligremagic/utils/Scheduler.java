package ru.ligremagic.utils;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

public abstract class Scheduler {
    private static final LinkedList<Task> tasks = new LinkedList<>();

    private static void schedule(Supplier<Boolean> task, int delay) {
        tasks.add(new Task(task, delay));
    }

    public static List<Task> getTasks() {
        return tasks.stream().toList();
    }

    public static void remove(Task task) {
        tasks.remove(task);
    }
}
