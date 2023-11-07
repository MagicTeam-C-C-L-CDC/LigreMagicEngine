package ru.littleligr.magic.engine;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.minecraft.client.MinecraftClient;
import ru.littleligr.magic.engine.utils.DataFolders;

public class LigreMagicEngineClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        LigreMagicEngine.SPELLS.load(MinecraftClient.getInstance().getResourceManager(), DataFolders.SPELLS.path, LigreMagicEngine.GSON_BUILDER);
        LigreMagicEngine.FORMS.load(MinecraftClient.getInstance().getResourceManager(), DataFolders.FORMS.path, LigreMagicEngine.GSON_BUILDER);
    }
}
