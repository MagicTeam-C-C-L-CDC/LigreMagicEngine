package ru.littleligr.magic.engine.adapter;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.spell.common.StageComponent;

public class AdapterData implements StageComponent {
    public static final String  ADAPTER_FIELD_KEY = "adapter";

    public final Identifier adapter;
    public final Identifier stage;

    public AdapterData(Identifier adapter, Identifier stage) {
        this.adapter = adapter;
        this.stage = stage;
    }

    @Override
    public boolean filter(Identifier stage) {
        return this.stage.equals(stage);
    }
}
