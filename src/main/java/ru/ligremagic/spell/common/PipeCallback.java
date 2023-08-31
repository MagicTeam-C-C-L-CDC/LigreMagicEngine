package ru.ligremagic.spell.common;

import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;

import java.util.List;

public interface PipeCallback {
    void call(BlockPos target);

    void call(Entity target);

    void callEntityList(List<Entity> targets);
    void callBlockList(List<BlockPos> targets);

    void call();
}