package com.junethewoods.variants.core.inventory;

import com.junethewoods.variants.core.init.VSTags;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;

public class VSCraftingMenu extends CraftingMenu {
    private final ContainerLevelAccess access;

    public VSCraftingMenu(int containerId, Inventory inventory, ContainerLevelAccess worldAccess) {
        super(containerId, inventory, worldAccess);
        this.access = worldAccess;
    }

    protected static boolean stillValid(ContainerLevelAccess access, Player player) {
        return access.evaluate((world, pos) -> world.getBlockState(pos).is(VSTags.Blocks.CRAFTING_TABLES) && player.distanceToSqr((double) pos.getX() + 0.5d,
                (double) pos.getY() + 0.5d, (double) pos.getZ() + 0.5d) <= 64, true);
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(this.access, player);
    }
}
