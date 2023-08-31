package ru.ligremagic.mixin;

import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.ligremagic.animations.IExampleAnimatedPlayer;
import ru.ligremagic.item.tools.SpellProvider;

@Mixin(Item.class)
public class ItemUseAnimatorMixin {
    @Inject(method = "use", at = @At("HEAD"))
    private void playAnimation(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        //We must start the animation on client-side
        if (world.isClient) {
            ItemStack itemStack = user.getStackInHand(hand);

            if (itemStack == null)
                return;

            Item handItem = itemStack.getItem();

            if (!(handItem instanceof SpellProvider))
                return;

            var animationContainer = ((IExampleAnimatedPlayer)user).custom_magic_getModAnimation();

            //Use setAnimation to set the current animation. It will be played automatically.
            KeyframeAnimation anim = PlayerAnimationRegistry.getAnimation(new Identifier("custom-magic", "one_handed_projectile_charge"));

            // Requested API, disable parts of animation.
            // Following code disables the left leg (since API 0.4.0)
            var builder = anim.mutableCopy();
            var part = builder.getPart("leftLeg");
            part.setEnabled(false);

            // done modifying rules
            anim = builder.build();

            animationContainer.setAnimation(new KeyframeAnimationPlayer(anim));
        }
    }

}
