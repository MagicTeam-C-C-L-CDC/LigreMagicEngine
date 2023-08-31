package ru.ligremagic.item.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import ru.ligremagic.spell.Spell;
import ru.ligremagic.spell.delivery.instant.DeliveryRadius;
import ru.ligremagic.spell.delivery.real.DeliveryProjectile;
import ru.ligremagic.spell.effect.terraforming.create.EffectCreate;

public class Staff extends SwordItem {
    public Staff(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    //Spell spell = new Spell(new DeliveryFlow(32), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));
    Spell spell = new Spell(new DeliveryProjectile(), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));
    //Spell spell = new Spell(new DeliveryTarget(32), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient)
            spell.call(user, world);

        return super.use(world, user, hand);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return super.getUseAction(stack);
    }
}
