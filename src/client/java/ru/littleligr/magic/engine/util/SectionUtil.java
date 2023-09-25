package ru.littleligr.magic.engine.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;
import ru.littleligr.magic.engine.ui.utils.RenderUtils;

import java.util.LinkedList;
import java.util.List;

public abstract class SectionUtil {
    public static List<Section> getSections (double radius, int subdivisions, int sectionCount) { // 32 8 4
        LinkedList<Section> sections = new LinkedList<>();
        List<Vec3d> points = RenderUtils.getCircle(radius, subdivisions);

        int sectionStep = subdivisions / sectionCount; // 2

        for (int i = 0; i < sectionCount; i++) {
            int lowSubTarget = sectionStep * i; //2
            int highSubTarget = sectionStep * (i + 1); //4

            LinkedList<Vec3d> sectionPoints = new LinkedList<>();

            for (int currentSub = lowSubTarget; currentSub <= highSubTarget; currentSub ++)
                sectionPoints.add(points.get(currentSub));

            sections.add(new Section().setPoints(sectionPoints.stream().toList()));
        }

        return sections;
    }

    public static void fillSections (List<Section> sections, double radius, int subdivisions, int innerRadius) { // 32 8 4
        List<Vec3d> points = RenderUtils.getCircle(radius, subdivisions);

        int sectionStep = subdivisions / sections.size(); // 16 / 6 = 2,2/3

        for (int i = 0; i < sections.size(); i++) {
            int lowSubTarget = sectionStep * i; //2
            int highSubTarget = sectionStep * (i + 1); //4

            Section section = sections.get(i);
            section.points = new LinkedList<>();

            for (int currentSub = lowSubTarget; currentSub <= highSubTarget; currentSub ++)
                section.points.add(points.get(currentSub));

            Vec3d corner = section.points.get(section.points.size() / 2);
            double distance = corner.distanceTo(Vec3d.ZERO);
            double textDistance = ((distance + innerRadius) / 2) / distance;
            int textWidth = MinecraftClient.getInstance().textRenderer.getWidth(section.description.asOrderedText()) / 2;
            section.setTextPos(corner.multiply(textDistance).subtract(textWidth, 0, 0));
        }
    }
}
