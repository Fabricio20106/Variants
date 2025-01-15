package melonystudios.variants.data.bowltype;

import melonystudios.variants.Variants;
import melonystudios.variants.item.bowl.BowlTypes;
import net.minecraft.data.DataGenerator;

import javax.annotation.Nonnull;

public class VSBowlTypesProvider extends BowlTypesProvider {
    public VSBowlTypesProvider(DataGenerator generator) {
        super(generator, Variants.MOD_ID);
    }

    @Nonnull
    @Override
    public String getName() {
        return "Variants - Bowl Types";
    }

    @Override
    protected void addTypes() {
        this.add("painting", BowlTypes.PAINTING);
        this.add("enderwood", BowlTypes.ENDERWOOD);
    }

    public static class VanillaBowlTypesProvider extends BowlTypesProvider {
        public VanillaBowlTypesProvider(DataGenerator generator) {
            super(generator, "minecraft");
        }

        @Override
        @Nonnull
        public String getName() {
            return "Variants - Vanilla Bowl Types";
        }

        @Override
        protected void addTypes() {
            this.add("oak", BowlTypes.OAK);
            this.add("spruce", BowlTypes.SPRUCE);
            this.add("birch", BowlTypes.BIRCH);
            this.add("jungle", BowlTypes.JUNGLE);
            this.add("acacia", BowlTypes.ACACIA);
            this.add("dark_oak", BowlTypes.DARK_OAK);
            this.add("crimson", BowlTypes.CRIMSON);
            this.add("warped", BowlTypes.WARPED);
        }
    }
}
