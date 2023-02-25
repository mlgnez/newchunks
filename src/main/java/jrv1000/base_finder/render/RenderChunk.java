package jrv1000.base_finder.render;

import me.x150.renderer.render.Renderer3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class RenderChunk {

    public static void RenderChunkOutline(Vec3d centerPos, MatrixStack matrixStack, Color color){

        Vec3d pp = new Vec3d(centerPos.x + 8, centerPos.y, centerPos.z + 8);
        Vec3d pn = new Vec3d(centerPos.x + 8, centerPos.y, centerPos.z - 8);
        Vec3d nn = new Vec3d(centerPos.x - 8, centerPos.y, centerPos.z - 8);
        Vec3d np = new Vec3d(centerPos.x - 8, centerPos.y, centerPos.z + 8);

        Renderer3d.renderLine(matrixStack, color, pp, pn);
        Renderer3d.renderLine(matrixStack, color, pn, nn);
        Renderer3d.renderLine(matrixStack, color, nn, np);
        Renderer3d.renderLine(matrixStack, color, np, pp);

    }

}
