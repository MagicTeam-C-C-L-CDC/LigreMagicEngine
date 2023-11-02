package ru.littleligr.magic.engine.spell;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.config.Components;
import ru.littleligr.magic.engine.network.UpdateSelectedSpellPacket;
import ru.littleligr.magic.engine.spell.common.MagicDataComponent;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.mana.ManaConsumer;
import ru.littleligr.magic.engine.spell.common.SpellScriptContainer;
import ru.littleligr.magic.engine.spell.mana.PlayerMana;
import ru.littleligr.magic.engine.storage.info.FormInfo;
import ru.littleligr.magic.engine.network.UpdateSelectedFormPacket;

import java.util.Optional;

public class PlayerMagicData implements MagicDataComponent, AutoSyncedComponent, ServerTickingComponent {

    public static final String PLAYER_SELECTED_FORM_IDENTIFIER = "lme_form_identifier";

    private final PlayerEntity player;
    public final PlayerEquipment equipment;
    private SpellCastProvider spellProvider;
    private Identifier selectedFormIdentifier = new Identifier("");

    public PlayerMagicData(PlayerEntity player, PlayerEquipment equipment) {
        this.player = player;
        this.equipment = equipment;
    }

    public void setMana(float mana) {
        Components.getManaComponent(player).orElseThrow().setMana(mana);
    }
    public float getMana() {
        return Components.getManaComponent(player).orElseThrow().getCurrentMana();
    }

    public void cast(WizardInfo wizard) {
        if (player.getWorld().isClient) return;

        Optional<SpellScriptContainer> spellContainer = equipment.getSpellScriptContainer();
        Optional<ManaConsumer> manaConsumer = equipment.getManaConsumer();

        if (spellContainer.isEmpty() || manaConsumer.isEmpty()) return;

        if (spellProvider == null && getFormInfo().isPresent())
            spellProvider = new SpellCastProvider(wizard, getFormInfo().get(), spellContainer.get().getSpellInfo().spell());
        else if (getFormInfo().isEmpty()) {
            LigreMagicEngine.LOGGER.error(String.format("SpellForm with identifier %s not found",  selectedFormIdentifier.toString()));
            return;
        }

        /*
         * Checking that available mana with comparing absorbing power of player+equip
         * if that enough, then consume all available mana
         * it's mean absorb mana with player+equip power, but check this with current player mana (not giving go lower than 0)
         */
        if (spellProvider.castBlock) return;

        Optional<PlayerMana> mana = Components.getManaComponent(player);
        if (mana.isEmpty()) return;

        if (spellProvider.isManaEnough(mana.orElseThrow().getAvailableMana(equipment.getManaConsume(true))))
            spellProvider.consume(mana.orElseThrow().drainMana(spellProvider.manaTarget(), equipment.getManaConsume(true)));
        else spellProvider.castBlock = true;

        if (spellProvider.isCastEnd())
            spellProvider = null;
    }

    public void selectSpell(int id) {
        equipment.getSpellScriptContainer().ifPresent(s -> s.selectSpell(id));
        if (player.getWorld().isClient)
            LigreMagicEngine.OWO_NET_CHANNEL.clientHandle().send(new UpdateSelectedSpellPacket(id));
    }

    public void stopCast() {
        spellProvider = null;
    }

    @Override
    public void setForm(Identifier identifier) {
        selectedFormIdentifier = identifier;
        if (player.getWorld().isClient)
            LigreMagicEngine.OWO_NET_CHANNEL.clientHandle().send(new UpdateSelectedFormPacket(identifier));
    }

    @Override
    public Identifier getForm() {
        return selectedFormIdentifier;
    }

    @Override
    public Optional<FormInfo> getFormInfo() {
        return LigreMagicEngine.FORMS.getSpellFormInfo(selectedFormIdentifier);
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        selectedFormIdentifier = new Identifier(tag.getString(PLAYER_SELECTED_FORM_IDENTIFIER));
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putString(PLAYER_SELECTED_FORM_IDENTIFIER, selectedFormIdentifier.toString());
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return this.player.getUuid().equals(player.getUuid());
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        buf.writeIdentifier(selectedFormIdentifier);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        selectedFormIdentifier = buf.readIdentifier();
    }

    @Override
    public void serverTick() {
        if (spellProvider == null)
            Components.getManaComponent(player).ifPresent(m -> m.absorbMana(equipment.getManaAbsorb(), equipment.getManaCapacity()));
    }

    @Override
    public String toString() {
        return "PlayerMagicData{" +
                "player=" + player +
                ", equipment=" + equipment +
                ", spellProvider=" + spellProvider +
                ", selectedFormIdentifier=" + selectedFormIdentifier +
                '}';
    }
}
