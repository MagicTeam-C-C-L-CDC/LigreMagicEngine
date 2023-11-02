package ru.littleligr.magic.engine.spell.target;

import net.minecraft.entity.LivingEntity;
import ru.littleligr.magic.engine.spell.common.WizardInfo;

public class LivingEntityTarget extends Target<LivingEntity> {
    public LivingEntityTarget(LivingEntity target) {
        super(target);
    }
}
