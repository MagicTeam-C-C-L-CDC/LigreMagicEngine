package ru.littleligr.magic.engine.spell.mana;

import net.minecraft.item.Equipment;

public interface ManaConsumer extends Equipment {
    default boolean destroyAfterUse(){ return false; }
    float manaConsume();
    float onUseManaConsume();
    default float attackDamage() {return 0;}
}
