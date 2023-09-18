package ru.ligremagic.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.ligremagic.LigreMagic;
import ru.ligremagic.spell.cast.delivery.real.DeliveryProjectile;
import ru.ligremagic.spell.common.SpellLock;
import ru.ligremagic.spell.common.SpellUnit;
import ru.ligremagic.spell.common.PipeCallback;
import ru.ligremagic.spell.cast.delivery.SpellDelivery;

import java.util.LinkedList;
import java.util.List;

public class SpellPipe implements PipeCallback{

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

        pipe.removeFirst().call(spellOwner, target, new SpellPipe(spellOwner, new LinkedList<>(pipe)));
    }

    @Override
    public void call(Entity target) {
        if (pipe.isEmpty())
            return;

        pipe.removeFirst().call(spellOwner, target, new SpellPipe(spellOwner, new LinkedList<>(pipe)));
    }

    @Override
    public void callEntityList(List<Entity> targets) {
        if (pipe.isEmpty())
            return;

        SpellUnit unit = pipe.removeFirst();
        targets.forEach(x -> unit.call(spellOwner, x, new SpellPipe(spellOwner, new LinkedList<>(pipe))));
    }

    @Override
    public void callBlockList(List<BlockPos> targets) {
        if (pipe.isEmpty())
            return;

        SpellUnit unit = pipe.removeFirst();
        targets.forEach(x -> unit.call(spellOwner, x, new SpellPipe(spellOwner, new LinkedList<>(pipe))));
    }

    @Override
    public void call() {
        if(!pipe.isEmpty()) {
            LigreMagic.LOGGER.info(String.valueOf(pipe.getFirst() instanceof DeliveryProjectile));
            pipe.removeFirst().call(spellOwner, this);
        }
    }

    public Boolean isLocked() {
        if (pipe.isEmpty())
            return false;
        return pipe.getFirst() instanceof SpellLock;
    }
}
