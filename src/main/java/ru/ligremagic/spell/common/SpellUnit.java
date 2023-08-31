package ru.ligremagic.spell.common;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public interface SpellUnit {
    void call(PlayerEntity spellOwner, World world, PipeCallback callback);
    default void call(PlayerEntity spellOwner, World world, Entity target, PipeCallback callback){}
    default void call(PlayerEntity spellOwner, World world, BlockPos pos, PipeCallback callback){}
    default int getCost() {return 0;}
    default int getCastTime() {return 0;}
    Optional<FX> getVisualEffect();

    default Optional<Identifier> getSpellCastVisualEffect() {return Optional.empty();}

}
