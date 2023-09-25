package ru.littleligr.magic.engine.spell.common;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface PipeCallback {
    void call(BlockPos target);

    void call(LivingEntity target);

    void callEntityList(List<LivingEntity> targets);
    void callBlockList(List<BlockPos> targets);

    void call();
}