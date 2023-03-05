package jrv1000.base_finder.mixin;

import jrv1000.base_finder.client.Base_finderClient;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.Direction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockUpdateS2CPacket.class)
public abstract class BlockUpdateS2CPacketMixin {

    private static final Direction[] searchDirs = new Direction[] { Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.UP };

    @Shadow public abstract BlockState getState();

    @Shadow public abstract BlockPos getPos();

    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At("HEAD"))
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci){

        if(!this.getState().getFluidState().isEmpty() && !this.getState().getFluidState().isStill()){

            ChunkPos chunkPos = new ChunkPos(this.getPos());

            for (Direction dir: searchDirs) {
                if (MinecraftClient.getInstance().player.world.getBlockState(this.getPos().offset(dir)).getFluidState().isStill() && !Base_finderClient.oldChunks.contains(chunkPos)) {
                    Base_finderClient.newChunks.add(chunkPos);
                    return;
                }
            }

        }

    }

}
