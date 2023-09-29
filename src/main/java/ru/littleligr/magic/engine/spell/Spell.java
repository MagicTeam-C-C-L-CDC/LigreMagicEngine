package ru.littleligr.magic.engine.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.spell.common.EffectData;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.storage.Adapters;
import ru.littleligr.magic.engine.storage.Effects;

import java.util.List;

public class Spell {
    public final float cost;

    private List<EffectData> effects;
    private List<AdapterData> sounds;
    private List<AdapterData> visuals;
    private List<AdapterData> rules;

    public Spell(float cost) {
        this.cost = cost;
    }

    public <E, T extends Target<E>> void onStage(PlayerEntity spellOwner, Identifier stage, T target) {
        LigreMagicEngine.LOGGER.info(spellOwner + " " + stage + " " + target.target.toString());
        effects.stream().filter(ed -> ed.stage.equals(stage)).forEach(effect -> Effects.tryAcceptHandler(effect.effect, spellOwner, target));
        sounds.stream().filter(sd -> sd.stage.equals(stage)).forEach(sd -> Adapters.tryAcceptHandler(sd.adapter, spellOwner, target, sd));
        visuals.stream().filter(sd -> sd.stage.equals(stage)).forEach(sd -> Adapters.tryAcceptHandler(sd.adapter, spellOwner, target, sd));
    }

    @Override
    public String toString() {
        return "Spell{" +
                "cost=" + cost +
                ", effects=" + effects +
                ", sounds=" + sounds +
                ", visuals=" + visuals +
                ", rules=" + rules +
                '}';
    }

}
