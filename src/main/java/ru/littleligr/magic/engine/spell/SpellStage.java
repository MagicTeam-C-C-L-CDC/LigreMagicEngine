package ru.littleligr.magic.engine.spell;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;

public enum SpellStage {
    CAST(new Identifier(LigreMagicEngine.MOD_ID, "cast")),
    RELEASE(new Identifier(LigreMagicEngine.MOD_ID, "release")),
    TARGET(new Identifier(LigreMagicEngine.MOD_ID, "target"));

    public final Identifier identifier;

    SpellStage(Identifier identifier) {
        this.identifier = identifier;
    }
}
