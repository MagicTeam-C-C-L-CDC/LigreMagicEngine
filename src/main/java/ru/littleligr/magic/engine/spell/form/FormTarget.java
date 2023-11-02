package ru.littleligr.magic.engine.spell.form;


import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.common.SpellCallback;
import ru.littleligr.magic.engine.spell.common.WizardInfo;
import ru.littleligr.magic.engine.utils.RaycastUtil;

public class FormTarget implements SpellForm {
    Identifier identifier = new Identifier(LigreMagicEngine.MOD_ID, "form_radius");
    private final int range;

    public FormTarget(int range) {
        this.range = range;
    }

    @Override
    public void call(WizardInfo spellOwner, SpellCallback callback) {
        RaycastUtil.raycast(spellOwner, range, callback);
    }
}
