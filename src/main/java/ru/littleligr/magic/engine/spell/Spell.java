package ru.littleligr.magic.engine.spell;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.common.EffectData;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.utils.StagedList;

import java.util.List;

public class Spell {

    public final Identifier texture;
    public final float cost;
    private final List<EffectData> effects;
    private final StagedList components;
    private final StagedList rules;

    public Spell(Identifier texture, float cost, List<EffectData> effects, StagedList components, StagedList rules) {
        this.texture = texture;
        this.cost = cost;
        this.effects = effects;
        this.components = components;
        this.rules = rules;
    }

    public <E, T extends Target<E>> void onStage(WizardInfo spellOwner, Identifier stage, T target) {
        LigreMagicEngine.EFFECTS.accept(effects.stream().filter(effectData -> effectData.stage.equals(stage)), spellOwner, target);
        LigreMagicEngine.ADAPTERS.accept(components.filter(stage), spellOwner, target);
    }

    @Override
    public String toString() {
        return "Spell{" +
                "cost=" + cost +
                ", effects=" + effects +
                ", components=" + components +
                ", rules=" + rules +
                '}';
    }

}
