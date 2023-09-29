package ru.littleligr.magic.engine.config;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.adapter.data.SoundAdapterData;
import ru.littleligr.magic.engine.spell.target.BlockPosTarget;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;
import ru.littleligr.magic.engine.storage.Adapters;
import ru.littleligr.magic.engine.storage.SoundEventsMap;

import java.util.Arrays;

public abstract class LigreMagicEngineAdapters {

    public static void register() {

        Adapters.register(new Identifier(LigreMagicEngine.MOD_ID, "vanilla_sound_adapter"), SoundAdapterData.class, register -> {
            register.onTarget(
                    BlockPosTarget.class,
                    (spellOwner, box, data) -> spellOwner.getWorld().playSound(null, box.target, getEvent(data.sound), getCategor(data.category))
            );

            register.onTarget(
                    LivingEntityTarget.class,
                    (spellOwner, box, data) -> spellOwner.getWorld().playSound(null, box.target.getBlockPos(), getEvent(data.sound), getCategor(data.category))
            );
        });
    }

    public static SoundEvent getEvent(Identifier identifier) {
        return SoundEventsMap.map.get(identifier);
    }

    public static SoundCategory getCategor(Identifier identifier) {
        return Arrays.stream(SoundCategory.values()).filter(c -> c.getName().equals(identifier.getPath())).findAny().orElse(SoundCategory.NEUTRAL);
    }

}
