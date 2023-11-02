package ru.littleligr.magic.engine.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import ru.littleligr.magic.engine.storage.vanilla.VanillaSounds;

@Mixin(SoundEvents.class)
public class SoundEventsMixin {
    @Inject(at = @At("RETURN"), method = "register(Lnet/minecraft/util/Identifier;Lnet/minecraft/util/Identifier;)Lnet/minecraft/sound/SoundEvent;")
    private static void register(Identifier id, Identifier soundId, CallbackInfoReturnable<SoundEvent> cir) {
        VanillaSounds.map.put(soundId, cir.getReturnValue());
    }
}
