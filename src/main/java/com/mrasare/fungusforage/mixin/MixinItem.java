package com.mrasare.fungusforage.mixin;

import net.minecraft.client.renderer.ItemRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ItemRenderer.class)
@OnlyIn(Dist.CLIENT)
public abstract class MixinItem {


//    @Inject(at = @At(value="TAIL"),method ="Lnet/minecraft/client/renderer/entity/ItemRenderer;render(Lnet/minecraft/entity/item/ItemEntity;FFLcom/mojang/blaze3d/matrix/MatrixStack;Lnet/minecraft/client/renderer/IRenderTypeBuffer;I)V", cancellable = true)
//    protected void skrt(){
//
//    }

/*    @Shadow public abstract Item getItem();

    @Inject(at = @At(value="HEAD"),method ="Lnet/minecraft/item/ItemStack;getDisplayName()Lnet/minecraft/util/text/ITextComponent;", cancellable = true)
    protected void skrt(CallbackInfoReturnable<ITextComponent> cir) {
        ClientPlayerEntity client;
        ItemStack highlightedItem;

        if((client =Minecraft.getInstance().player)!=null){
            highlightedItem = ((ItemStack)(Object)this);
            Block block = Block.getBlockFromItem(highlightedItem.getItem());

            if(block instanceof AbstractShroom){
                AbstractShroom shroom = ((AbstractShroom) block);

                client.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(research->{
                    if(!research.isDiscovered(shroom.name)) {
                        cir.setReturnValue(new StringTextComponent("Unidentified Mushroom"));
                    }
                });
            }

        }
    }*/



}


