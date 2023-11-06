package ru.littleligr.magic.engine;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import ru.littleligr.magic.engine.utils.DataFolders;

public class LigreMagicEngineClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientLifecycleEvents.CLIENT_STARTED.register((minecraftServer) -> {
            LigreMagicEngine.SPELLS.load(minecraftServer.getResourceManager(), DataFolders.SPELLS.path, LigreMagicEngine.GSON_BUILDER);
            LigreMagicEngine.FORMS.load(minecraftServer.getResourceManager(), DataFolders.FORMS.path, LigreMagicEngine.GSON_BUILDER);
        });
    }
}
