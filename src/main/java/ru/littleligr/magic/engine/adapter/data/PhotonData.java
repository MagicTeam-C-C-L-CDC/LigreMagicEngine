package ru.littleligr.magic.engine.adapter.data;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.AdapterData;

public class PhotonData extends AdapterData {

    public final Identifier effect;

    public PhotonData(Identifier adapter, Identifier stage, Identifier effect) {
        super(adapter, stage);
        this.effect = effect;
    }
}
