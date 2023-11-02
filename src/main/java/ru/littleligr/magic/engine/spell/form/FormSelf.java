package ru.littleligr.magic.engine.spell.form;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.spell.target.LivingEntityTarget;

public class FormSelf implements SpellForm {
    Identifier identifier = new Identifier(LigreMagicEngine.MOD_ID, "form_self");


    @Override
    public void call(WizardInfo spellOwner, SpellCallback callback) {
        callback.call(new LivingEntityTarget(spellOwner.player()));
    }
}
