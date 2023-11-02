package ru.littleligr.magic.engine.utils;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.target.BlockPosTarget;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;

public abstract class RaycastUtil {

    public static void raycast (WizardInfo spellOwner, int range, SpellCallback callback) {
        Vec3d start = spellOwner.player().getEyePos();
        Vec3d look = spellOwner.player().getRotationVec(1.0F)
                .normalize()
                .multiply(range);
        Vec3d end = start.add(look);

        Box searchAABB = spellOwner.player().getBoundingBox().expand(range, range, range);
        var entityRaycast = ProjectileUtil.raycast(spellOwner.player(), start, end, searchAABB, (target) -> !target.isSpectator() && target.canHit(), range*range); // `range*range` is provided for squared distance comparison
        if (entityRaycast != null && entityRaycast.getEntity().isLiving())
            callback.call(new LivingEntityTarget((LivingEntity) entityRaycast.getEntity()));
        else {
            BlockHitResult blockRaycast = spellOwner.player().getWorld().raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.ANY, spellOwner.player()));
            if (blockRaycast != null)
                callback.call(new BlockPosTarget(blockRaycast.getBlockPos()));
        }
    }
}
