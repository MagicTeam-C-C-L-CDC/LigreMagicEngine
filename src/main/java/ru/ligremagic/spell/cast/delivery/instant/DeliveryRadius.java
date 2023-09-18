package ru.ligremagic.spell.cast.delivery.instant;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import ru.ligremagic.spell.common.PipeCallback;
import ru.ligremagic.spell.cast.delivery.SpellDelivery;

import java.util.*;

public record DeliveryRadius(int radius) implements SpellDelivery{

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public Optional<FX> getVisualEffect() {
        return Optional.empty();
    }


    @Override
    public void call(PlayerEntity spellOwner, PipeCallback callback) {
        List<BlockPos> blockPosList = new LinkedList<>();

        for (int x = spellOwner.getBlockPos().getX() - radius; x <= spellOwner.getBlockPos().getX() + radius; x++)
            for (int z = spellOwner.getBlockPos().getZ() - radius; z <= spellOwner.getBlockPos().getZ() + radius; z++)
                blockPosList.add(new BlockPos(x, spellOwner.getBlockPos().getY() - 1, z));

        callback.callBlockList(blockPosList);
    }

    @Override
    public void call(PlayerEntity spellOwner, BlockPos pos, PipeCallback callback) {
        List<BlockPos> blockPosList = new LinkedList<>();

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++)
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++)
                blockPosList.add(new BlockPos(x, pos.getY(), z));

        callback.callBlockList(blockPosList);
    }

    @Override
    public Optional<Identifier> getSpellCastVisualEffect() {
        return null;
    }

    @Override
    public Optional<Identifier> getSpellCastAnimation() {
        return Optional.empty();
    }

    @Override
    public void call(PlayerEntity spellOwner, Entity entity, PipeCallback callback) {
        List<BlockPos> blockPosList = new LinkedList<>();

        for (int x = entity.getBlockPos().getX() - radius; x <= entity.getBlockPos().getX() + radius; x++)
            for (int z = entity.getBlockPos().getZ() - radius; z <= entity.getBlockPos().getZ() + radius; z++)
                blockPosList.add(new BlockPos(x, entity.getBlockPos().getY() - 1, z));

        callback.callBlockList(blockPosList);
    }

}
