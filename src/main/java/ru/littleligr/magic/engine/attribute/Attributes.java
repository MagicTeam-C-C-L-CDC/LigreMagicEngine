package ru.littleligr.magic.engine.attribute;

import net.minecraft.entity.attribute.ClampedEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;

public class Attributes {
    public Attributes(PlayerEntity entity) {
        EntityAttributeModifier e = new EntityAttributeModifier("test", 2, EntityAttributeModifier.Operation.ADDITION);
        //EntityAttributes.GENERIC_ARMOR
        ClampedEntityAttribute a = new ClampedEntityAttribute("", 2, 1, 2);
    }

}
