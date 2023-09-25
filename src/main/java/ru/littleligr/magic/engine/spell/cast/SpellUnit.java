package ru.littleligr.magic.engine.spell.cast;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import ru.littleligr.magic.engine.spell.common.PipeCallback;

import java.util.Optional;

public interface SpellUnit {
    void call(PlayerEntity spellOwner, PipeCallback callback);
    default void call(PlayerEntity spellOwner, LivingEntity target, PipeCallback callback){}
    default void call(PlayerEntity spellOwner, BlockPos pos, PipeCallback callback){}
    default float getCost() {return 0;}
    default float getCastTime() {return 0;}
    Optional<FX> getVisualEffect();

    default Optional<Identifier> getSpellCastVisualEffect() {return Optional.empty();}

}
