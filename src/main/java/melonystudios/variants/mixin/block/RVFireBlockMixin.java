package melonystudios.variants.mixin.block;

import melonystudios.variants.util.InterfaceMethods;
import net.minecraft.block.Block;
import net.minecraft.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FireBlock.class)
public abstract class RVFireBlockMixin extends Block implements InterfaceMethods.FireBlockMethods {
    @Shadow
    protected abstract void setFlammable(Block block, int encouragement, int flammability);

    public RVFireBlockMixin(Properties properties) {
        super(properties);
    }

    @Override
    public void flammable(Block block, int encouragement, int flammability) {
        this.setFlammable(block, encouragement, flammability);
    }
}
