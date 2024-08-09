package melonystudios.variants.data.bowltype;

import melonystudios.variants.Variants;
import melonystudios.variants.stew.bowl.BowlTypes;
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
        this.add("oak", BowlTypes.OAK);
        this.add("spruce", BowlTypes.SPRUCE);
        this.add("birch", BowlTypes.BIRCH);
        this.add("jungle", BowlTypes.JUNGLE);
        this.add("acacia", BowlTypes.ACACIA);
        this.add("dark_oak", BowlTypes.DARK_OAK);
        this.add("painting", BowlTypes.PAINTING);
        this.add("crimson", BowlTypes.CRIMSON);
        this.add("warped", BowlTypes.WARPED);
        this.add("enderwood", BowlTypes.ENDERWOOD);
    }
}
