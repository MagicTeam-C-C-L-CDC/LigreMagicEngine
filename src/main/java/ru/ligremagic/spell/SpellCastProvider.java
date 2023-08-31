package ru.ligremagic.spell;

import ru.ligremagic.spell.common.SpellLock;
import ru.ligremagic.spell.common.SpellUnit;
import ru.ligremagic.spell.delivery.SpellDelivery;

import java.util.List;

public class SpellCastProvider {

    protected final List<SpellUnit> units;
    protected final SpellDelivery head;

    protected double accumulatedMana = 0;

    public SpellCastProvider(SpellDelivery head, List<SpellUnit> units) {
        this.units = units;
        this.head = head;
    }

    public int getManaCost() {
        return units.stream().map(SpellUnit::getCost).reduce(Integer::sum).orElse(0);
    }

    public Boolean isLock () {
        if (head == null) return false;
        return head instanceof SpellLock;
    }

    public void call () {

    }
}
