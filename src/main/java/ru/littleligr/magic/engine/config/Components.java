package ru.littleligr.magic.engine.config;


import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.PlayerEquipment;
import ru.littleligr.magic.engine.spell.PlayerMagicData;
import ru.littleligr.magic.engine.spell.common.CreativeSpellContainer;
import ru.littleligr.magic.engine.spell.common.SpellScriptContainer;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.mana.PlayerMana;
import ru.littleligr.magic.engine.storage.ComponentsRegister;

import java.util.Optional;

public final class Components implements EntityComponentInitializer, ItemComponentInitializer {
    public static final ComponentKey<PlayerMana> MANA_COMPONENT_KEY =
            ComponentRegistry.getOrCreate(new Identifier(LigreMagicEngine.MOD_ID, "mana_component"), PlayerMana.class);

    public static final ComponentKey<PlayerMagicData> MAGIC_DATA_COMPONENT_KEY =
            ComponentRegistry.getOrCreate(new Identifier(LigreMagicEngine.MOD_ID, "magic_data_component"), PlayerMagicData.class);

    public static final ComponentKey<SpellScriptContainer> SPELL_SCRIPT_CONTAINER_COMPONENT_KEY =
            ComponentRegistry.getOrCreate(new Identifier(LigreMagicEngine.MOD_ID, "spell_script_container_component"), SpellScriptContainer.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(MANA_COMPONENT_KEY, PlayerMana::new, RespawnCopyStrategy.ALWAYS_COPY);

        registry.registerForPlayers(
                MAGIC_DATA_COMPONENT_KEY,
                player ->new PlayerMagicData(player, new PlayerEquipment(player)),
                RespawnCopyStrategy.ALWAYS_COPY
        );

        registry.registerForPlayers(
                SPELL_SCRIPT_CONTAINER_COMPONENT_KEY,
                CreativeSpellContainer::new,
                RespawnCopyStrategy.ALWAYS_COPY
        );
    }

    @Override
    public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
        ComponentsRegister.getItemRegisterConsumers().forEach(consumer -> consumer.accept(registry));
    }
    public static void syncMana(PlayerEntity player) {
        MANA_COMPONENT_KEY.sync(player);
    }

    public static Optional<PlayerMana> getManaComponent(PlayerEntity player) {
        return MANA_COMPONENT_KEY.maybeGet(player);
    }

    public static Optional<PlayerMagicData> getMagicDataComponent(PlayerEntity player) {
        return MAGIC_DATA_COMPONENT_KEY.maybeGet(player);
    }

}