package ru.littleligr.magic.engine.util;

import com.mojang.blaze3d.systems.RenderSystem;
import io.wispforest.owo.ui.parsing.UIParsing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.*;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.w3c.dom.Element;

import java.util.List;

public class Section {
    protected Text description = Text.literal("");
    protected List<Vec3d> points = List.of();
    protected Vec3d textPos = Vec3d.ZERO;
    protected int deliveryId = -1;

    public Section setPoints(List<Vec3d> points) {
        this.points = points;
        return this;
    }

    public static Section parse(Element element) {
        UIParsing.expectAttributes(element, "description", "delivery_id");

        Section section = new Section();

        Text description = Text.literal(element.getAttributeNode("description").getTextContent());
        int delivery_id = UIParsing.parseSignedInt(element.getAttributeNode("delivery_id"));

        section.setDescription(description);
        section.setDeliveryId(delivery_id);
        return section;
    }

    public void draw(DrawContext context, Vec3d pos, int color) {
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);

        bufferBuilder.vertex(context.getMatrices().peek().getPositionMatrix(), (float) pos.x, (float) pos.y, (float) pos.z).color(color).next();

        for (int i = points.size() - 1; i >= 0; i--)
            bufferBuilder
                    .vertex(context.getMatrices().peek().getPositionMatrix(), (float) (points.get(i).x + pos.x), (float) (points.get(i).y + pos.y), (float) (points.get(i).z + pos.z))
                    .color(color)
                    .next();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        Tessellator.getInstance().draw();

        context.drawText(MinecraftClient.getInstance().textRenderer, description, (int) (textPos.x + pos.x), (int) (textPos.y + pos.y), 0xffffffff, false);
       }

    public double distanceToSideCenter (Vec3d pos) {
        return pos.distanceTo(points.get(points.size() / 2));
    }

    public Text getDescription() {
        return description;
    }

    public void setDescription(Text text) {
        this.description = text;
    }

    public Vec3d getTextPos() {
        return textPos;
    }

    public void setTextPos(Vec3d textPos) {
        this.textPos = textPos;
    }

    public int getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(int delivery_id) {
        this.deliveryId = delivery_id;
    }


}
