package ru.littleligr.magic.engine.config;

import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.form.*;
import ru.littleligr.magic.engine.storage.lme.Forms;

public abstract class LigreMagicEngineForms {
    public static void register(Forms forms) {
        forms.register(new Identifier(LigreMagicEngine.MOD_ID, "projectile"), FormProjectile::new);
        forms.register(new Identifier(LigreMagicEngine.MOD_ID, "target"), () -> new FormTarget(32));
        forms.register(new Identifier(LigreMagicEngine.MOD_ID, "flow"), () -> new FormFlow(32));
        forms.register(new Identifier(LigreMagicEngine.MOD_ID, "self"), FormSelf::new);
    }
}
