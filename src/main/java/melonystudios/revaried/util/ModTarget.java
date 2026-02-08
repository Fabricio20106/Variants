package melonystudios.revaried.util;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

/// Represents the target mod of a compatibility item.
/// @param modID The mod id of the mod.
/// @param modName The mod's name, used if a valid translation cannot be found.
public record ModTarget(String modID, String modName) {
    public static final ModTarget F10_ELEMENTS = new ModTarget("f10elements", "F10 Elements");

    /// @return The display name of this mod as a {@link Component}, using *Mod Menu*'s format for translations.
    public Component getDisplayName() {
        return I18n.exists("modmenu.nameTranslation." + this.modID()) ? Component.translatable("modmenu.nameTranslation." + this.modID()) : Component.literal(this.modName());
    }
}
