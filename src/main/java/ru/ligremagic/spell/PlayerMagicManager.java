package ru.ligremagic.spell;

import ru.ligremagic.animations.PlayerAnimator;
import ru.ligremagic.spell.PlayerMagicData;
import ru.ligremagic.spell.SpellScript;
import ru.ligremagic.spell.SpellCastProvider;
import ru.ligremagic.spell.common.SpellCharger;

import java.util.Optional;

public class PlayerMagicManager {
    private int capacity;
    private final PlayerMagicData spellData;
    private final PlayerAnimator playerAnimator;

    public PlayerMagicManager(PlayerMagicData spellData, PlayerAnimator playerAnimator) {
        this.spellData = spellData;
        this.playerAnimator = playerAnimator;
    }

    public void stopCast() {
        spellData.spellProvider = null;
    }

    public void cast() {
        Optional<SpellScript> spell = spellData.getSpellScript();
        if (spell.isEmpty())
            return;

        if (spellData.spellProvider == null)
            spellData.spellProvider = new SpellCastProvider(new SpellCharger(spell.get().getManaCost()), spell.get().createSpellPipe(spellData.player));

        if (spellData.spellProvider.charger().ready()) {
            if (!spellData.player.getWorld().isClient)
                spellData.spellProvider.pipe().call();

            if (!spellData.spellProvider.pipe().isLocked())
                spellData.spellProvider = null;
        }
        else spellData.spellProvider.charger().consumeMana(takeMana());
    }

    private int takeMana() {return 100;}

    //начало каста
    //процесс каста
    //конец каста
}
