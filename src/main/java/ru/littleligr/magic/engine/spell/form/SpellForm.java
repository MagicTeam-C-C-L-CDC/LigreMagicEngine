package ru.littleligr.magic.engine.spell.form;

import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.WizardInfo;

public interface SpellForm {

    void call(WizardInfo spellOwner, SpellCallback callback);
}