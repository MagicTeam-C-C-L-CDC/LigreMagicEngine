package ru.littleligr.magic.engine.spell.form;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.SpellLock;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.utils.RaycastUtil;

public class FormFlow implements SpellLock, SpellForm {

    private final int range;
    private final Identifier identifier = new Identifier(LigreMagicEngine.MOD_ID, "form_radius");

    public FormFlow(int range) {
        this.range = range;
    }

    @Override
    public void call(WizardInfo spellOwner, SpellCallback callback) {
        RaycastUtil.raycast(spellOwner, range, callback);
    }
}
