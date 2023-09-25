package ru.littleligr.magic.engine.control;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.glfw.GLFW;
import ru.littleligr.magic.engine.ui.dynamichud.HUD;
import ru.littleligr.magic.engine.ui.owo.Screens;

public class RegisterKeys{

    public static final KeyBinding openMagicScreen = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.ligremagic.magicscreen", GLFW.GLFW_KEY_LEFT_ALT, "key.category.ui.magic"));
    public static final KeyBinding changeHotbar = KeyBindingHelper.registerKeyBinding(new KeyBinding("key.ligremagic.changehotbar", GLFW.GLFW_KEY_X, "key.category.ui.magic"));


    public void register(HUD hud) {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openMagicScreen.wasPressed())
                MinecraftClient.getInstance().setScreen(Screens.deliverySelectScreen);

            while (changeHotbar.wasPressed())
                hud.getHotbarWidget().switchMode();
        });
    }
}
