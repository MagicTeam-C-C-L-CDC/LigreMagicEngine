package ru.littleligr.magic.engine.adapter;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import ru.littleligr.magic.engine.spell.target.Target;

public interface AdapterCallback<T> {
    <E, F extends Target<E>> void call(PlayerEntity spellOwner, F target, T data);
}
