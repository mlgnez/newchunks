package jrv1000.base_finder.render;

import jrv1000.base_finder.client.Base_finderClient;
import jrv1000.base_finder.config.Config;
import me.x150.renderer.render.Renderer3d;
import me.x150.renderer.util.RendererUtils;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderContext;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import org.joml.Vector3i;

import java.awt.*;

public class ChunkRenderer implements WorldRenderEvents.Last{
    @Override
    public void onLast(WorldRenderContext context) {

        Renderer3d.renderThroughWalls();

        //add if for config
        if(Config.getNewchunks()){

            for(int i = 0; i < Base_finderClient.newChunks.size(); i++){

                ChunkPos chunk = Base_finderClient.newChunks.get(i);

                PlayerEntity player = MinecraftClient.getInstance().player;

                if(Math.abs(player.getPos().x - chunk.x * 16) + Math.abs(player.getPos().y- player.getPos().y) + Math.abs(player.getPos().z - chunk.z * 16) < Config.getChunkrenderdistance()){
                    MatrixStack matrixStack = new MatrixStack();
                    matrixStack.scale(1, 1, 1);

                    RenderChunk.RenderChunkOutline(new Vec3d(chunk.x * 16, 50, chunk.z * 16), matrixStack, Color.RED);
                }

            }
        }




    }
}
