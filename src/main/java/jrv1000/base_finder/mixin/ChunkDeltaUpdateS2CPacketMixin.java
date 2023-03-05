package jrv1000.base_finder.mixin;

import jrv1000.base_finder.client.Base_finderClient;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.ChunkDeltaUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.BiConsumer;

@Mixin(ChunkDeltaUpdateS2CPacket.class)
public abstract class ChunkDeltaUpdateS2CPacketMixin {

    private static final Direction[] searchDirs = new Direction[] { Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.UP };

    @Shadow public abstract void visitUpdates(BiConsumer<BlockPos, BlockState> visitor);

    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At("HEAD"))
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci){

        this.visitUpdates((pos, state) -> {
            if (!state.getFluidState().isEmpty() && !state.getFluidState().isStill()) {
                ChunkPos chunkPos = new ChunkPos(pos);

                for (Direction dir: searchDirs) {
                    if (MinecraftClient.getInstance().player.world.getBlockState(pos.offset(dir)).getFluidState().isStill() && !Base_finderClient.oldChunks.contains(chunkPos)) {
                        Base_finderClient.newChunks.add(chunkPos);
                        return;
                    }
                }
            }
        });

    }

}
