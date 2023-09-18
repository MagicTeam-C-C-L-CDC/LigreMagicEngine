package ru.ligremagic;

import com.tanishisherewith.dynamichud.DynamicHUD;
import com.tanishisherewith.dynamichud.huds.MoveableScreen;
import com.tanishisherewith.dynamichud.util.DynamicUtil;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import ru.ligremagic.ui.HUD;
import ru.ligremagic.ui.owo.TestScreen;

public class LigreMagicClient implements ClientModInitializer {

	public static DynamicUtil dynamicutil = DynamicHUD.getDynamicUtil();
	KeyBinding binding1 = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.ligremagic.magicscreen", GLFW.GLFW_KEY_X, "key.category.ui.magic"));

	@Override
	public void onInitializeClient() {
		LigreMagic.LOGGER.info("LigreMagic client init started");
		DynamicHUD.setAbstractScreen(new MoveableScreen(Text.of("Editor Screen"), dynamicutil));
		DynamicHUD.setIWigdets(new HUD(MinecraftClient.getInstance()));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (binding1.wasPressed()) {
				MinecraftClient.getInstance().setScreen(new TestScreen());
				client.player.sendMessage(Text.literal("Key 1 was pressed!"), false);
			}
		});
		//dynamicutil.getWidgetManager().setWidgetLoading(new HUD(MinecraftClient.getInstance()));

		LigreMagic.LOGGER.info("LigreMagic client init completed");
	}
}