package jrv1000.base_finder.client;

import jrv1000.base_finder.render.ChunkRenderer;
import jrv1000.base_finder.render.RenderChunk;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.WorldRenderEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.math.ChunkPos;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class Base_finderClient implements ClientModInitializer {

    public static KeyBinding keyBinding;

    public static List<ChunkPos> newChunks = new ArrayList<>();
    public static List<ChunkPos> oldChunks = new ArrayList<>();

    @Override
    public void onInitializeClient() {

        newChunks.add(new ChunkPos(0,0));
        newChunks.add(new ChunkPos(0,1));
        newChunks.add(new ChunkPos(1,0));
        newChunks.add(new ChunkPos(1,1));

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.jrv1000.openconfig", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_G, // The keycode of the key
                "category.jrv1000.config" // The translation key of the keybinding's category.
        ));

        WorldRenderEvents.LAST.register(new ChunkRenderer());

    }
}
