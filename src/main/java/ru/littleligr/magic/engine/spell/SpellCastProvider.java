package ru.littleligr.magic.engine.spell;

public class SpellCastProvider {
    private final SpellPipe pipe;
    private float accumulatedMana = 0;
    private final float manaGoal;
    private Status status = Status.CHARGE;

    public SpellCastProvider(SpellPipe pipe) {
        this.manaGoal = pipe.getRequiredMana() ;
        this.pipe = pipe;
    }

    public boolean isManaReady() {
        return accumulatedMana >= manaGoal;
    }

    public boolean isManaEnough(float mana) {
        if (pipe.isLocked())
            return pipe.getRequiredMana() <= mana;

        return mana != 0;
    }

    public float manaTarget() {
        if (pipe.isLocked())
            return pipe.getRequiredMana();
        else return manaGoal - accumulatedMana;
    }

    public void consume(float mana) {
       accumulatedMana += mana;
       if (isManaReady()) {
           pipe.call();
           if (!pipe.isLocked())
               status = Status.READY;
       }
    }

    public boolean isCastEnd() {
        return status == Status.READY;
    }

    public enum Status {
        CHARGE,
        READY
    }
}
