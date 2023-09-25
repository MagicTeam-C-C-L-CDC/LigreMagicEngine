package ru.littleligr.magic.engine.ui.owo.components;

import io.wispforest.owo.ui.base.BaseComponent;
import io.wispforest.owo.ui.core.*;
import io.wispforest.owo.ui.parsing.UIModel;
import io.wispforest.owo.ui.parsing.UIParsing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import ru.littleligr.magic.engine.spell.PlayerMagicData;
import ru.littleligr.magic.engine.spell.common.IMagicData;
import ru.littleligr.magic.engine.ui.utils.RenderUtils;
import ru.littleligr.magic.engine.util.Section;
import ru.littleligr.magic.engine.util.SectionUtil;

import java.util.*;

public class RoundSelector extends BaseComponent {
    private final int OUTER_COLOR = 0xbb2a2a2a;
    private final int INNER_COLOR = 0xff2a2a2a;
    private final int ON_HOVER_COLOR = 0xbbffbc30;

    protected int radius, innerRadius, subdivisions;

    protected List<Section> sections = List.of();
    protected List<Vec3d> circle;

    protected int previousSelectedIndex = 0;

    @Nullable
    protected PlayerMagicData playerMagicData;

    public RoundSelector(int radius, int innerRadius, int subdivisions) {
        this.radius = radius;
        this.innerRadius = innerRadius;
        this.subdivisions = subdivisions;
        circle = RenderUtils.getCircle(innerRadius, subdivisions);
        playerMagicData = ((IMagicData) MinecraftClient.getInstance().player).getPlayerMagicData();
    }

    @Override
    public void draw(OwoUIDrawContext context, int mouseX, int mouseY, float partialTicks, float delta) {
        Vec3d mousePos = new Vec3d(mouseX - x, mouseY - y, 0);

        int selectedIndex = previousSelectedIndex;

        List<Double> distance = sections.stream().map(section -> section.distanceToSideCenter(mousePos)).toList();

        for (int i = 0; i < distance.size(); i++)
            if (distance.get(i) < distance.get(selectedIndex))
                selectedIndex = i;

        if (selectedIndex != previousSelectedIndex)
            playerMagicData.selectDelivery(sections.get(selectedIndex).getDeliveryId());

        previousSelectedIndex = selectedIndex;

        for(int i = 0; i < sections.size(); i++)
            sections.get(i).draw(context, new Vec3d(x, y, 0), i == selectedIndex ? ON_HOVER_COLOR : OUTER_COLOR);

        RenderUtils.drawCircle(
                circle,
                Tessellator.getInstance(),
                context.getMatrices().peek().getPositionMatrix(),
                new Vec3d(x, y, 0),
                INNER_COLOR
        );

        int textWidth = MinecraftClient.getInstance().textRenderer.getWidth(sections.get(previousSelectedIndex).getDescription().asOrderedText()) / 2;
        context.drawText(MinecraftClient.getInstance().textRenderer, sections.get(previousSelectedIndex).getDescription(), x - textWidth, y, 0xffffffff, true);
    }

    public static RoundSelector parse(Element element) {
        UIParsing.expectAttributes(element, "radius", "innerRadius", "subdivisions");

        int radius = UIParsing.parseSignedInt(element.getAttributeNode("radius"));
        int innerRadius = UIParsing.parseSignedInt(element.getAttributeNode("innerRadius"));
        int subdivisions = UIParsing.parseSignedInt(element.getAttributeNode("subdivisions"));

        return new RoundSelector(radius, innerRadius, subdivisions);
    }

    @Override
    public void parseProperties(UIModel model, Element element, Map<String, Element> children) {
        super.parseProperties(model, element, children);
        List<Element> elements = UIParsing
                .get(children, "children", e -> UIParsing.<Element>allChildrenOfType(e, Node.ELEMENT_NODE))
                .orElse(Collections.emptyList());

        List<Section> sections = elements.stream().map(Section::parse).toList();
        SectionUtil.fillSections(sections, radius, subdivisions, innerRadius);

        this.sections = sections;
    }

    @Override
    protected int determineHorizontalContentSize(Sizing sizing) {
        return radius * 2;
    }

    @Override
    protected int determineVerticalContentSize(Sizing sizing) {
        return  radius * 2;
    }

    @Override
    public void inflate(Size space) {
    }
}
