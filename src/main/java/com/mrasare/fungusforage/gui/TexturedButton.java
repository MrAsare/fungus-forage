package com.mrasare.fungusforage.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrasare.fungusforage.FungusForage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;


public class TexturedButton extends Button{
    private ResourceLocation resourceLocation;
    private ResourceLocation highlighter;
    public TexturedButton(int x, int y, int width, int height, ITextComponent title, Button.IPressable pressedAction,String textureName){
        super(x,y,width,height,title,pressedAction);

        resourceLocation= new ResourceLocation(FungusForage.MODID,"textures/gui/"+textureName);
        highlighter = new ResourceLocation(FungusForage.MODID,"textures/gui/highlight.png");
    }





    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        Minecraft.getInstance().getTextureManager().bindTexture(resourceLocation);
        blit(matrixStack, x, y, 0, 0, width, height, width, height);


        if(mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.width && mouseY < this.y + this.height){
                Minecraft.getInstance().getTextureManager().bindTexture(highlighter);
                blit(matrixStack, x, y, 0, 0,width,height,width,height);
            }




    }
}
