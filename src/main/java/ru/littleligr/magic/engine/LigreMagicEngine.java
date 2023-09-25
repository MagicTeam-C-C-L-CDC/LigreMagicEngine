package ru.littleligr.magic.engine;

import io.wispforest.owo.network.OwoNetChannel;
import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resource.featuretoggle.FeatureManager;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.littleligr.magic.engine.block.BlockInit;
import ru.littleligr.magic.engine.item.ItemInit;
import ru.littleligr.magic.engine.network.UpdateSelectedMagicDeliveryPacket;
import ru.littleligr.magic.engine.spell.common.IMagicData;

public class LigreMagicEngine implements ModInitializer {

	public static final String MOD_ID = "lme";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final OwoNetChannel OWO_NET_CHANNEL = OwoNetChannel.create(new Identifier(MOD_ID, "main"));

	@Override
	public void onInitialize() {
		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(BlockInit.class, MOD_ID, false);

		OWO_NET_CHANNEL.registerServerbound(UpdateSelectedMagicDeliveryPacket.class, (message, access) -> {
			IMagicData player = (IMagicData) access.player();
			player.getPlayerMagicData().selectDelivery(message.id());
		});
	}
}