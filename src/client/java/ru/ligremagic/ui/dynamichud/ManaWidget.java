package ru.ligremagic.ui;


import com.tanishisherewith.dynamichud.widget.Widget;
import com.tanishisherewith.dynamichud.widget.WidgetBox;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import ru.ligremagic.LigreMagic;
import ru.ligremagic.spell.PlayerMagicData;

public class ManaWidget extends Widget {
    private static final Identifier MANA_BAR = new Identifier(LigreMagic.MOD_ID, "textures/gui/mana_bar.png");
    private static final Identifier HEALTH_BAR = new Identifier(LigreMagic.MOD_ID, "textures/gui/health_bar.png");
    private static final Identifier MANA_BAR_EMPTY = new Identifier(LigreMagic.MOD_ID, "textures/gui/mana_bar_empty.png");
    private static final Identifier HEALTH_BAR_EMPTY = new Identifier(LigreMagic.MOD_ID, "textures/gui/health_bar_empty.png");

    private final PlayerMagicData data;
    /**
     * Constructs a Widget object.
     *
     * @param client The Minecraft client instance
     * @param data Player data manager
     */
    public ManaWidget(MinecraftClient client, PlayerMagicData data) {
        super(client, "");
        this.data = data;
    }

    @Override
    public void setTextGeneratorFromLabel() {

    }

    @Override
    public WidgetBox getWidgetBox() {
        return null;
    }

    @Override
    public void render(DrawContext drawContext) {
        drawContext.drawTexture(MANA_BAR_EMPTY, 0, 0, 0, 0, 182, 5);
        LigreMagic.LOGGER.info("DRAW MANA");
    }
}
