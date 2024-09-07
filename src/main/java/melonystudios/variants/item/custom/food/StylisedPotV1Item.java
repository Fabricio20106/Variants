package melonystudios.variants.item.custom.food;

import melonystudios.variants.stew.StewBehavior;

public class StylisedPotV1Item extends DrinkableContainerItem {
    public StylisedPotV1Item(StewBehavior behavior, Properties properties) {
        super(behavior, properties);
    }

    /*private final PoisoningType poisoningType;
    private final int poisoningDuration;
    private final String compatMod;

    public StylisedPotItem(PoisoningType poisoningType, int duration, String compatMod, Properties properties) {
        super(new DefaultStewBehavior(), properties);
        this.poisoningType = poisoningType;
        if (poisoningType.getPoisoningEffect() != null && poisoningType.getPoisoningEffect().isPresent() && poisoningType.getPoisoningEffect().get().isInstantenous()) {
            this.poisoningDuration = duration;
        } else {
            this.poisoningDuration = duration * 20;
        }
        this.compatMod = compatMod;
    }

    public StylisedPotItem(PoisoningType poisoningType, int duration, Properties properties) {
        this(poisoningType, duration, null, properties);
    }

    public boolean hasTypeInNBT(ItemStack potStack) {
        return potStack.getTag() != null && potStack.getTag().contains("poisoning_type", Constants.TagTypes.STRING);
    }

    public boolean hasEffectsInNBT(ItemStack potStack) {
        return potStack.getTag() != null && potStack.getTag().contains("effect", Constants.TagTypes.COMPOUND);
    }

    public PoisoningType getTypeFromNBT(ItemStack potStack) {
        if (hasTypeInNBT(potStack)) {
            ResourceLocation type = ResourceLocation.tryParse(potStack.getTag().getString("poisoning_type"));
            if (type != null && VSRegistries.POISONING_TYPE.containsKey(type)) return VSRegistries.POISONING_TYPE.getValue(type);
        }
        return this.poisoningType;
    }

    public TranslationTextComponent getPoisoningTranslation(ItemStack potStack) {
        TranslationTextComponent fromConstructor = new TranslationTextComponent("poisoning_type." + this.poisoningType.getTypeRegistry().getRegistryName().getNamespace() + "." + this.poisoningType.getTypeRegistry().getRegistryName().getPath());
        if (hasTypeInNBT(potStack)) {
            ResourceLocation poisoningType = ResourceLocation.tryParse(potStack.getTag().getString("poisoning_type"));
            assert poisoningType != null;
            if (!potStack.getTag().contains("poisoning_type", Constants.TagTypes.STRING) || potStack.getTag().getString("poisoning_type").isEmpty()) return fromConstructor;
            return new TranslationTextComponent("poisoning_type." + poisoningType.getNamespace() + "." + poisoningType.getPath());
        } else {
            return fromConstructor;
        }
    }

    public void executeFunctionality(ItemStack containerStack, ItemStack potStack, World world, LivingEntity livEntity) {
        PoisoningType type = getTypeFromNBT(potStack);
//        this.containerItem = new ItemStack(VSItems.STYLISED_POT.get());
        if (hasEffectsInNBT(potStack)) {
            NBTUtils.addEffectsFromNBT(potStack.getOrCreateTagElement("effect"), world, livEntity, this.poisoningType.getPoisoningEffect().get());
        } else if (type != null && type.getPoisoningEffect() != null) {
            if (!world.isClientSide) {
                livEntity.addEffect(new EffectInstance(this.poisoningType.getPoisoningEffect().get(), this.poisoningDuration));
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack potStack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.appendHoverText(potStack, world, tooltip, flag);
        PoisoningType type = getTypeFromNBT(potStack);

        if (this.compatMod != null && NBTUtils.shouldNotHideTooltip("hide_compat_mod", potStack)) {
            tooltip.add(new TranslationTextComponent("tooltip.variants.compat_item_from", this.compatMod).withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
        }
        if (hasEffectsInNBT(potStack)) {
            CompoundNBT effectTag = potStack.getOrCreateTagElement("effect");
            Effect effect = ForgeRegistries.POTIONS.getValue(ResourceLocation.tryParse(effectTag.getString("id")));
            if (effect != null) {
                tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".stylised_pot.poisoning_or_effect", new TranslationTextComponent(Util.makeDescriptionId("effect", effect.getRegistryName())).withStyle(VSStyles.getFromRGB(
                        effect.getColor()))).withStyle(TextFormatting.GRAY));
            }
        }
        if (type != null && type.getPoisoningEffect() != null) {
            tooltip.add(new TranslationTextComponent("tooltip." + Variants.MOD_ID + ".stylised_pot.poisoning", getPoisoningTranslation(potStack).withStyle(VSStyles.getFromRGB(type.getPoisoningEffect().get().getColor()))).withStyle(
                    TextFormatting.GRAY));
        }
    }

    @Override
    public void fillItemCategory(ItemGroup tab, NonNullList<ItemStack> list) {
        if (this.allowdedIn(tab)) {
            ItemStack potStack = new ItemStack(this);
            PoisoningType type = getTypeFromNBT(potStack);
            CompoundNBT tag = potStack.getOrCreateTag();
            if (potStack.getTag() != null && type != null) tag.putString("poisoning_type", type.getTypeRegistry().getRegistryName().toString());
            if (potStack.getTag() != null) {
                CompoundNBT effectTag = potStack.getOrCreateTagElement("effect");
                effectTag.putInt("duration", this.poisoningDuration);
                tag.put("effect", effectTag);
            }
            list.add(potStack);
        }
    }*/
}
