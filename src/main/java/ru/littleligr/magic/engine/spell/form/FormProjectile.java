package ru.littleligr.magic.engine.spell.form;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.joml.Vector3f;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.target.BlockPosTarget;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;

public class FormProjectile implements SpellForm {
    Identifier identifier = new Identifier(LigreMagicEngine.MOD_ID, "form_projectile");

    @Override
    public void call(PlayerEntity spellOwner, SpellCallback callback) {
        ProjectileEntity projectile = new ProjectileEntity(EntityType.SHULKER_BULLET, spellOwner.getWorld()) {
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
                callback.call(new BlockPosTarget(blockHitResult.getBlockPos().offset(blockHitResult.getSide())));
            }

            @Override
            protected void onEntityHit(EntityHitResult entityHitResult) {
                super.onEntityHit(entityHitResult);
                Entity entity = entityHitResult.getEntity();
                if (entity.isLiving())
                    callback.call(new LivingEntityTarget((LivingEntity) entity));
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

        //FX fx = FXHelper.getFX(getSpellCastVisualEffect().orElseThrow());
        //EntityEffect e = new EntityEffect(fx, spellOwner.getWorld(), projectile);
        //e.setAllowMulti(true);
        //e.setForcedDeath(true);
        //e.start();
        spellOwner.getWorld().spawnEntity(projectile);

    }
}
