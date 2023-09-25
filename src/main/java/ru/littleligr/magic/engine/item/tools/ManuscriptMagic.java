package ru.littleligr.magic.engine.item.tools;

import net.minecraft.block.Block;
import net.minecraft.item.Items;
import ru.littleligr.magic.engine.spell.SpellScript;
import ru.littleligr.magic.engine.spell.cast.form.DeliveryRadius;
import ru.littleligr.magic.engine.spell.cast.effect.terraforming.create.EffectCreate;
import ru.littleligr.magic.engine.spell.common.SpellScriptContainer;

public class ManuscriptMagic extends Staff implements SpellScriptContainer {
    //Spell spell = new Spell(new DeliveryFlow(32), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));
    SpellScript spell = new SpellScript(new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));
    //Spell spell = new Spell(new DeliveryTarget(32), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));

    public ManuscriptMagic(Settings settings) {
        super(settings);
    }

    @Override
    public SpellScript getSpellScript() {
        return spell;
    }

    @Override
    public SpellScript selectSpell(int id) {
        return spell;
    }

    @Override
    public int spellCapacity() {
        return 1;
    }

}
