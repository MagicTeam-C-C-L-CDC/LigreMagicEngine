package ru.littleligr.magic.engine.mixin;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.littleligr.magic.engine.spell.PlayerMagicData;
import ru.littleligr.magic.engine.spell.common.IMagicData;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMagicDataMixin implements IMagicData {
    @Unique
    private PlayerMagicData spellData;

    @Unique
    private World ligreMagic$World;

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void init(World world, BlockPos pos, float yaw, GameProfile gameProfile, CallbackInfo ci) {
        spellData = new PlayerMagicData(((PlayerEntity)(Object)this));
        ligreMagic$World = world;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        if (!ligreMagic$World.isClient())
            spellData.tick();
    }

    @Override
    public PlayerMagicData getPlayerMagicData() {
        return spellData;
    }
}
