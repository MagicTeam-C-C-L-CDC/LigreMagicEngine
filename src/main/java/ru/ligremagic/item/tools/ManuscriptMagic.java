package ru.ligremagic.item.tools;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import ru.ligremagic.spell.SpellScript;
import ru.ligremagic.spell.cast.delivery.instant.DeliveryRadius;
import ru.ligremagic.spell.cast.delivery.real.DeliveryProjectile;
import ru.ligremagic.spell.cast.effect.terraforming.create.EffectCreate;
import ru.ligremagic.spell.script.SpellScriptContainer;

import java.util.Optional;

public class ManuscriptMagic extends Item implements SpellScriptContainer {
    //Spell spell = new Spell(new DeliveryFlow(32), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));
    SpellScript spell = new SpellScript(new DeliveryProjectile(), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));
    //Spell spell = new Spell(new DeliveryTarget(32), new DeliveryRadius(1), new EffectCreate(Block.getBlockFromItem(Items.STONE)));

    public ManuscriptMagic(Settings settings) {
        super(settings);
    }

    @Override
    public Optional<SpellScript> getSpellScript() {
        return Optional.of(spell);
    }

    @Override
    public boolean destroyAfterUse() {
        return false;
    }
}
