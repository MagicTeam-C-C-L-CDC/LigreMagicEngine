package ru.littleligr.magic.engine;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.control.RegisterKeys;
import ru.littleligr.magic.engine.item.ItemInit;
import ru.littleligr.magic.engine.network.UpdateManaLevelPacket;
import ru.littleligr.magic.engine.spell.common.IMagicData;
import ru.littleligr.magic.engine.ui.dynamichud.HUD;
import ru.littleligr.magic.engine.ui.owo.RegisterOwoComponents;

public class LigreMagicClient implements ClientModInitializer {

	public static HUD hud;


	@Override
	public void onInitializeClient() {
		LigreMagicEngine.LOGGER.info("LigreMagic client init started");

		ModelPredicateProviderRegistry.register(ItemInit.STAFF, new Identifier("pull"), (itemStack, clientWorld, livingEntity, tick) -> {
			if (livingEntity == null) {
				return 0.0F;
			}
			return livingEntity.getActiveItem() != itemStack ? 0.0F : (itemStack.getMaxUseTime() - livingEntity.getItemUseTimeLeft()) / 20.0F;
		});

		new RegisterOwoComponents().register();
		hud = new HUD(MinecraftClient.getInstance());
		new RegisterKeys().register(hud);

		LigreMagicEngine.OWO_NET_CHANNEL.registerClientbound(UpdateManaLevelPacket.class, (message, access) -> {
			IMagicData player = (IMagicData) access.player();
			player.getPlayerMagicData().setManaLevel(message.manaLevel());
		});

		LigreMagicEngine.LOGGER.info("LigreMagic client init completed");
	}
}