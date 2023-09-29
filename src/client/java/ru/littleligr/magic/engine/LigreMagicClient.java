package ru.littleligr.magic.engine;

import net.fabricmc.api.ClientModInitializer;
import ru.littleligr.magic.engine.network.UpdateManaLevelPacket;
import ru.littleligr.magic.engine.spell.common.IMagicData;

public class LigreMagicClient implements ClientModInitializer {


	@Override
	public void onInitializeClient() {
		LigreMagicEngine.LOGGER.info("LigreMagicEngine client init started");

		LigreMagicEngine.OWO_NET_CHANNEL.registerClientbound(UpdateManaLevelPacket.class, (message, access) -> {
			IMagicData player = (IMagicData) access.player();
			player.getPlayerMagicData().setManaLevel(message.manaLevel());
		});

		LigreMagicEngine.LOGGER.info("LigreMagicEngine client init completed");
	}
}