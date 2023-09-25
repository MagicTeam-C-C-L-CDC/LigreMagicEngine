package ru.littleligr.magic.engine.ui.dynamichud.widgets;


import com.tanishisherewith.dynamichud.widget.Widget;
import com.tanishisherewith.dynamichud.widget.WidgetBox;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.PlayerMagicData;
import ru.littleligr.magic.engine.spell.common.IMagicData;

public class ManaWidget extends Widget {
    private static final Identifier MANA_BAR = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/mana_bar.png");
    private static final Identifier HEALTH_BAR = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/health_bar.png");
    private static final Identifier MANA_BAR_EMPTY = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/mana_bar_empty.png");
    private static final Identifier HEALTH_BAR_EMPTY = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/health_bar_empty.png");

    private PlayerMagicData data;

    private int width = 16, height = 16;
    /**
     * Constructs a Widget object.
     *
     * @param client The Minecraft client instance
     */
    public ManaWidget(MinecraftClient client) {
        super(client, "");
        setDraggable(true);
        setWidth(182);
        setHeight(5);
        xPercent =0.40416667F;
        yPercent = 0.9388889F;
    }



    @Override
    public void setTextGeneratorFromLabel() {

    }


    @Override
    public WidgetBox getWidgetBox() {
        return new WidgetBox(this.getX(), this.getY(), this.getX() + this.getWidth(), this.getY() + this.getHeight(), scale);
    }

    @Override
    public void render(DrawContext drawContext) {
        drawContext.drawTexture(MANA_BAR_EMPTY, getX(), getY(), 0, 0, getWidth(), getHeight(), getWidth(), getHeight());
        drawContext.drawTexture(MANA_BAR, getX(), getY(), 0, 0, Math.round(getWidthFromMana()), getHeight(), getWidth(), getHeight());
    }

    public float getWidthFromMana() {
        if (data == null) {
            if (this.client.player == null)
                return width;
            else
                data = ((IMagicData) this.client.player).getPlayerMagicData();
        }

        return width * data.manaPercent();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void writeToTag(NbtCompound tag) {
        tag.putString("class", getClass().getName());
        tag.putBoolean("isDraggable", isDraggable);
        tag.putFloat("xPercent", xPercent);
        tag.putFloat("yPercent", yPercent);
        tag.putBoolean("Enabled", enabled);
        tag.putFloat("scale", scale);
    }
}
