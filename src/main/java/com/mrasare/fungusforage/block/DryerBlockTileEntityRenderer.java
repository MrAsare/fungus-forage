package com.mrasare.fungusforage.block;


import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.util.math.vector.Vector3f;


public  class DryerBlockTileEntityRenderer extends TileEntityRenderer<DryerBlockTileEntity> {
    public DryerBlockTileEntityRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(DryerBlockTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
            matrixStackIn.push();
            matrixStackIn.translate(0.5d,0.3d,0.5d);
            matrixStackIn.scale(0.7f,0.7f,0.7f);
            matrixStackIn.rotate(tileEntityIn.getBlockState().get(DryerBlock.FACING).getOpposite().getRotation());
            IBakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelWithOverrides(tileEntityIn.getItem(),null,null);
            Minecraft.getInstance().getItemRenderer().renderItem(tileEntityIn.getItem(), ItemCameraTransforms.TransformType.GROUND,true,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn,model);
            matrixStackIn.pop();
    }


}