package ru.ligremagic.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.ligremagic.LigreMagic;
import ru.ligremagic.spell.mana.IMagicPlayer;
import ru.ligremagic.spell.mana.ManaConsumer;
import ru.ligremagic.spell.PlayerMagicManager;
import ru.ligremagic.spell.script.SpellScriptContainer;

@Mixin(Item.class)
public class ItemMixin {

    @Inject(method = "onStoppedUsing", at = @At("HEAD"))
    private void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks, CallbackInfo ci) {

        ItemStack itemStack = user.getStackInHand(user.getActiveHand());
        if (itemStack == null) return;
        LigreMagic.LOGGER.info(itemStack.toString());
        Item handItem = itemStack.getItem();
        if (handItem instanceof ManaConsumer) {
            LigreMagic.LOGGER.info("THIS IS MANA CONSUMER!!!");
            PlayerMagicManager manaManager = ((IMagicPlayer) user).getPlayerManaManager();
            manaManager.stopCast();
        }

    }

    @Inject(method = "use", at = @At("HEAD"))
    private void use(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {

        ItemStack itemStack = user.getStackInHand(hand);
        if (itemStack == null) return;

        Item handItem = itemStack.getItem();
        if (handItem instanceof ManaConsumer) {
            SpellScriptContainer scriptContainer = null;

            if (handItem instanceof SpellScriptContainer)
                scriptContainer = (SpellScriptContainer) handItem;
            else {}

            PlayerMagicManager manaManager = ((IMagicPlayer) user).getPlayerManaManager();
            manaManager.cast();
        }

        /*
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
        }*/
    }

}
