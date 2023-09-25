package ru.littleligr.magic.engine.ui.owo.components;

import io.wispforest.owo.ui.parsing.UIModel;
import io.wispforest.owo.ui.parsing.UIParsing;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.util.math.Vector2f;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.joml.Math;
import org.w3c.dom.Element;
import ru.littleligr.magic.engine.LigreMagicEngine;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class TexturedButtonComponent extends ButtonWidget {
    protected int u = 0;
    protected int v = 0;
    protected int textureWidth = 32;
    protected int textureHeight = 32;
    protected Identifier texture;
    protected Identifier onClickTexture;
    protected Identifier hoverTexture;

    private List<Vector2f> CIRCLE_POINTS;
    private final Identifier GRAY_BACKGROUND = new Identifier(LigreMagicEngine.MOD_ID, "textures/gui/ui_gray_circle.png");

    public TexturedButtonComponent(Identifier texture, int u, int v, int width, int height, int textureWidth, int textureHeight) {
        super(0, 0, width, height, Text.empty(), (x) -> {}, ButtonWidget.DEFAULT_NARRATION_SUPPLIER);
        this.u = u;
        this.v = v;
        this.textureWidth = width;
        this.textureHeight = textureHeight;
        this.texture = texture;

        LinkedList<Vector2f> points = new LinkedList<>();
        for (float i = 0, step = 1f/4; i <= 1; i += step) {
            Vector2f point = new Vector2f(1 - i * 2, (float) Math.sin(Math.PI * i));
            points.add(point);
        }

        //points.forEach((p) -> p.mul(64));
        CIRCLE_POINTS = List.copyOf(points);
    }

    public void setHoverTexture(Identifier hoverTexture) {
        this.hoverTexture = hoverTexture;
    }

    public void setOnClickTexture(Identifier onClickTexture) {
        this.onClickTexture = onClickTexture;
    }

    public void setOnPress(PressAction onPress) {

    }

    @Override
    public void renderButton(DrawContext context, int mouseX, int mouseY, float delta) {
        //this.renderer.draw((OwoUIDrawContext) context, this, delta);
         context.drawTexture(texture, this.getX(), this.getY(), u, v, this.getWidth(), this.getHeight(), textureWidth, textureHeight);
       /*
        this.renderer.draw((OwoUIDrawContext) context, this, delta);

        var textRenderer = MinecraftClient.getInstance().textRenderer;
        int color = this.active ? 0xffffff : 0xa0a0a0;

        if (this.textShadow) {
            context.drawCenteredTextWithShadow(textRenderer, this.getMessage(), this.getX() + this.width / 2, this.getY() + (this.height - 8) / 2, color);
        } else {
            context.drawText(textRenderer, this.getMessage(), (int) (this.getX() + this.width / 2f - textRenderer.getWidth(this.getMessage()) / 2f), (int) (this.getY() + (this.height - 8) / 2f), color, false);
        }

        var tooltip = ((ClickableWidgetAccessor) this).owo$getTooltip();
        if (this.hovered && tooltip != null)
            context.drawTooltip(textRenderer, tooltip.getLines(MinecraftClient.getInstance()), HoveredTooltipPositioner.INSTANCE, mouseX, mouseY);


        */

        /*
        RenderSystem.setShaderTexture(0, GRAY_BACKGROUND);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        Matrix4f matrix4f = context.getMatrices().peek().getPositionMatrix();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.LINE_STRIP, VertexFormats.POSITION_TEXTURE);

        for (Vector2f point : this.CIRCLE_POINTS) {
            float u = point.getX() / 2 + 0.5f;
            float v = point.getY() / 2 + 0.5f;
            bufferBuilder.vertex(matrix4f, getX() + point.getX() * 16, getY() + point.getY() * 16, 0).texture(u, v).next();
        }
        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());

         */
/*
        RenderSystem.setShaderTexture(0, GRAY_BACKGROUND);
        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        Matrix4f matrix4f = context.getMatrices().peek().getPositionMatrix();
        BufferBuilder bufferBuilder = Tessellator.getInstance().getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_STRIP, VertexFormats.POSITION_TEXTURE);

        bufferBuilder.vertex(matrix4f, getX() + 32 * 0.5f, getY(), 0).texture(0.5f, 0).next();
        bufferBuilder.vertex(matrix4f, getX() + 32 * 0.1f, getY() + 32 * 0.1f, 0).texture(0.1f, 0.1f).next();

        bufferBuilder.vertex(matrix4f, getX() , getY() + 32 * 0.5f, 0).texture(0, 0.5f).next();
        bufferBuilder.vertex(matrix4f, getX() + 32 * 0.1f , getY() + 32 * 0.9f, 0).texture(0.1f, 0.9f).next();

        bufferBuilder.vertex(matrix4f, getX() + 32 * 0.5f , getY() + 32 * 1f, 0).texture(0.5f, 1f).next();
        bufferBuilder.vertex(matrix4f, getX() + 32 * 0.9f , getY() + 32 * 0.9f, 0).texture(0.9f, 0.9f).next();

        bufferBuilder.vertex(matrix4f, getX() + 32 * 1f, getY() + 32 * 0.5f, 0).texture(1f, 0.5f).next();
        bufferBuilder.vertex(matrix4f, getX() + 32 * 0.9f , getY() + 32 * 0.1f, 0).texture(0.9f, 0.1f).next();

        BufferRenderer.drawWithGlobalProgram(bufferBuilder.end());

 */


    }

    @Override
    public void parseProperties(UIModel model, Element element, Map<String, Element> children) {
        super.parseProperties(model, element, children);
        UIParsing.apply(children, "u", UIParsing::parseUnsignedInt, this::setU);
        UIParsing.apply(children, "v", UIParsing::parseUnsignedInt, this::setV);
        UIParsing.apply(children, "width", UIParsing::parseUnsignedInt, this::setWidth);
        UIParsing.apply(children, "height", UIParsing::parseUnsignedInt, this::setHeight);
        UIParsing.apply(children, "texture-width", UIParsing::parseUnsignedInt, this::setTextureWidth);
        UIParsing.apply(children, "texture-height", UIParsing::parseUnsignedInt, this::setTextureHeight);
        UIParsing.apply(children, "texture", UIParsing::parseIdentifier, this::setTexture);
        LigreMagicEngine.LOGGER.warn("SO, i parse some props for text but");
    }

    public static TexturedButtonComponent parse(Element element) {
        UIParsing.expectAttributes(element, "texture");
        var textureId = UIParsing.parseIdentifier(element.getAttributeNode("texture"));

        int u = 0, v = 0, regionWidth = 0, regionHeight = 0, textureWidth = 256, textureHeight = 256;
        if (element.hasAttribute("u")) {
            u = UIParsing.parseSignedInt(element.getAttributeNode("u"));
        }

        if (element.hasAttribute("v")) {
            v = UIParsing.parseSignedInt(element.getAttributeNode("v"));
        }

        if (element.hasAttribute("width")) {
            regionWidth = UIParsing.parseSignedInt(element.getAttributeNode("width"));
        }

        if (element.hasAttribute("height")) {
            regionHeight = UIParsing.parseSignedInt(element.getAttributeNode("height"));
        }

        if (element.hasAttribute("texture-width")) {
            textureWidth = UIParsing.parseSignedInt(element.getAttributeNode("texture-width"));
        }

        if (element.hasAttribute("texture-height")) {
            textureHeight = UIParsing.parseSignedInt(element.getAttributeNode("texture-height"));
        }

        return new TexturedButtonComponent(textureId, u, v, regionWidth, regionHeight, textureWidth, textureHeight);
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
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

    public int getTextureWidth() {
        return textureWidth;
    }

    public void setTextureWidth(int textureWidth) {
        this.textureWidth = textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    public void setTextureHeight(int textureHeight) {
        this.textureHeight = textureHeight;
    }

    public void setTexture(Identifier texture) {
        this.texture = texture;
        LigreMagicEngine.LOGGER.warn("I even parse texture");
    }
}
