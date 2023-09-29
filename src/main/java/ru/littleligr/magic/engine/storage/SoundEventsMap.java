package ru.littleligr.magic.engine.storage;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public abstract class SoundEventsMap {
    public static final HashMap<Identifier, SoundEvent> map = new HashMap<>();
}
