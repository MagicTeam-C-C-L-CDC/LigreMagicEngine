package ru.littleligr.magic.engine.ui.dynamichud.widgets;


import com.tanishisherewith.dynamichud.widget.Widget;
import com.tanishisherewith.dynamichud.widget.WidgetBox;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;
import org.joml.Vector4i;
import ru.littleligr.magic.engine.LigreMagicEngine;
import ru.littleligr.magic.engine.spell.PlayerMagicData;
import ru.littleligr.magic.engine.spell.common.IMagicData;

import java.util.List;

public class HotbarWidget extends Widget {

    public Mode mode = Mode.ITEMS;

    protected final Identifier WIDGETS = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/widgets.png");
    protected final Identifier SKILLS = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/skills_small.png");
    protected final List<Vector4i> SKILLS_TEXTURES_BOX = List.of(
            new Vector4i(101,23,  16, 16),
            new Vector4i(101,122,  16, 16),
            new Vector4i(42,63,  16, 16)
    );
    private int width = 16, height = 16;

    protected PlayerEntity player;
    protected PlayerMagicData data;

    public HotbarWidget(MinecraftClient client) {
        super(client, "");
        setWidth(182);
        setHeight(22);
        setDraggable(true);
        xPercent = 0.40416667F;
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

        if (data == null)
            data = ((IMagicData)player).getPlayerMagicData();

        if (mode == Mode.ITEMS)
            renderItemHotbar(drawContext);
        else renderMagicHotbar(drawContext);
    }

    private void renderMagicHotbar(DrawContext context) {
        context.drawTexture(WIDGETS, getX(), getY(), 0, 0, getWidth(), getHeight());
        for(int i = 0; i < SKILLS_TEXTURES_BOX.size(); i++){
            Vector4i uv = SKILLS_TEXTURES_BOX.get(i);
            context.drawTexture(SKILLS, getX() + i * 20 + 3, getY() + 3, uv.x, uv.y, uv.z, uv.w, 239, 162);
        }
        context.drawTexture(WIDGETS, getX() + data.getSelectedDelivery() * 20 - 1, getY() -1, 0, 22, 24, 22);
    }

    private void renderItemHotbar(DrawContext context) {
       context.drawTexture(WIDGETS, getX(), getY(), 0, 0, getWidth(), getHeight());
        for(int itemIndex = 0; itemIndex < 9; ++itemIndex) {
            ItemStack stack = player.getInventory().main.get(itemIndex);

            context.drawItem(stack, getX() + itemIndex * 20 + 3, getY() + 3);
            if (stack.getCount() > 1) {
                context.getMatrices().push();
                context.getMatrices().translate(0.0F, 0.0F, 200.0F);
                String stackCount = String.valueOf(stack.getCount());
                context.drawText(client.textRenderer, stackCount, getX() + itemIndex * 20 + 20 - client.textRenderer.getWidth(stackCount), getY() + 12, 16777215, true);
                context.getMatrices().pop();
            }
        }


        context.drawTexture(WIDGETS, getX() + player.getInventory().selectedSlot * 20 - 1, getY() -1, 0, 22, 24, 22);
        /*
        PlayerEntity playerEntity = this.getCameraPlayer();
        if (playerEntity != null) {
            ItemStack itemStack = playerEntity.getOffHandStack();
            Arm arm = playerEntity.getMainArm().getOpposite();
            int i = this.scaledWidth / 2;
            int j = true;
            int k = true;
            context.getMatrices().push();
            context.getMatrices().translate(0.0F, 0.0F, -90.0F);
            context.drawTexture(WIDGETS_TEXTURE, i - 91, this.scaledHeight - 22, 0, 0, 182, 22);
            context.drawTexture(WIDGETS_TEXTURE, i - 91 - 1 + playerEntity.getInventory().selectedSlot * 20, this.scaledHeight - 22 - 1, 0, 22, 24, 22);
            if (!itemStack.isEmpty()) {
                if (arm == Arm.LEFT) {
                    context.drawTexture(WIDGETS_TEXTURE, i - 91 - 29, this.scaledHeight - 23, 24, 22, 29, 24);
                } else {
                    context.drawTexture(WIDGETS_TEXTURE, i + 91, this.scaledHeight - 23, 53, 22, 29, 24);
                }
            }

            context.getMatrices().pop();
            int l = 1;

            int m;
            int n;
            int o;
            for(m = 0; m < 9; ++m) {
                n = i - 90 + m * 20 + 2;
                o = this.scaledHeight - 16 - 3;
                this.renderHotbarItem(context, n, o, tickDelta, playerEntity, (ItemStack)playerEntity.getInventory().main.get(m), l++);
            }

            if (!itemStack.isEmpty()) {
                m = this.scaledHeight - 16 - 3;
                if (arm == Arm.LEFT) {
                    this.renderHotbarItem(context, i - 91 - 26, m, tickDelta, playerEntity, itemStack, l++);
                } else {
                    this.renderHotbarItem(context, i + 91 + 10, m, tickDelta, playerEntity, itemStack, l++);
                }
            }

            RenderSystem.enableBlend();
            if (this.client.options.getAttackIndicator().getValue() == AttackIndicator.HOTBAR) {
                float f = this.client.player.getAttackCooldownProgress(0.0F);
                if (f < 1.0F) {
                    n = this.scaledHeight - 20;
                    o = i + 91 + 6;
                    if (arm == Arm.RIGHT) {
                        o = i - 91 - 22;
                    }

                    int p = (int)(f * 19.0F);
                    context.drawTexture(ICONS, o, n, 0, 94, 18, 18);
                    context.drawTexture(ICONS, o, n + 18 - p, 18, 112 - p, 18, p);
                }
            }

            RenderSystem.disableBlend();
        }

         */
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


    public void switchMode() {
        if (this.mode == Mode.ITEMS)
            mode = Mode.MAGIC;
        else mode = Mode.ITEMS;
    }

    public enum Mode {
        ITEMS,
        MAGIC
    }
}
