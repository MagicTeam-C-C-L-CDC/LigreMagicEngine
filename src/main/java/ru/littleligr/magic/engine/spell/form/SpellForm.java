package ru.littleligr.magic.engine.spell.form;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import ru.littleligr.magic.engine.spell.common.SpellCallback;

import java.util.Optional;

public interface SpellForm {

    void call(PlayerEntity spellOwner, SpellCallback callback);

    default Optional<Identifier> getSpellCastAnimation() {return Optional.empty();}
}