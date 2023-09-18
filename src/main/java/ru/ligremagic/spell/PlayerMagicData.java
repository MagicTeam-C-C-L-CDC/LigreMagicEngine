package ru.ligremagic.spell;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import ru.ligremagic.spell.script.SpellScriptContainer;

import java.util.Optional;

public class PlayerMagicData {

    public final PlayerEntity player;
    public SpellCastProvider spellProvider;
    private final int manaTarget = 0;
    private final int currentMana = 0;

    public PlayerMagicData(PlayerEntity player) {
        this.player = player;
    }

    public Optional<SpellScript> getSpellScript() {
        Optional<SpellScript> scriptContainer = Optional.empty();
        if (player.getMainHandStack() != null && player.getMainHandStack().getItem() instanceof SpellScriptContainer)
            scriptContainer = ((SpellScriptContainer) player.getMainHandStack().getItem()).getSpellScript();
        else if (player.getOffHandStack() != null && player.getOffHandStack().getItem() instanceof SpellScriptContainer){
            scriptContainer = ((SpellScriptContainer) player.getOffHandStack().getItem()).getSpellScript();
        }
        return scriptContainer;
    }

    public int manaLevel() {
        return 100;
    }

    public int maxManaLevel() {
        return 100;
    }

    public int manaPercent () {
        return currentMana;
    }

    public String manaPercentString () {
        if (spellProvider != null)
            return spellProvider.charger().accumulatedMana + "//" + spellProvider.charger().manaGoal;
        return "100";
    }
}
