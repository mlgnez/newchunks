package jrv1000.base_finder.mixin;

import jrv1000.base_finder.client.Base_finderClient;
import jrv1000.base_finder.config.Config;
import jrv1000.base_finder.render.RenderChunk;
import me.x150.renderer.render.Renderer2d;
import me.x150.renderer.render.Renderer3d;
import me.x150.renderer.util.RendererUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Environment(EnvType.CLIENT)
@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {

    @Inject(at = @At("HEAD"), method = "tick")
    public void tick(CallbackInfo ci){
        if(Base_finderClient.keyBinding.isPressed())
        {
            MinecraftClient.getInstance().setScreen(Config.MakeConfig().build());
        }

        MatrixStack stack = new MatrixStack();
        stack.scale(1,1,1);

        RendererUtils.setupRender();

    }

}
