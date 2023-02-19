package jrv1000.base_finder.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public class Base_finderClient implements ClientModInitializer {

    public static KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {

        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.jrv1000.openconfig", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_G, // The keycode of the key
                "category.jrv1000.config" // The translation key of the keybinding's category.
        ));

    }
}