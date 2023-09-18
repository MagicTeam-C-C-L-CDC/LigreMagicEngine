package ru.ligremagic.spell.cast.delivery.real;

import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import ru.ligremagic.spell.common.PipeCallback;
import ru.ligremagic.spell.common.SpellLock;
import ru.ligremagic.spell.cast.delivery.SpellDelivery;

import java.util.Optional;

public class DeliveryFlow implements SpellDelivery, SpellLock {

    private final int range;

    public DeliveryFlow(int range) {
        this.range = range;
    }

    @Override
    public void call(PlayerEntity spellOwner, PipeCallback callback) {
        Vec3d start = spellOwner.getEyePos();
        Vec3d look = spellOwner.getRotationVec(1.0F)
                .normalize()
                .multiply(range);
        Vec3d end = start.add(look);

        BlockHitResult blockRaycast = spellOwner.getWorld().raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, spellOwner));
        if (blockRaycast == null) {
            Box searchAABB = spellOwner.getBoundingBox().expand(range, range, range);
            var entityRaycast = ProjectileUtil.raycast(spellOwner, start, end, searchAABB, (target) -> !target.isSpectator() && target.canHit(), range*range); // `range*range` is provided for squared distance comparison
            if (entityRaycast != null)
                callback.call(entityRaycast.getEntity());
        }
        else
            callback.call(blockRaycast.getBlockPos());
    }

    @Override
    public int getCost() {
        return 0;
    }



    private static boolean raycastObstacleFree(Entity entity, Vec3d start, Vec3d end) {
        var hit = entity.getWorld().raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, entity));
        return hit.getType() != HitResult.Type.BLOCK;
    }

    @Override
    public Optional<FX> getVisualEffect() {
        return null;
    }

    @Override
    public Optional<Identifier> getSpellCastVisualEffect() {
        return null;
    }

    @Override
    public Optional<Identifier> getSpellCastAnimation() {
        return Optional.empty();
    }
}
