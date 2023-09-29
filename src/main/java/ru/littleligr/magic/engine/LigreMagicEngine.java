package ru.littleligr.magic.engine;

import io.wispforest.owo.network.OwoNetChannel;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.littleligr.magic.engine.config.LigeMagicEngineEffects;
import ru.littleligr.magic.engine.config.LigreMagicEngineAdapters;
import ru.littleligr.magic.engine.config.LigreMagicEngineConfig;
import ru.littleligr.magic.engine.item.ItemInit;
import ru.littleligr.magic.engine.network.UpdateSelectedMagicFormPacket;
import ru.littleligr.magic.engine.spell.common.IMagicData;
import ru.littleligr.magic.engine.storage.Spells;

public class LigreMagicEngine implements ModInitializer {

	public static final String MOD_ID = "lme";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final OwoNetChannel OWO_NET_CHANNEL = OwoNetChannel.create(new Identifier(MOD_ID, "main"));
	public static final LigreMagicEngineConfig CONFIG = LigreMagicEngineConfig.createAndLoad();

	@Override
	public void onInitialize() {
		OWO_NET_CHANNEL.registerServerbound(UpdateSelectedMagicFormPacket.class, (message, access) -> {
			IMagicData player = (IMagicData) access.player();
			player.getPlayerMagicData().selectDelivery(message.id());
		});

		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);

		LigeMagicEngineEffects.register();
		LigreMagicEngineAdapters.register();

		ServerLifecycleEvents.SERVER_STARTED.register((minecraftServer) -> {
			Spells.loadSpells(minecraftServer.getResourceManager());
		});

	}
}