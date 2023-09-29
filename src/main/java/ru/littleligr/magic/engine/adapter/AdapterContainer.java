package ru.littleligr.magic.engine.adapter;

import net.minecraft.util.Identifier;

public class AdapterContainer {
    public final Identifier adapter;
    public final String stage;
    public final String instance;

    public AdapterContainer(Identifier adapter, String stage, String instance) {
        this.adapter = adapter;
        this.stage = stage;
        this.instance = instance;
    }
}
