package ru.littleligr.magic.engine.spell.cast.effect.terraforming.create;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import ru.littleligr.magic.engine.spell.common.PipeCallback;
import ru.littleligr.magic.engine.spell.cast.effect.SpellEffect;

import java.util.Optional;


public record EffectCreate(Block block) implements SpellEffect {

    @Override
    public void call(PlayerEntity spellOwner, PipeCallback callback) {

    }

    @Override
    public float getCost() {
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
