package ru.littleligr.magic.engine.spell.common;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.spell.SpellStage;

public class EffectData {
    public final Identifier effect;
    public final  Identifier stage;

    public EffectData(Identifier effect, Identifier stage) {
        this.effect = effect;
        this.stage = stage;
    }

    public EffectData(Identifier effect) {
        this.effect = effect;
        this.stage = SpellStage.TARGET.identifier;
    }
}
