package ru.littleligr.magic.engine.spell.common;

import ru.littleligr.magic.engine.spell.Spell;

public interface SpellScriptContainer {

    Spell selectSpell(int id);

    Spell getSpellScript();

    int spellCapacity();
}
