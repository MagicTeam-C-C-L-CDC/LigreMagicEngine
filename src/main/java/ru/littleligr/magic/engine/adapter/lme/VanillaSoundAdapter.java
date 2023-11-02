package ru.littleligr.magic.engine.adapter.lme;

import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.adapter.data.SoundAdapterData;
import ru.littleligr.magic.engine.handler.AdapterHandler;
import ru.littleligr.magic.engine.spell.target.BlockPosTarget;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;
import ru.littleligr.magic.engine.storage.vanilla.VanillaSounds;

import java.util.Arrays;
import java.util.Optional;

public class VanillaSoundAdapter extends AdapterHandler<SoundAdapterData> {

    public VanillaSoundAdapter() {
        this.onTarget(
                BlockPosTarget.class,
                (spellOwner, box, data) -> spellOwner.player().getWorld().playSound(null, box.target, getEvent(data.sound).orElse(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK), getCategory(data.category))
        );

        this.onTarget(
                LivingEntityTarget.class,
                (spellOwner, box, data) -> spellOwner.player().getWorld().playSound(null, box.target.getBlockPos(), getEvent(data.sound).orElse(SoundEvents.BLOCK_AMETHYST_BLOCK_BREAK), getCategory(data.category))
        );
    }

    public static Optional<SoundEvent> getEvent(Identifier identifier) {
        return Optional.ofNullable(VanillaSounds.map.get(identifier));
    }

    public static SoundCategory getCategory(Identifier identifier) {
        return Arrays.stream(SoundCategory.values()).filter(c -> c.getName().equals(identifier.getPath())).findAny().orElse(SoundCategory.NEUTRAL);
    }
}
