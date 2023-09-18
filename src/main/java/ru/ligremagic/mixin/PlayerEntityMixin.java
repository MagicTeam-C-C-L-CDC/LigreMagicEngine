package ru.ligremagic.mixin;

import com.mojang.authlib.GameProfile;
import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.ligremagic.animations.IExampleAnimatedPlayer;
import ru.ligremagic.animations.PlayerAnimator;
import ru.ligremagic.spell.PlayerMagicData;
import ru.ligremagic.spell.mana.IMagicPlayer;
import ru.ligremagic.spell.PlayerMagicManager;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin implements IExampleAnimatedPlayer, IMagicPlayer {

    //Unique annotation will rename private methods/fields if needed to avoid collisions.
    @Unique
    private final ModifierLayer<IAnimation> modAnimationContainer = new ModifierLayer<>();
    @Unique
    private PlayerMagicData spellData;

    @Unique
    private PlayerAnimator playerAnimator;

    @Unique
    private PlayerMagicManager manaManager;

    /**
     * Add the animation registration to the end of the constructor
     * Or you can use {@link PlayerAnimationAccess#REGISTER_ANIMATION_EVENT} event for this
     */


    @Inject(method = "<init>", at = @At(value = "RETURN"))
    private void init(World world, BlockPos pos, float yaw, GameProfile gameProfile, CallbackInfo ci) {
        //Mixin does not know (yet) that this will be merged with AbstractClientPlayerEntity
        //PlayerAnimationAccess.getPlayerAnimLayer((AbstractClientPlayerEntity) (Object)this).addAnimLayer(1000, modAnimationContainer); //Register the layer with a priority
        //The priority will tell, how important is this animation compared to other mods. Higher number means higher priority
        //Mods with higher priority will override the lower priority mods (if they want to animation anything)

        PlayerEntity player = ((PlayerEntity)(Object)this);
        spellData = new PlayerMagicData(player);
        playerAnimator = new PlayerAnimator();
        manaManager = new PlayerMagicManager(spellData, playerAnimator);
    }


    /**
     * Override the interface function, so we can use it in the future
     */
    @Override
    public ModifierLayer<IAnimation> custom_magic_getModAnimation() {
        return modAnimationContainer;
    }

    @Override
    public PlayerMagicManager getPlayerManaManager() {
        return manaManager;
    }

    @Override
    public PlayerMagicData getPlayerMagicData() {
        return spellData;
    }


    @Override
    public PlayerAnimator getPlayerAnimator() {
        return playerAnimator;
    }
}
