package ru.littleligr.magic.engine.spell.common;

import net.minecraft.entity.player.PlayerEntity;
import ru.littleligr.magic.engine.spell.mana.ManaConsumer;

public record WizardInfo(PlayerEntity player, ManaConsumer manaConsumer) {
}
