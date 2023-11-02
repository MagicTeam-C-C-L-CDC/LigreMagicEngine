package ru.littleligr.magic.engine.network;

import io.wispforest.owo.network.OwoNetChannel;
import ru.littleligr.magic.engine.config.Components;

public abstract class LigreMagicEngineNetworks {
    public static void register(OwoNetChannel netChannel) {
        netChannel.registerServerbound(UpdateSelectedFormPacket.class, (message, access) -> {
            Components.MAGIC_DATA_COMPONENT_KEY.get(access.player()).setForm(message.identifier());
        });

        netChannel.registerServerbound(UpdateSelectedSpellPacket.class, (message, access) -> {
            Components.MAGIC_DATA_COMPONENT_KEY.get(access.player()).selectSpell(message.id());
        });
    }
}
