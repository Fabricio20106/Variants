package com.junethewoods.variants.mixin.tutorial;

//@Mixin(FindTreeStep.class)
public class VSFindTreeStepMixin {
    /*@Unique
    private static final ITextComponent TITLE = new TranslationTextComponent("tutorial.find_tree.title");
    @Unique
    private static final ITextComponent DESCRIPTION = new TranslationTextComponent("tutorial.find_tree.description");
    @Unique
    private final Tutorial tutorial;
    @Unique
    private TutorialToast toast;
    @Unique
    private int timeWaiting;

    public VSFindTreeStepMixin(Tutorial tutorial) {
        this.tutorial = tutorial;
    }

    @Inject(method = "tick", at = @At("HEAD"))
    public void tick(CallbackInfo ci) {
        ++this.timeWaiting;
        if (this.tutorial.getGameMode() != GameType.SURVIVAL) {
            this.tutorial.setStep(TutorialSteps.NONE);
        } else {
            if (this.timeWaiting == 1) {
                ClientPlayerEntity clientPlayer = this.tutorial.getMinecraft().player;
                if (clientPlayer != null) {
                    for (Block block : ForgeRegistries.BLOCKS) {
                        if (completesFindTreeTutorial()) {
                            if (clientPlayer.inventory.contains(new ItemStack(block))) {
                                this.tutorial.setStep(TutorialSteps.CRAFT_PLANKS);
                                return;
                            }
                        }
                    }

                    if (FindTreeStep.hasPunchedTreesPreviously(clientPlayer)) {
                        this.tutorial.setStep(TutorialSteps.CRAFT_PLANKS);
                        return;
                    }
                }
            }

            if (this.timeWaiting >= 6000 && this.toast == null) {
                this.toast = new TutorialToast(TutorialToast.Icons.TREE, TITLE, DESCRIPTION, false);
                this.tutorial.getMinecraft().getToasts().addToast(this.toast);
            }
        }
    }

    @Inject(method = "onLookAt", at = @At("HEAD"))
    public void onLookAt(ClientWorld world, RayTraceResult hitResult, CallbackInfo ci) {
        if (hitResult.getType() == RayTraceResult.Type.BLOCK) {
            BlockState state = world.getBlockState(((BlockRayTraceResult) hitResult).getBlockPos());
            if (state.is(VSTags.Blocks.COMPLETES_FIND_TREE_TUTORIAL)) {
                this.tutorial.setStep(TutorialSteps.PUNCH_TREE);
            }
        }
    }

    @Inject(method = "onGetItem", at = @At("HEAD"))
    public void onGetItem(ItemStack stack, CallbackInfo ci) {
        if (stack.getItem().is(VSTags.Items.COMPLETES_FIND_TREE_TUTORIAL)) {
            this.tutorial.setStep(TutorialSteps.CRAFT_PLANKS);
        }
    }

    @Inject(method = "hasPunchedTreesPreviously", at = @At("HEAD"), cancellable = true)
    private static void hasPunchedTreesPreviouslyA(ClientPlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        for(Block block : ForgeRegistries.BLOCKS) {
            if (completesFindTreeTutorial()) {
                if (player.getStats().getValue(Stats.BLOCK_MINED.get(block)) > 0) {
                    cir.setReturnValue(true);
                }
            }
        }
        cir.setReturnValue(false);
    }

    @Unique
    private static boolean completesFindTreeTutorial() {
        return ForgeRegistries.BLOCKS.getValues().stream().anyMatch((block1 -> block1.is(VSTags.Blocks.COMPLETES_FIND_TREE_TUTORIAL)));
    }*/
}
