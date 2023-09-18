package ru.ligremagic.item.tools;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;
import ru.ligremagic.block.RedGem;
import ru.ligremagic.item.ItemInit;

public class Manuscript extends Item{
    public Manuscript(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient && context.getWorld().getBlockState(context.getBlockPos()).getBlock() instanceof RedGem) {
            ItemEntity magicscript = new ItemEntity(
                    context.getWorld(),
                    context.getBlockPos().getX(),
                    context.getBlockPos().getY(),
                    context.getBlockPos().getZ(),
                    new ItemStack(ItemInit.MANUSCRIPT_MAGIC)
            );

            context.getWorld().setBlockState(context.getBlockPos(), Blocks.AIR.getDefaultState());
            context.getWorld().spawnEntity(magicscript);
        }
        return super.useOnBlock(context);
    }
}
