package ru.littleligr.magic.engine.adapter.data;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.AdapterData;

public class SoundAdapterData extends AdapterData {

    public final Identifier sound;
    public final Identifier category;

    public SoundAdapterData(Identifier stage, Identifier adapter, Identifier soundEvent, Identifier soundCategory) {
        super(adapter, stage);
        this.sound = soundEvent;
        this.category = soundCategory;
    }

    @Override
    public String toString() {
        return "SoundAdapterData{" +
                "sound=" + sound +
                ", category=" + category +
                ", adapter=" + adapter +
                ", stage='" + stage + '\'' +
                '}';
    }
}
