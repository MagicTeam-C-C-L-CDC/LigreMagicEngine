package ru.littleligr.magic.engine.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.littleligr.magic.engine.config.Components;

@Environment(EnvType.SERVER)
@Mixin(LivingEntity.class)
public class LivingEntityMixin {

    @Unique
    private LivingEntity entity;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void init(EntityType entityType, World world, CallbackInfo ci) {
        entity = (LivingEntity)(Object)this;
    }

    @Inject(method = "onEquipStack", at = @At("HEAD"))
    public void equipStack(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack, CallbackInfo ci) {
        if (entity instanceof PlayerEntity)
            Components.getMagicDataComponent((PlayerEntity) entity).ifPresent(md -> md.equipment.addEquipment(slot, oldStack, newStack));
    }
}
