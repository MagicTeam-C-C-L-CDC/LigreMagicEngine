package ru.ligremagic.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import ru.ligremagic.spell.common.SpellUnit;
import ru.ligremagic.spell.common.SpellLock;
import ru.ligremagic.spell.delivery.SpellDelivery;

import java.util.LinkedList;
import java.util.List;


public class Spell{

    protected SpellDelivery startUnit;
    protected List<SpellUnit> units;

    protected SpellCastProvider castProvider;

    public Spell(SpellDelivery start, SpellUnit...units) {
        this.startUnit = start;
        this.units = List.of(units);
    }

    /**
     * TO-DO
     * ADD CLASS WITH FUNCTIONAL OF CHAIN ANALYZE
     * DO AFTER COMPLETE ALL MAGIC SPELLS
     */
    private void checkPipe() {}

    public void call(PlayerEntity spellOwner, World world) {
        if (castProvider == null || !(startUnit instanceof SpellLock)) {
            castProvider = new SpellCastProvider(startUnit, units);

           // KeyframeAnimation anim = PlayerAnimationRegistry.getAnimation(startUnit.getSpellCastAnimation().orElseThrow());
        }
        else castProvider.call();
    }
}