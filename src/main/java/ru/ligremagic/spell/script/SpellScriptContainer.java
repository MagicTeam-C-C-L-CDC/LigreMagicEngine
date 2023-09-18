package ru.ligremagic.spell.script;

import ru.ligremagic.spell.SpellScript;

import java.util.Optional;

public interface SpellScriptContainer {
    Optional<SpellScript> getSpellScript();
    boolean destroyAfterUse();
}
