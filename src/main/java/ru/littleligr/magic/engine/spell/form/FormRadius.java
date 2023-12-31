package ru.littleligr.magic.engine.spell.form;

import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.WizardInfo;

public class FormRadius implements SpellForm {

    int radius = 32;

    @Override
    public void call(WizardInfo spellOwner, SpellCallback callback) {

    }

/*
    @Override
    public void call(PlayerEntity spellOwner, SpellCallback callback) {
        List<BlockPos> blockPosList = new LinkedList<>();

        for (int x = spellOwner.getBlockPos().getX() - radius; x <= spellOwner.getBlockPos().getX() + radius; x++)
            for (int z = spellOwner.getBlockPos().getZ() - radius; z <= spellOwner.getBlockPos().getZ() + radius; z++)
                blockPosList.add(new BlockPos(x, spellOwner.getBlockPos().getY() - 1, z));

        //callback.callBlockList(blockPosList);
    }

    @Override
    public void call(PlayerEntity spellOwner, BlockPos pos, SpellCallback callback) {
        List<BlockPos> blockPosList = new LinkedList<>();

        for (int x = pos.getX() - radius; x <= pos.getX() + radius; x++)
            for (int z = pos.getZ() - radius; z <= pos.getZ() + radius; z++)
                blockPosList.add(new BlockPos(x, pos.getY(), z));

       // callback.callBlockList(blockPosList);
    }

    @Override
    public void call(PlayerEntity spellOwner, LivingEntity entity, SpellCallback callback) {
        List<BlockPos> blockPosList = new LinkedList<>();

        for (int x = entity.getBlockPos().getX() - radius; x <= entity.getBlockPos().getX() + radius; x++)
            for (int z = entity.getBlockPos().getZ() - radius; z <= entity.getBlockPos().getZ() + radius; z++)
                blockPosList.add(new BlockPos(x, entity.getBlockPos().getY() - 1, z));

        callback.callBlockList(blockPosList);
    }

    @Override
    public Identifier getIdentifier() {
        return identifier;
    }

 */
}
