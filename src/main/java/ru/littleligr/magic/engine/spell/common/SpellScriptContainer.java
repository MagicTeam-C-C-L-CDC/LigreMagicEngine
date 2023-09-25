package ru.littleligr.magic.engine.spell.common;

import ru.littleligr.magic.engine.spell.SpellScript;

public interface SpellScriptContainer {

    SpellScript selectSpell(int id);

    SpellScript getSpellScript();

    int spellCapacity();
}
