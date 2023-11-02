package ru.littleligr.magic.engine.storage.vanilla;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public abstract class VanillaSounds {
    public static final HashMap<Identifier, SoundEvent> map = new HashMap<>();
}
