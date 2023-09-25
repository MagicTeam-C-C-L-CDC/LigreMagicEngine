package ru.littleligr.magic.engine.spell;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import ru.littleligr.magic.engine.spell.common.SpellLock;
import ru.littleligr.magic.engine.spell.cast.SpellUnit;
import ru.littleligr.magic.engine.spell.common.PipeCallback;
import ru.littleligr.magic.engine.spell.cast.form.SpellDelivery;

import java.util.LinkedList;
import java.util.List;

public class SpellPipe implements PipeCallback {

    private final PlayerEntity spellOwner;

    private final LinkedList<SpellUnit> pipe;

    public SpellPipe(PlayerEntity spellOwner, LinkedList<SpellUnit> pipe) {
        this.spellOwner = spellOwner;
        this.pipe = pipe;
    }

    public SpellPipe(PlayerEntity spellOwner, SpellDelivery delivery, List<SpellUnit> pipe) {
        this.spellOwner = spellOwner;
        this.pipe = new LinkedList<>(pipe);
        this.pipe.addFirst(delivery);
    }
    @Override
    public void call(BlockPos target) {
        if (pipe.isEmpty())
            return;

        pipe.removeFirst().call(spellOwner, target, this);
    }

    @Override
    public void call(LivingEntity target) {
        if (pipe.isEmpty())
            return;

        pipe.removeFirst().call(spellOwner, target, this);
    }

    @Override
    public void callEntityList(List<LivingEntity> targets) {
        if (pipe.isEmpty())
            return;

        SpellUnit unit = pipe.removeFirst();
        targets.forEach(x -> unit.call(spellOwner, x, copyPipe(false)));

    }

    @Override
    public void callBlockList(List<BlockPos> targets) {
        if (pipe.isEmpty())
            return;

        SpellUnit unit = pipe.removeFirst();
        targets.forEach(x -> unit.call(spellOwner, x, copyPipe(false)));

    }

    @Override
    public void call() {
        if(pipe.isEmpty())
            return;

        if (pipe.getFirst() instanceof SpellLock)
            pipe.getFirst().call(spellOwner, copyPipe(true));
        else pipe.removeFirst().call(spellOwner, this);
    }

    public Boolean isLocked() {
        if (pipe.isEmpty())
            return false;
        return pipe.getFirst() instanceof SpellLock;
    }

    public float getRequiredMana() {
        if (pipe.isEmpty())
            return 0;
        else return pipe.stream().map(SpellUnit::getCost).reduce(Float::sum).orElse(0f);
    }

    private SpellPipe copyPipe(boolean shift) {
        if (shift)
            return new SpellPipe(spellOwner, new LinkedList<>(pipe.subList(1, pipe.size())));
        else return new SpellPipe(spellOwner, new LinkedList<>(pipe));
    }
}
