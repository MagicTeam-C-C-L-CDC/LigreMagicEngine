package ru.littleligr.magic.engine.ui.dynamichud.widgets;


import com.tanishisherewith.dynamichud.widget.Widget;
import com.tanishisherewith.dynamichud.widget.WidgetBox;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import ru.littleligr.magic.engine.LigreMagicEngine;

public class ArmWidget extends Widget{

    protected final Identifier WIDGETS = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/widgets.png");

    private int width = 16, height = 16;

    protected PlayerEntity player;

    public ArmWidget(MinecraftClient client) {
        super(client, "");
        setWidth(29);
        setHeight(24);
        setDraggable(true);
        xPercent = 0.38F;
        yPercent = 0.9F;
    }

    @Override
    public void setTextGeneratorFromLabel() {

    }

    @Override
    public WidgetBox getWidgetBox() {
        return new WidgetBox(getX(), getY(), getX() + getWidth(), getY() + getHeight(), scale);
    }

    @Override
    public void render(DrawContext drawContext) {
        if (player == null) {
            player = client.player;
            return;
        }

        ItemStack itemStack = player.getOffHandStack();
        if (!itemStack.isEmpty()) {
            drawContext.drawTexture(WIDGETS, getX(), getY(), 24, 22, getWidth(), getHeight());
            drawContext.drawItem(itemStack, getX() + 3, getY() + 4);
        }
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
