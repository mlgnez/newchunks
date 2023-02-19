package jrv1000.base_finder.mixin;

import jrv1000.base_finder.client.Base_finderClient;
import jrv1000.base_finder.config.Config;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci){
        if(Base_finderClient.keyBinding.isPressed())
        {
            MinecraftClient.getInstance().setScreen(Config.MakeConfig().build());
        }
    }

}
