package ru.littleligr.magic.engine.item;

import dev.onyxstudios.cca.api.v3.item.ItemComponent;
import net.minecraft.item.ItemStack;
import ru.littleligr.magic.engine.spell.common.SpellScriptContainer;
import ru.littleligr.magic.engine.storage.info.SpellInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SpellContainerItem extends ItemComponent implements SpellScriptContainer {

    private int id = 0;
    private final List<SpellInfo> spells;

    public SpellContainerItem(ItemStack stack, int size) {
        super(stack);
        spells = new ArrayList<>(size);
    }

    @Override
    public void selectSpell(int id) {
        this.id = id;
    }

    @Override
    public SpellInfo getSpellInfo() {
        return spells.get(selectedId());
    }

    @Override
    public int selectedId() {
        return id;
    }

    @Override
    public List<SpellInfo> getSpells() {
        return spells;
    }

    @Override
    public int spellCapacity() {
        return 1;
    }
}
