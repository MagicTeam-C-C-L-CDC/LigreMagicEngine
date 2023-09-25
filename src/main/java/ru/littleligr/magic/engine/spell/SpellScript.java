package ru.littleligr.magic.engine.spell;

import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.spell.cast.form.SpellDelivery;
import ru.littleligr.magic.engine.spell.cast.SpellUnit;

import java.util.List;


public class SpellScript {
    protected List<SpellUnit> units;

    public SpellScript(SpellUnit...units) {
        this.units = List.of(units);
    }

    /**
     * TO-DO
     * ADD CLASS WITH FUNCTIONAL OF CHAIN ANALYZE
     * DO AFTER COMPLETE ALL MAGIC SPELLS
     */
    private void checkPipe() {}

    public SpellCastProvider createSpellCastProvider(SpellDelivery startUnit, PlayerEntity spellOwner) {
        return new SpellCastProvider(new SpellPipe(spellOwner, startUnit, units));
    }
}