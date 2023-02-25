package jrv1000.base_finder.mixin;

import jrv1000.base_finder.client.Base_finderClient;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.ChunkDataS2CPacket;
import net.minecraft.util.math.ChunkPos;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkDataS2CPacket.class)
public class ChunkDataS2CPacketMixin {

    @Shadow @Final private int chunkX;

    @Shadow @Final private int chunkZ;

    @Inject(method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V", at = @At("HEAD"))
    public void apply(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci){

        ChunkPos pos = new ChunkPos(this.chunkX, this.chunkZ);

        if(!Base_finderClient.newChunks.contains(pos)){
            Base_finderClient.newChunks.add(pos);
        }

    }

}
