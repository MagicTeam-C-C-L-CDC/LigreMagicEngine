package ru.ligremagic.spell.common;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

import java.util.Optional;

public interface SpellUnit {
    void call(PlayerEntity spellOwner, PipeCallback callback);
    default void call(PlayerEntity spellOwner, Entity target, PipeCallback callback){}
    default void call(PlayerEntity spellOwner, BlockPos pos, PipeCallback callback){}
    default int getCost() {return 0;}
    default int getCastTime() {return 0;}
    Optional<FX> getVisualEffect();

    default Optional<Identifier> getSpellCastVisualEffect() {return Optional.empty();}

}
