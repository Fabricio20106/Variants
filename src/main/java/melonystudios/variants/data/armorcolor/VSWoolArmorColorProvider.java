package melonystudios.variants.data.armorcolor;

import melonystudios.variants.Variants;
import melonystudios.variants.item.custom.armor.color.WoolArmorColor;
import net.minecraft.data.DataGenerator;

import javax.annotation.Nonnull;

public class VSWoolArmorColorProvider extends WoolArmorColorProvider {
    public VSWoolArmorColorProvider(DataGenerator generator) {
        super(generator, Variants.MOD_ID);
    }

    @Override
    @Nonnull
    public String getName() {
        return Variants.generatorName("Wool Armor Colors");
    }

    @Override
    protected void addWoolArmorColors() {
        this.add("glow_black", WoolArmorColor.GLOW_BLACK);
        this.add("null", WoolArmorColor.NULL_DESIGN);
    }

    public static class VanillaWACProvider extends WoolArmorColorProvider {
        public VanillaWACProvider(DataGenerator generator) {
            super(generator, "minecraft");
        }

        @Override
        @Nonnull
        public String getName() {
            return Variants.generatorName("Wool Armor Colors (Vanilla)");
        }

        @Override
        protected void addWoolArmorColors() {
            this.add("white", WoolArmorColor.WHITE);
            this.add("light_gray", WoolArmorColor.LIGHT_GRAY);
            this.add("gray", WoolArmorColor.GRAY);
            this.add("black", WoolArmorColor.BLACK);
            this.add("brown", WoolArmorColor.BROWN);
            this.add("red", WoolArmorColor.RED);
            this.add("orange", WoolArmorColor.ORANGE);
            this.add("yellow", WoolArmorColor.YELLOW);
            this.add("lime", WoolArmorColor.LIME);
            this.add("green", WoolArmorColor.GREEN);
            this.add("cyan", WoolArmorColor.CYAN);
            this.add("light_blue", WoolArmorColor.LIGHT_BLUE);
            this.add("blue", WoolArmorColor.BLUE);
            this.add("purple", WoolArmorColor.PURPLE);
            this.add("magenta", WoolArmorColor.MAGENTA);
            this.add("pink", WoolArmorColor.PINK);
        }
    }

    public static class F10ElementsWACProvider extends WoolArmorColorProvider {
        public F10ElementsWACProvider(DataGenerator generator) {
            super(generator, "f10elements");
        }

        @Override
        @Nonnull
        public String getName() {
            return Variants.generatorName("Wool Armor Colors (F10 Elements)");
        }

        @Override
        protected void addWoolArmorColors() {
            this.add("inno", WoolArmorColor.INNO);
        }
    }
}
