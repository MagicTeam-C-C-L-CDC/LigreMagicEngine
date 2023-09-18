package ru.ligremagic;

import io.wispforest.owo.registration.reflect.FieldRegistrationHandler;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ligremagic.block.BlockInit;
import ru.ligremagic.item.ItemInit;

public class LigreMagic implements ModInitializer {

	public static final String MOD_ID = "ligremagic";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	//public static DynamicUtil dynamicutil = DynamicHUD.getDynamicUtil();

	@Override
	public void onInitialize() {
		FieldRegistrationHandler.register(ItemInit.class, MOD_ID, false);
		FieldRegistrationHandler.register(BlockInit.class, MOD_ID, false);
	}
}