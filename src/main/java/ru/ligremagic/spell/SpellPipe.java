package ru.ligremagic.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import ru.ligremagic.spell.common.SpellUnit;
import ru.ligremagic.spell.common.PipeCallback;

import java.util.LinkedList;
import java.util.List;

public class SpellPipe implements PipeCallback{

    private final PlayerEntity spellOwner;
    private final World world;

    private final LinkedList<SpellUnit> pipe;


    public SpellPipe(PlayerEntity spellOwner, World world, LinkedList<SpellUnit> pipe) {
        this.spellOwner = spellOwner;
        this.world = world;
        this.pipe = pipe;
    }

    @Override
    public void call(BlockPos target) {
        if (pipe.isEmpty())
            return;

        pipe.removeFirst().call(spellOwner, world, target, new SpellPipe(spellOwner, world, new LinkedList<>(pipe)));
    }

    @Override
    public void call(Entity target) {
        if (pipe.isEmpty())
            return;

        pipe.removeFirst().call(spellOwner, world, target, new SpellPipe(spellOwner, world, new LinkedList<>(pipe)));
    }

    @Override
    public void callEntityList(List<Entity> targets) {
        if (pipe.isEmpty())
            return;

        SpellUnit unit = pipe.removeFirst();
        targets.forEach(x -> unit.call(spellOwner, world, x, new SpellPipe(spellOwner, world, new LinkedList<>(pipe))));
    }

    @Override
    public void callBlockList(List<BlockPos> targets) {
        if (pipe.isEmpty())
            return;

        SpellUnit unit = pipe.removeFirst();
        targets.forEach(x -> unit.call(spellOwner, world, x, new SpellPipe(spellOwner, world, new LinkedList<>(pipe))));
    }

    @Override
    public void call() {
        if(!pipe.isEmpty())
            pipe.removeFirst().call(spellOwner, world, this);
    }
}
