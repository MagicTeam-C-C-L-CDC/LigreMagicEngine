package ru.littleligr.magic.engine.item.tools;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.spell.Spell;
import ru.littleligr.magic.engine.spell.common.SpellScriptContainer;
import ru.littleligr.magic.engine.storage.Spells;

public class ManuscriptMagic extends Staff implements SpellScriptContainer {
    public ManuscriptMagic(Settings settings) {
        super(settings);
    }

    @Override
    public Spell getSpellScript() {
        return Spells.spells.get(new Identifier("lme:levitation"));
    }

    @Override
    public Spell selectSpell(int id) {
        return Spells.spells.get(new Identifier("lme:levitation"));
    }

    @Override
    public int spellCapacity() {
        return 1;
    }

}
