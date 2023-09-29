package ru.littleligr.magic.engine.adapter;

import net.minecraft.util.Identifier;

public class AdapterData {

    public final Identifier adapter;
    public final Identifier stage;

    public AdapterData(Identifier adapter, Identifier stage) {
        this.adapter = adapter;
        this.stage = stage;
    }
}
