package ru.littleligr.magic.engine.config;

import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.target.BlockPosTarget;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;
import ru.littleligr.magic.engine.storage.Effects;

public abstract class LigeMagicEngineEffects {

    public static void register() {
        if (!LigreMagicEngine.CONFIG.spells.registerLigreMagicEngineSpells()) return;

        Effects.register(new Identifier(LigreMagicEngine.MOD_ID, "effect_blind"), register -> {
            register.onTarget(
                    LivingEntityTarget.class,
                    (spellOwner, target) -> target.target.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS), spellOwner)
            );
        });

        Effects.register(new Identifier(LigreMagicEngine.MOD_ID, "effect_create_block"), register -> {
            register.onTarget(
                    BlockPosTarget.class,
                    (spellOwner, box) -> spellOwner.getWorld().setBlockState(box.target, Blocks.STONE.getDefaultState())
            );

            register.onTarget(
                    LivingEntityTarget.class,
                    (spellOwner, box) -> spellOwner.getWorld().setBlockState(box.target.getBlockPos(), Blocks.STONE.getDefaultState())
            );
        });

        Effects.register(new Identifier(LigreMagicEngine.MOD_ID, "cleansing_effect"), register -> {
            register.onTarget(
                    LivingEntityTarget.class,
                    (playerEntity, target) -> target.target.clearStatusEffects()
            );
        });

        Effects.register(new Identifier(LigreMagicEngine.MOD_ID, "shackling_effect"), register -> {
            register.onTarget(
                    LivingEntityTarget.class,
                    (playerEntity, target) -> target.target.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS), playerEntity)
            );
        });

        Effects.register(new Identifier(LigreMagicEngine.MOD_ID, "levitation_effect"), register -> {
            register.onTarget(
                    LivingEntityTarget.class,
                    (playerEntity, target) -> target.target.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 30, 1), playerEntity)
            );
        });
    }

}
