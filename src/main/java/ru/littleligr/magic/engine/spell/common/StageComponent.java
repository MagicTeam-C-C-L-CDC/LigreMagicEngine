package ru.littleligr.magic.engine.spell.common;

import net.minecraft.util.Identifier;

public interface StageComponent {
    boolean filter(Identifier stage);
}
