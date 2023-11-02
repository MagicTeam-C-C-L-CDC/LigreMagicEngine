package ru.littleligr.magic.engine.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Nest;
import ru.littleligr.magic.engine.LigreMagicEngine;

@Config(name = LigreMagicEngine.MOD_ID, wrapperName = "LigreMagicEngineConfig")
public class LigreMagicEngineConfigModel {
    public float basePlayerManaCapacity = 200;
    public float basePlayerManaAbsorb = 1f;
    @Nest
    public Spell spells = new Spell();

    public static class Spell {
        public boolean registerLigreMagicEngineSpells = true;
    }
}