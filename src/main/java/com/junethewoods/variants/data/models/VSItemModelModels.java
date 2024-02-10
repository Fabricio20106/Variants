package com.junethewoods.variants.data.models;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public abstract class VSItemModelModels extends ItemModelProvider {
    private final ModelFile generated = getExistingFile(mcLoc("item/generated"));
    private final ModelFile spyglass = getExistingFile(modLoc("item/template_spyglass"));

    public VSItemModelModels(DataGenerator generator, String modID, ExistingFileHelper fileHelper) {
        super(generator, modID, fileHelper);
    }

    public void standard(ModelFile parent, String name) {
        getBuilder(name).parent(parent).texture("layer0", "item/" + name);
    }

    public void toolSet(ModelFile parent, String material) {
        getBuilder(material + "_sword").parent(parent).texture("layer0", "item/" + material + "_sword");
        getBuilder(material + "_pickaxe").parent(parent).texture("layer0", "item/" + material + "_pickaxe");
        getBuilder(material + "_shovel").parent(parent).texture("layer0", "item/" + material + "_shovel");
        getBuilder(material + "_axe").parent(parent).texture("layer0", "item/" + material + "_axe");
        getBuilder(material + "_hoe").parent(parent).texture("layer0", "item/" + material + "_hoe");
    }

    public void armorSet(ModelFile parent, String material) {
        getBuilder(material + "_helmet").parent(parent).texture("layer0", "item/" + material + "_helmet");
        getBuilder(material + "_chestplate").parent(parent).texture("layer0", "item/" + material + "_chestplate");
        getBuilder(material + "_leggings").parent(parent).texture("layer0", "item/" + material + "_leggings");
        getBuilder(material + "_boots").parent(parent).texture("layer0", "item/" + material + "_boots");
    }

    public void block(String name) {
        withExistingParent(name, modLoc("block/" + name));
    }

    public void block(String name, String extras) {
        withExistingParent(name, modLoc("block/" + name + extras));
    }

    public void blockItem(String name) {
        getBuilder(name).parent(generated).texture("layer0", "block/" + name);
    }

    public void blockItem(String name, String extras) {
        getBuilder(name).parent(generated).texture("layer0", "block/" + name + extras);
    }

    public void glassPane(String name) {
        getBuilder(name + "_pane").parent(generated).texture("layer0", "block/" + name);
    }

    public ResourceLocation bowlId() {
        return new ResourceLocation("bowl_id");
    }

    public ResourceLocation armorDesign() {
        return new ResourceLocation("design");
    }

    // Methods for making specific items (for example, spyglasses)
    public void expoStew(String name, String stewType) {
        String[] bowls = {"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "painting", "crimson", "warped", "ender"};

        for (String bowl : bowls) {
            getBuilder(name + "_" + bowl).parent(generated).texture("layer0", modLoc("item/" + bowl + "_bowl")).texture("layer1", modLoc("item/stew_" + stewType));
        }

        getBuilder(name).parent(generated).texture("layer1", modLoc("item/stew_" + stewType))
                .override().predicate(bowlId(), 0).model(getExistingFile(modLoc("item/" + name + "_oak"))).end()
                .override().predicate(bowlId(), 1).model(getExistingFile(modLoc("item/" + name + "_spruce"))).end()
                .override().predicate(bowlId(), 2).model(getExistingFile(modLoc("item/" + name + "_birch"))).end()
                .override().predicate(bowlId(), 3).model(getExistingFile(modLoc("item/" + name + "_jungle"))).end()
                .override().predicate(bowlId(), 4).model(getExistingFile(modLoc("item/" + name + "_acacia"))).end()
                .override().predicate(bowlId(), 5).model(getExistingFile(modLoc("item/" + name + "_dark_oak"))).end()
                .override().predicate(bowlId(), 6).model(getExistingFile(modLoc("item/" + name + "_painting"))).end()
                .override().predicate(bowlId(), 7).model(getExistingFile(modLoc("item/" + name + "_crimson"))).end()
                .override().predicate(bowlId(), 8).model(getExistingFile(modLoc("item/" + name + "_warped"))).end()
                .override().predicate(bowlId(), 9).model(getExistingFile(modLoc("item/" + name + "_ender"))).end();
    }

    public void spyglass(String name) {
        getBuilder(name).parent(spyglass).texture("spyglass", "item/" + name);
    }
}
