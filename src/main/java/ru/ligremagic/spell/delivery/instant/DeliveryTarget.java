package ru.ligremagic.spell.delivery.instant;


import com.lowdragmc.photon.client.fx.FX;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import ru.ligremagic.spell.common.PipeCallback;
import ru.ligremagic.spell.delivery.SpellDelivery;

import java.util.Optional;

public class DeliveryTarget implements SpellDelivery {

    private final int range;

    public DeliveryTarget(int range) {
        this.range = range;
    }

    @Override
    public void call(PlayerEntity spellOwner, World world, PipeCallback callback) {
        Vec3d start = spellOwner.getEyePos();
        Vec3d look = spellOwner.getRotationVec(1.0F)
                .normalize()
                .multiply(range);
        Vec3d end = start.add(look);

        BlockHitResult blockRaycast = world.raycast(new RaycastContext(start, end, RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, spellOwner));
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

    @Override
    public Optional<FX> getVisualEffect() {
        return Optional.empty();
    }

    @Override
    public Optional<Identifier> getSpellCastVisualEffect() {
        return Optional.empty();
    }

    @Override
    public Optional<Identifier> getSpellCastAnimation() {
        return Optional.empty();
    }
}
