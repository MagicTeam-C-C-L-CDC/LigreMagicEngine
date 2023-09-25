package ru.littleligr.magic.engine.ui.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import java.util.LinkedList;
import java.util.List;

public class RenderUtils {
    public static Vec3d getCirclePoint (Vec3d center, double radius, double angle) {
        return new Vec3d(
                center.x + radius * Math.cos(angle),
                center.y + radius * Math.sin(angle),
                center.z
        );
    }

    public static List<Vec3d> getCircleWedge (double radius, double angle, int subdivisions) {
        List<Vec3d> circlePoints = new LinkedList<>();

        double angleOffset = angle / subdivisions;

        for (int i = 0; i < subdivisions; i ++)
            circlePoints.add(getCirclePoint(Vec3d.ZERO, radius, i * angleOffset));

        circlePoints.add(circlePoints.get(0));


        return circlePoints;
    }

    public static List<Vec3d> getCircle (double radius, int subdivisions) {
        return getCircleWedge (radius, 2 * Math.PI, subdivisions);
    }



    public static void drawCircle (List<Vec3d> vertexes, Tessellator tessellator, Matrix4f positionMatrix, Vec3d pos, int color) {
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_COLOR);


        for (int i = vertexes.size() - 1; i >= 0; i--)
            bufferBuilder
                    .vertex(positionMatrix, (float) (vertexes.get(i).x + pos.x), (float) (vertexes.get(i).y + pos.y), (float) (vertexes.get(i).z + pos.z))
                    .color(color)
                    .next();

        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionColorProgram);

        Tessellator.getInstance().draw();
    }
/*
    public static void addCircleTextured (Tessellator tessellator, Matrix4f positionMatrix, Vec3d pos, double radius, int subdivisions) {
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_TEXTURE);
        for (Vec3d point : RenderUtils.getCircle(pos, radius, subdivisions)) {
            bufferBuilder.vertex(positionMatrix, (float) point.x, (float) point.y, (float) point.z).texture((float) ((point.x - pos.x) / radius), (float) ((point.y - pos.y) / radius)).next();
        }
    }

    public static void addSection (Identifier texture, Tessellator tessellator, Matrix4f positionMatrix, Vec3d pos, double radius, int subdivisions, int sections, int section) {
        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(VertexFormat.DrawMode.TRIANGLE_FAN, VertexFormats.POSITION_TEXTURE);
        float sectionStep = (float) subdivisions / sections; //32
        int lowSubTarget = (int) (sectionStep * section); // 32 * 0 = 0
        int highSubTarget = (int) (sectionStep * (section + 1)); // 32 * 1 = 32
        int currentSub = 0;
        for (Vec3d point : RenderUtils.getCircle(pos, radius, subdivisions)) { // pos, 32, 128
            if (currentSub > lowSubTarget && currentSub <= highSubTarget + 1) // 0 / 32
                bufferBuilder.vertex(positionMatrix, (float) point.x, (float) point.y, (float) point.z).texture((float) ((point.x - pos.x) / radius), (float) ((point.y - pos.y) / radius)).next();
            currentSub += 1;
        }
        bufferBuilder.vertex(positionMatrix, (float) pos.x, (float) pos.y, (float) pos.z).texture( 0.5f, 0.5f).next();
    }

 */
}