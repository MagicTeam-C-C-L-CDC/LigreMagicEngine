package ru.littleligr.magic.engine.block;

import io.wispforest.owo.registration.annotations.AssignedName;
import io.wispforest.owo.registration.reflect.BlockRegistryContainer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.lang.reflect.Field;

public class BlockInit implements BlockRegistryContainer {
    @AssignedName("red_gem")
    public static final Block RED_GEM = new RedGem(FabricBlockSettings.create());

    @Override
    public void postProcessField(String namespace, Block value, String identifier, Field field) {
        if (field.isAnnotationPresent(NoBlockItem.class)) return;


        BlockItem blockItem = null;

        if (identifier.equals("red_gem")) {
            blockItem = createBlockItem(value, identifier);
        }
        else blockItem = createBlockItem(value, identifier);

        Registry.register(Registries.ITEM, new Identifier(namespace, identifier), blockItem);

        BlockItem finalBlockItem = blockItem;
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(finalBlockItem));
    }
}
