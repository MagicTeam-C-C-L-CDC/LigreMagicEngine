package ru.littleligr.magic.engine.spell.target;

import net.minecraft.entity.LivingEntity;

public class LivingEntityTarget extends Target<LivingEntity> {
    public LivingEntityTarget(LivingEntity target) {
        super(target);
    }
}
