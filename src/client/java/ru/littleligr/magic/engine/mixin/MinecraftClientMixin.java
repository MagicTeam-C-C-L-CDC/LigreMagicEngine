package ru.littleligr.magic.engine.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.option.GameOptions;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.littleligr.magic.engine.LigreMagicClient;
import ru.littleligr.magic.engine.ui.dynamichud.widgets.HotbarWidget;

@Mixin(MinecraftClient.class)
public class MinecraftClientMixin {

    @Final
    @Shadow
    public GameOptions options;


    @Shadow
    public ClientPlayerEntity player;

    @Inject(method = "handleInputEvents", at = @At("HEAD"))
    public void handleInputs(CallbackInfo ci) {
        if (LigreMagicClient.hud.getHotbarWidget().mode == HotbarWidget.Mode.MAGIC)
            for(int i = 0; i < 9; ++i) {
                if (this.options.hotbarKeys[i].wasPressed()) {
                   // PlayerMagicData data = ((IMagicPlayer) player).getPlayerMagicData();
                   // data.selectDelivery(i);
                    //LigreMagic.OWO_NET_CHANNEL.clientHandle().send(new UpdateSelectedMagicDeliveryPacket(i));
                }

                while (this.options.hotbarKeys[i].wasPressed()) { }
            }
    }

    /*
    @ModifyConstant(method = "handleInputEvents", constant = @Constant(intValue = 9))
    private int injected(int value) {
        if (LigreMagicClient.hud.getHotbarWidget().mode == HotbarWidget.Mode.MAGIC)
            return 0;
        return value;
    }

     */
}
