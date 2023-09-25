package ru.littleligr.magic.engine.spell.common;

public class SpellCharger {
    public int accumulatedMana = 0;
    public final int manaGoal;

    public SpellCharger(int manaGoal) {
        this.manaGoal = manaGoal;
    }

    public boolean consumeMana (int mana) {
        accumulatedMana += mana;
        return accumulatedMana >= manaGoal;
    }

    public boolean ready () {
        return accumulatedMana >= manaGoal;
    }
}
