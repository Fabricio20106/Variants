package melonystudios.variants.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFW;

public class VSKeys {
    public static final KeyBinding SHOW_TAGS_KEY = new KeyBinding("key.melony_studios.show_tags", GLFW.GLFW_KEY_LEFT_ALT, "key.categories.melony_studios");

    @OnlyIn(Dist.CLIENT)
    public static boolean isAltDown() {
        return InputMappings.isKeyDown(Constants.MINECRAFT_WINDOW, SHOW_TAGS_KEY.getKey().getValue());
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isShiftDown() {
        return Minecraft.getInstance().options.keyShift.isDown();
    }

    public static TranslationTextComponent getTranslation(KeyBinding keyBind) {
        return new TranslationTextComponent(keyBind.saveString());
    }
}
