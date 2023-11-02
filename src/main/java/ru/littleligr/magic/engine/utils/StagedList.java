package ru.littleligr.magic.engine.utils;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.AdapterData;

import java.util.List;
import java.util.stream.Stream;

public class StagedList {
    private final List<AdapterData> list;

    public StagedList(List<AdapterData> list) {
        this.list = list;
    }

    public Stream<AdapterData> filter(Identifier stage) {
        return list.stream().filter(t -> t.filter(stage));
    }
}
