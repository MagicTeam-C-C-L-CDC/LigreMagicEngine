package ru.littleligr.magic.engine.spell.common;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.storage.info.SpellInfo;
import ru.littleligr.magic.engine.storage.lme.Spells;

import java.util.List;

public class CreativeSpellContainer implements SpellScriptContainer{

    public static final String CREATIVE_SELECTED_SPELL_KEY = "lme_creative_selected_spell";
    private int selectedSpell = 0;
    private List<SpellInfo> spells = null;
    private final PlayerEntity player;

    public CreativeSpellContainer(PlayerEntity player) {
        this.player = player;
    }

    @Override
    public void selectSpell(int id) {
        selectedSpell = id;
    }

    @Override
    public SpellInfo getSpellInfo() {
        if (spells == null)
            spells = LigreMagicEngine.SPELLS.getSpells();
        return spells.get(selectedSpell);
    }

    @Override
    public int selectedId() {
        return selectedSpell;
    }

    @Override
    public List<SpellInfo> getSpells() {
        if (spells == null)
            spells = LigreMagicEngine.SPELLS.getSpells();
        return spells;
    }

    @Override
    public int spellCapacity() {
        if (spells == null)
            spells = LigreMagicEngine.SPELLS.getSpells();
        return spells.size();
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        selectedSpell = tag.getInt(CREATIVE_SELECTED_SPELL_KEY);
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putInt(CREATIVE_SELECTED_SPELL_KEY, selectedSpell);
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.player;
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        selectedSpell = buf.readInt();
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        buf.writeInt(selectedSpell);
    }
}
