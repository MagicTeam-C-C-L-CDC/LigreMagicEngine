package ru.littleligr.magic.engine.storage;

import com.google.gson.GsonBuilder;
import net.minecraft.resource.ResourceManager;

public interface DataLoader {
    void load(ResourceManager resourceManager, String folder, GsonBuilder builder);
}
