package ru.ligremagic.spell.delivery.real;

import com.lowdragmc.photon.client.fx.EntityEffect;
import com.lowdragmc.photon.client.fx.FX;
import com.lowdragmc.photon.client.fx.FXHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.joml.Vector3f;
import ru.ligremagic.spell.common.PipeCallback;
import ru.ligremagic.spell.delivery.SpellDelivery;

import java.util.Optional;

public class DeliveryProjectile implements SpellDelivery {

    @Override
    public int getCost() {
        return 0;
    }

    @Override
    public Optional<FX> getVisualEffect() {
        return Optional.empty();
    }

    @Override
    public void call(PlayerEntity spellOwner, World world, PipeCallback callback) {
        ProjectileEntity projectile = new ProjectileEntity(EntityType.SHULKER_BULLET, world) {
            @Override
            public boolean cannotBeSilenced() {
                return super.cannotBeSilenced();
            }

            @Override
            protected void initDataTracker() {

            }

            @Override
            protected void onBlockHit(BlockHitResult blockHitResult) {
                super.onBlockHit(blockHitResult);
                callback.call(blockHitResult.getBlockPos().offset(blockHitResult.getSide()));
            }

            @Override
            protected void onEntityHit(EntityHitResult entityHitResult) {
                super.onEntityHit(entityHitResult);
                callback.call(entityHitResult.getEntity());
            }

            @Override
            public void tick() {
                HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
                if (hitResult.getType() != HitResult.Type.MISS)
                    this.onCollision(hitResult);
                this.checkBlockCollision();

                this.setPosition(this.getX() + getVelocity().x, this.getY() + getVelocity().y, this.getZ() + getVelocity().z);
            }

            @Override
            protected void onCollision(HitResult hitResult) {
                super.onCollision(hitResult);
                this.discard();
            }
        };

        //projectile.setOwner(spellOwner);
        Vector3f playerPos = spellOwner.getPos().toVector3f();
        float yaw = spellOwner.getYaw();
        float pitch = spellOwner.getPitch();

        projectile.refreshPositionAndAngles(playerPos.x, playerPos.y + 1, playerPos.z, yaw, pitch);
        projectile.setVelocity(spellOwner, pitch, yaw, 0, 5, 1);

        FX fx = FXHelper.getFX(getSpellCastVisualEffect().orElseThrow());
        EntityEffect e = new EntityEffect(fx, world, projectile);
        e.setAllowMulti(true);
        e.setForcedDeath(true);
        e.start();
        world.spawnEntity(projectile);

    }

    @Override
    public Optional<Identifier> getSpellCastVisualEffect() {
        return Optional.ofNullable(Identifier.of("photon", "fire"));
    }

    @Override
    public Optional<Identifier> getSpellCastAnimation() {
        return Optional.empty();
    }
}
