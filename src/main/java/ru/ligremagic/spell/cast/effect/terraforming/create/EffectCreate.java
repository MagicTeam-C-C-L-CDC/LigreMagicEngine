package ru.ligremagic.spell.cast.effect.terraforming.create;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import ru.ligremagic.spell.common.PipeCallback;
import ru.ligremagic.spell.cast.effect.SpellEffect;

import java.util.Optional;


public record EffectCreate(Block block) implements SpellEffect {

    @Override
    public void call(PlayerEntity spellOwner, PipeCallback callback) {

    }

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public Optional<FX> getVisualEffect() {
        return Optional.empty();
    }


    @Override
    public void call(PlayerEntity spellOwner, BlockPos pos, PipeCallback callback) {
        spellOwner.getWorld().setBlockState(pos, block.getDefaultState());
        callback.call(pos);
    }
}
