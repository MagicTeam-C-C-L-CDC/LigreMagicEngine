package ru.littleligr.magic.engine.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.target.BlockPosTarget;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;

public abstract class RaycastUtil {

    public static void raycast (PlayerEntity spellOwner, int range, SpellCallback callback) {
        Vec3d start = spellOwner.getEyePos();
        Vec3d look = spellOwner.getRotationVec(1.0F)
                .normalize()
                .multiply(range);
        Vec3d end = start.add(look);

        Box searchAABB = spellOwner.getBoundingBox().expand(range, range, range);
        var entityRaycast = ProjectileUtil.raycast(spellOwner, start, end, searchAABB, (target) -> !target.isSpectator() && target.canHit(), range*range); // `range*range` is provided for squared distance comparison
        if (entityRaycast != null && entityRaycast.getEntity().isLiving())
            callback.call(new LivingEntityTarget((LivingEntity) entityRaycast.getEntity()));
        else {
            BlockHitResult blockRaycast = spellOwner.getWorld().raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, spellOwner));
            if (blockRaycast != null)
                callback.call(new BlockPosTarget(blockRaycast.getBlockPos()));
        }
    }
}
