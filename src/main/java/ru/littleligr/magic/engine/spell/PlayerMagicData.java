package ru.littleligr.magic.engine.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.network.UpdateManaLevelPacket;
import ru.littleligr.magic.engine.network.UpdateSelectedMagicFormPacket;
import ru.littleligr.magic.engine.spell.common.SpellScriptContainer;
import ru.littleligr.magic.engine.spell.form.*;

public class PlayerMagicData {

    private final PlayerEntity player;
    private SpellCastProvider spellProvider;
    private final float manaTarget = 400;
    private float currentMana = 340;
    private int delivery = 1;
    float absorbPower = 4;
    float drainPower = 15;
    private boolean castBlock = false;

    public PlayerMagicData(PlayerEntity player) {
        this.player = player;
    }

    public Spell getSpellScript() {
        Spell scriptContainer = null;
        if (player.getMainHandStack() != null && player.getMainHandStack().getItem() instanceof SpellScriptContainer)
            scriptContainer = ((SpellScriptContainer) player.getMainHandStack().getItem()).getSpellScript();
        else if (player.getOffHandStack() != null && player.getOffHandStack().getItem() instanceof SpellScriptContainer)
            scriptContainer = ((SpellScriptContainer) player.getOffHandStack().getItem()).getSpellScript();

        return scriptContainer;
    }

    public float manaLevel() {
        return currentMana;
    }

    public float manaPercent () {
        return currentMana /manaTarget;
    }

    public void setManaLevel(float mana) {
        currentMana = mana;
    }

    public void selectDelivery(int id) {
        this.delivery = id;
        if (player.getWorld().isClient)
            LigreMagicEngine.OWO_NET_CHANNEL.clientHandle().send(new UpdateSelectedMagicFormPacket(id));
    }

    public int getSelectedDelivery() {
        return delivery;
    }

    public SpellForm getDelivery() {
        return switch (delivery) {
            case 1 -> new FormFlow(32);
            case 2 -> new FormTarget(32);
            case 3 -> new FormProjectile();
            case 4 -> new FormSelf();
            default -> throw new IllegalArgumentException();
        };
    }

    public void tick() {
        if (spellProvider == null) {
            absorbMana();
            LigreMagicEngine.OWO_NET_CHANNEL.serverHandle(player).send(new UpdateManaLevelPacket(manaLevel()));
        }
    }

    private void absorbMana() {
        if (manaTarget - currentMana > absorbPower)
            currentMana += absorbPower;
        else currentMana = manaTarget;
    }

    public float getAvailableMana () {
        return Math.min(currentMana, drainPower);
    }

    public float drainMana (float manaTarget) {
        float target = Math.min(drainPower, manaTarget);
        float drainedMana = Math.min(currentMana, target);
        currentMana -= drainedMana;
        player.sendMessage(Text.literal("Current mana: " + currentMana));
        return drainedMana;
    }

    public void cast() {
        Spell spellScript = getSpellScript();
        if (spellScript == null || castBlock) return;

        if (spellProvider == null)
            spellProvider = new SpellCastProvider(player, getDelivery(), spellScript, 14);

        if (spellProvider.isManaEnough(getAvailableMana()))
            spellProvider.consume(drainMana(spellProvider.manaTarget()));
        else castBlock = true;

        if (spellProvider.isCastEnd()) {
            spellProvider = null;
            castBlock = true;
        }

        LigreMagicEngine.OWO_NET_CHANNEL.serverHandle(player).send(new UpdateManaLevelPacket(manaLevel()));
    }

    public void stopCast() {
        spellProvider = null;
        castBlock = false;
    }
}
