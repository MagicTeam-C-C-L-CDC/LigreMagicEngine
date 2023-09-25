package ru.littleligr.magic.engine.spell.cast.form;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.spell.cast.SpellUnit;

import java.util.Optional;

public interface SpellDelivery extends SpellUnit {
    default Optional<Identifier> getSpellCastAnimation() {return Optional.empty();}
}