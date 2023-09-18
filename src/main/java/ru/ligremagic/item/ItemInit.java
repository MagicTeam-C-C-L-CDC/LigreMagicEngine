package ru.ligremagic.item;

import io.wispforest.owo.registration.annotations.AssignedName;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.ToolMaterials;
import ru.ligremagic.item.tools.Manuscript;
import ru.ligremagic.item.tools.ManuscriptMagic;
import ru.ligremagic.item.tools.Staff;

import java.lang.reflect.Field;

public class ItemInit implements ItemRegistryContainer {
    @AssignedName("staff")
    public static final Item STAFF = new Staff(ToolMaterials.IRON, 4, 1.2f, new FabricItemSettings());

    @AssignedName("manuscript")
    public static final Item MANUSCRIPT = new Manuscript(new FabricItemSettings());

    @AssignedName("manuscript_magic")
    public static final Item MANUSCRIPT_MAGIC = new ManuscriptMagic(new FabricItemSettings());

    @Override
    public void postProcessField(String namespace, Item value, String identifier, Field field) {
        ItemRegistryContainer.super.postProcessField(namespace, value, identifier, field);
        if (!identifier.equals("manuscript_magic"))
            ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(content -> content.add(value));
    }
}
