package ru.ligremagic.item.tools;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.UseAction;
import ru.ligremagic.spell.SpellScript;
import ru.ligremagic.spell.cast.delivery.instant.DeliveryRadius;
import ru.ligremagic.spell.cast.delivery.real.DeliveryProjectile;
import ru.ligremagic.spell.cast.effect.terraforming.create.EffectCreate;
import ru.ligremagic.spell.mana.ManaConsumer;
import ru.ligremagic.spell.script.SpellScriptContainer;

import java.util.Optional;

public class Staff extends SwordItem implements ManaConsumer{
    public Staff(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }



    @Override
    public UseAction getUseAction(ItemStack stack) {
        return super.getUseAction(stack);
    }
}
