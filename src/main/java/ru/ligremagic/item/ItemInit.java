package ru.ligremagic.item;

import io.wispforest.owo.registration.annotations.AssignedName;
import io.wispforest.owo.registration.reflect.ItemRegistryContainer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ToolMaterials;
import ru.ligremagic.item.tools.Staff;

public class ItemInit implements ItemRegistryContainer {
    @AssignedName("staff")
    public static final Staff STAFF = new Staff(ToolMaterials.IRON, 4, 1.2f, new FabricItemSettings());
}
