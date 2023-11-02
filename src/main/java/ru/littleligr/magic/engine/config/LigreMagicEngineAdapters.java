package ru.littleligr.magic.engine.config;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.adapter.data.SoundAdapterData;
import ru.littleligr.magic.engine.adapter.lme.VanillaSoundAdapter;
import ru.littleligr.magic.engine.spell.target.BlockPosTarget;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;
import ru.littleligr.magic.engine.storage.lme.Adapters;
import ru.littleligr.magic.engine.storage.vanilla.VanillaSounds;

import java.util.Arrays;
import java.util.Optional;

public abstract class LigreMagicEngineAdapters {

    public static void register(Adapters adapters) {
        adapters.register(new Identifier(LigreMagicEngine.MOD_ID, "vanilla_sound_adapter"), SoundAdapterData.class, VanillaSoundAdapter::new);
    }
}
