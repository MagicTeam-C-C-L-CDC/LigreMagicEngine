package ru.littleligr.magic.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.wispforest.owo.network.OwoNetChannel;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.littleligr.magic.engine.adapter.AdapterData;
import ru.littleligr.magic.engine.config.LigreMagicEngineAdapters;
import ru.littleligr.magic.engine.config.LigreMagicEngineConfig;
import ru.littleligr.magic.engine.config.LigreMagicEngineForms;
import ru.littleligr.magic.engine.network.LigreMagicEngineNetworks;
import ru.littleligr.magic.engine.storage.lme.Adapters;
import ru.littleligr.magic.engine.storage.lme.Effects;
import ru.littleligr.magic.engine.storage.lme.Forms;
import ru.littleligr.magic.engine.storage.lme.Spells;
import ru.littleligr.magic.engine.utils.DataFolders;
import ru.littleligr.magic.engine.utils.StagedList;
import ru.littleligr.magic.engine.utils.serializaer.AdapterDataDeserializer;
import ru.littleligr.magic.engine.utils.serializaer.IdentifierDeserializer;
import ru.littleligr.magic.engine.utils.serializaer.StagedListDeserializer;

public class LigreMagicEngine implements ModInitializer {

	public static final String MOD_ID = "lme";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static final OwoNetChannel OWO_NET_CHANNEL = OwoNetChannel.create(new Identifier(MOD_ID, "main"));
	public static final LigreMagicEngineConfig CONFIG = LigreMagicEngineConfig.createAndLoad();
	public static final Effects EFFECTS = new Effects();
	public static final Forms FORMS = new Forms();
	public static final Spells SPELLS = new Spells();
	public static final Adapters ADAPTERS = new Adapters();
	public static final GsonBuilder GSON_BUILDER = new GsonBuilder()
			.setPrettyPrinting()
			.registerTypeAdapter(Identifier.class, new IdentifierDeserializer())
			.registerTypeAdapter(AdapterData.class, new AdapterDataDeserializer())
			.registerTypeAdapter(StagedList.class, new StagedListDeserializer());

	@Override
	public void onInitialize() {
		LigreMagicEngineAdapters.register(ADAPTERS);
		LigreMagicEngineForms.register(FORMS);
		LigreMagicEngineNetworks.register(OWO_NET_CHANNEL);

		ServerLifecycleEvents.SERVER_STARTED.register((minecraftServer) -> {
			SPELLS.load(minecraftServer.getResourceManager(), DataFolders.SPELLS.path, GSON_BUILDER);
			FORMS.load(minecraftServer.getResourceManager(), DataFolders.FORMS.path, GSON_BUILDER);
		});
	}
}