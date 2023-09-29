package ru.littleligr.magic.engine.spell;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.SpellLock;
import ru.littleligr.magic.engine.spell.form.SpellForm;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;
import ru.littleligr.magic.engine.spell.target.Target;

public class SpellCastProvider implements SpellCallback{
    PlayerEntity spellOwner;
    Spell spell;
    SpellForm form;
    private float accumulatedMana = 0;
    private int consumeCounter = 0;
    private final float manaGoal;
    private Status status = Status.CHARGE;

    public SpellCastProvider(PlayerEntity spellOwner, SpellForm form, Spell spell, float manaGoal) {
        this.form = form;
        this.spell = spell;
        this.manaGoal = manaGoal;
        this.spellOwner = spellOwner;
    }

    public boolean isManaReady() {
        return accumulatedMana >= manaGoal;
    }

    public boolean isManaEnough(float mana) {
        if (form instanceof SpellLock)
            return manaGoal <= mana;

        return mana != 0;
    }

    public float manaTarget() {
        if (form instanceof SpellLock)
            return manaGoal;
        else return manaGoal - accumulatedMana;
    }

    public void consume(float mana) {
        consumeCounter++;

        if (consumeCounter == 1)
            spell.onStage(spellOwner, SpellStage.CAST.identifier, new LivingEntityTarget(spellOwner));

        accumulatedMana += mana;
        if (isManaReady()) {
            form.call(spellOwner, this);
            if (!(form instanceof SpellLock)) {
                status = Status.READY;
                spell.onStage(spellOwner, SpellStage.RELEASE.identifier, new LivingEntityTarget(spellOwner));
            }
        }
    }

    public boolean isCastEnd() {
        return status == Status.READY;
    }

    @Override
    public <E, T extends Target<E>> void call(T target) {
        spell.onStage(spellOwner, SpellStage.TARGET.identifier, target);
    }

    public enum Status {
        CHARGE,
        READY
    }
}
