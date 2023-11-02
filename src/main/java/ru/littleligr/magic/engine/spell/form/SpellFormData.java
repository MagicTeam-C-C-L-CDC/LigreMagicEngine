package ru.littleligr.magic.engine.spell.form;

import net.minecraft.util.Identifier;

public class SpellFormData {

    public final Identifier identifier;
    public final float cost;

    public SpellFormData(Identifier identifier, float cost) {
        this.identifier = identifier;
        this.cost = cost;
    }
}
