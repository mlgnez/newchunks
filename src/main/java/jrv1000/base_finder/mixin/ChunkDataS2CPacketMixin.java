package jrv1000.base_finder.mixin;

import jrv1000.base_finder.client.Base_finderClient;
import net.minecraft.client.MinecraftClient;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.ChunkData;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.chunk.WorldChunk;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkDataS2CPacket.class)
public abstract class ChunkDataS2CPacketMixin {

    @Shadow @Final private int chunkX;

    @Shadow @Final private int chunkZ;

    @Shadow public abstract ChunkData getChunkData();

    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At("HEAD"))
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci){

        ChunkPos pos = new ChunkPos(this.chunkX, this.chunkZ);

        if (!Base_finderClient.newChunks.contains(pos) && MinecraftClient.getInstance().player.world.getChunkManager().getChunk(this.chunkX, this.chunkZ) == null) {

            WorldChunk chunk = new WorldChunk(MinecraftClient.getInstance().player.world, pos);

            try {
                chunk.loadFromPacket(this.getChunkData().getSectionsDataBuf(), new NbtCompound(), this.getChunkData().getBlockEntities(this.chunkX, this.chunkZ));
            } catch (ArrayIndexOutOfBoundsException e) {
                return;
            }

            for (int x = 0; x < 16; x++) {
                for (int y = MinecraftClient.getInstance().player.world.getBottomY(); y < MinecraftClient.getInstance().player.world.getTopY(); y++) {
                    for (int z = 0; z < 16; z++) {
                        FluidState fluid = chunk.getFluidState(x, y, z);

                        if (!fluid.isEmpty() && !fluid.isStill()) {
                            Base_finderClient.oldChunks.add(pos);
                            return;
                        }
                    }
                }
            }

        }

        /*if(!Base_finderClient.newChunks.contains(pos)){
            Base_finderClient.newChunks.add(pos);
        }*/

    }

}
