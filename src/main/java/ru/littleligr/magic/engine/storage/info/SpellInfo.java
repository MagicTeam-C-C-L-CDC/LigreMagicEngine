package ru.littleligr.magic.engine.storage.info;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.spell.Spell;

public record SpellInfo(Identifier identifier, Spell spell) {

}
