package ru.littleligr.magic.engine.spell;

import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.SpellLock;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.form.SpellForm;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;
import ru.littleligr.magic.engine.spell.target.Target;
import ru.littleligr.magic.engine.storage.info.FormInfo;

public class SpellCastProvider implements SpellCallback{
    WizardInfo spellOwner;
    Spell spell;
    SpellForm form;
    private float accumulatedMana = 0;
    private int consumeCounter = 0;
    private final float manaGoal;
    private Status status = Status.CHARGE;

    public boolean castBlock = false;

    public SpellCastProvider(WizardInfo spellOwner, FormInfo formInfo, Spell spell) {
        this.form = formInfo.form();
        this.spell = spell;
        this.spellOwner = spellOwner;
        this.manaGoal = spell.cost + formInfo.formData().cost;
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
            spell.onStage(spellOwner, SpellStage.CAST.identifier, new LivingEntityTarget(spellOwner.player()));

        accumulatedMana += mana;
        if (isManaReady()) {
            form.call(spellOwner, this);
            if (!(form instanceof SpellLock)) {
                status = Status.READY;
                spell.onStage(spellOwner, SpellStage.RELEASE.identifier, new LivingEntityTarget(spellOwner.player()));
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

    @Override
    public String toString() {
        return "SpellCastProvider{" +
                "spellOwner=" + spellOwner +
                ", spell=" + spell +
                ", form=" + form +
                ", accumulatedMana=" + accumulatedMana +
                ", consumeCounter=" + consumeCounter +
                ", manaGoal=" + manaGoal +
                ", status=" + status +
                '}';
    }

    public enum Status {
        CHARGE,
        READY
    }
}
