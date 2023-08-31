package ru.ligremagic.spell.delivery;

import net.minecraft.util.Identifier;
import ru.ligremagic.spell.common.SpellUnit;

import java.util.Optional;

public interface SpellDelivery extends SpellUnit {
    default Optional<Identifier> getSpellCastAnimation() {return Optional.empty();}
}