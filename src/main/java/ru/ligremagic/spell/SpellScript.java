package ru.ligremagic.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import ru.ligremagic.spell.cast.delivery.SpellDelivery;
import ru.ligremagic.spell.common.SpellUnit;

import java.util.List;


public class SpellScript {

    protected SpellDelivery startUnit;
    protected List<SpellUnit> units;


    public SpellScript(SpellDelivery start, SpellUnit...units) {
        this.startUnit = start;
        this.units = List.of(units);
    }

    /**
     * TO-DO
     * ADD CLASS WITH FUNCTIONAL OF CHAIN ANALYZE
     * DO AFTER COMPLETE ALL MAGIC SPELLS
     */
    private void checkPipe() {}

    public int getManaCost() {
        return startUnit.getCost() + units.stream().map(SpellUnit::getCost).reduce(Integer::sum).orElse(0);
    }

    public SpellPipe createSpellPipe(PlayerEntity spellOwner) {
        return new SpellPipe(spellOwner, startUnit, units);
    }
}