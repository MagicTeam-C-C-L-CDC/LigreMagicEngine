package ru.littleligr.magic.engine.spell.mana;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.config.Components;

public class PlayerMana implements ManaComponent, AutoSyncedComponent {
    public static final String MANA_NBT_TAG = "lme_mana";
    private final float baseManaCapacity = LigreMagicEngine.CONFIG.basePlayerManaCapacity();
    private final float baseManaAbsorb = LigreMagicEngine.CONFIG.basePlayerManaAbsorb();
    private float currentMana = baseManaCapacity;
    private final PlayerEntity player;

    public PlayerMana(PlayerEntity player) {
        this.player = player;
    }

    public void absorbMana(float equipmentAbsorbPower, float equipmentCapacityPower) {
        float oldMana = currentMana;

        float fullManaCapacity = baseManaCapacity  + equipmentCapacityPower;
        float fullManaAbsorb = baseManaAbsorb + equipmentAbsorbPower;
        if (fullManaCapacity - currentMana > fullManaAbsorb)
            currentMana += fullManaAbsorb;
        else currentMana = fullManaCapacity;

        if (oldMana != currentMana)
            Components.syncMana(this.player);
    }

    public float getAvailableMana (float equipmentAbsorbPower) {
        return Math.min(currentMana, equipmentAbsorbPower + baseManaAbsorb);
    }

    public float drainMana (float manaTarget, float equipmentAbsorbPower) {
        float target = Math.min(equipmentAbsorbPower, manaTarget);
        float drainedMana = Math.min(currentMana, target);
        currentMana -= drainedMana;
        Components.syncMana(this.player);
        return drainedMana;
    }

    public void setMana(float mana) {
        this.currentMana = mana;
    }

    public float getCurrentMana() {
        return currentMana;
    }

    @Override
    public void readFromNbt(NbtCompound tag) {
        this.currentMana = tag.getFloat(MANA_NBT_TAG);
    }

    @Override
    public void writeToNbt(NbtCompound tag) {
        tag.putFloat(MANA_NBT_TAG, currentMana);
    }

    @Override
    public void writeSyncPacket(PacketByteBuf buf, ServerPlayerEntity recipient) {
        buf.writeFloat(currentMana);
    }

    @Override
    public void applySyncPacket(PacketByteBuf buf) {
        currentMana = buf.readFloat();
    }

    @Override
    public boolean shouldSyncWith(ServerPlayerEntity player) {
        return player == this.player;
    }
}
