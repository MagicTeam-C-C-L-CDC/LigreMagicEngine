package ru.littleligr.magic.engine.spell;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Equipment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import ru.littleligr.magic.engine.config.Components;
import ru.littleligr.magic.engine.spell.common.CreativeSpellContainer;
import ru.littleligr.magic.engine.spell.common.SpellScriptContainer;
import ru.littleligr.magic.engine.spell.mana.ManaAbsorber;
import ru.littleligr.magic.engine.spell.mana.ManaConsumer;
import ru.littleligr.magic.engine.spell.mana.ManaStorage;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class PlayerEquipment {

    private final PlayerEntity player;
    private final List<Equipment> storages = new LinkedList<>();

    private ManaConsumer manaConsumer = null;
    private SpellScriptContainer spellScriptContainer = null;

    public PlayerEquipment(PlayerEntity player) {
        this.player = player;
    }

    public float getManaCapacity() {
        return storages
                .stream()
                .filter(equipment -> equipment instanceof ManaStorage)
                .map(equipment -> ((ManaStorage) equipment).getManaCapacity())
                .reduce(Float::sum)
                .orElse(0F);
    }

    public float getManaAbsorb() {
        return storages
                .stream()
                .filter(equipment -> equipment instanceof ManaAbsorber)
                .map(equipment -> ((ManaAbsorber) equipment).absorbSpeed())
                .reduce(Float::sum)
                .orElse(0F);
    }

    public float getManaConsume(boolean onUse) {
        float equipPower = storages
                .stream()
                .filter(equipment -> equipment instanceof ManaConsumer)
                .map(equipment -> ((ManaConsumer) equipment).manaConsume())
                .reduce(Float::sum)
                .orElse(0F);

        if (onUse) return equipPower + manaConsumer.onUseManaConsume();
        return  equipPower;
    }

    public void addEquipment(EquipmentSlot slot, ItemStack oldStack, ItemStack newStack) {
      /*  if (equipment == null) return;
        if (equipment instanceof ManaStorage)
            storages.add((ManaStorage) equipment);
        if (equipment instanceof ManaAbsorber)
            absorbers.add((ManaAbsorber) equipment);
        if (equipment instanceof SpellScriptContainer)
            searchSpellScriptContainer();
        if (equipment instanceof ManaConsumer) {
            searchManaConsumer();
            consumers.add((ManaConsumer) equipment);
        }*/

    }

    public void removeEquipment(Item equipment) {
        if (equipment == null) return;
        storages.remove((Equipment) equipment);
    }

    public Optional<SpellScriptContainer> getSpellScriptContainer() {
        if (player.isCreative())
            return Components.SPELL_SCRIPT_CONTAINER_COMPONENT_KEY.maybeGet(player);
        else if (spellScriptContainer == null || (spellScriptContainer instanceof CreativeSpellContainer && !player.isCreative()))
            searchSpellScriptContainer();

        return Optional.ofNullable(spellScriptContainer);
    }

    private void searchSpellScriptContainer() {
        if (player.isCreative()) return;

        if (player.getMainHandStack() != null && player.getMainHandStack().getItem() instanceof SpellScriptContainer)
            spellScriptContainer = (SpellScriptContainer) player.getMainHandStack().getItem();
        else if (player.getOffHandStack() != null && player.getOffHandStack().getItem() instanceof SpellScriptContainer)
            spellScriptContainer = (SpellScriptContainer) player.getOffHandStack().getItem();
    }

    public Optional<ManaConsumer> getManaConsumer() {
        if (manaConsumer == null)
            searchManaConsumer();
        return Optional.ofNullable(manaConsumer);
    }

    private void searchManaConsumer() {
        if (player.getMainHandStack() != null && player.getMainHandStack().getItem() instanceof ManaConsumer)
            manaConsumer = (ManaConsumer) player.getMainHandStack().getItem();
    }


}
