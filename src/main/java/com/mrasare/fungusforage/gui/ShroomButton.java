package com.mrasare.fungusforage.gui;


import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.data.IResearch;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.data.ResearchStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.util.LazyOptional;

public class ShroomButton extends Button {

    private final Research.Mushrooms shroom;
    private int uOffset =0;
    private final int SIZE;
    private final ResourceLocation textureLocation;
    private final ResourceLocation undiscoveredTextureLocation;
    private final ResourceLocation highlighter;
    private final LazyOptional<IResearch> capability;
    private ITextComponent shroomName;
    private final ITextComponent fullName;
    private final ITextComponent unknown;
    private final ShroomSelect screen;



    public ShroomButton(int x, int y, int size, IPressable pressedAction, Research.Mushrooms shroom, ShroomSelect screen) {
        super(x, y, size, size, new StringTextComponent("soo"), pressedAction);
        this.SIZE = size;
        this.shroom = shroom;
        this.textureLocation = new ResourceLocation(FungusForage.MODID,"textures/item/" + shroom.getName() +".png");
        this.undiscoveredTextureLocation = new ResourceLocation(FungusForage.MODID,"textures/gui/undiscovered_shroom.png");
        this.highlighter = new ResourceLocation(FungusForage.MODID,"textures/gui/highlight.png");
        this.capability = Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY);
        this.fullName = ITextComponent.getTextComponentOrEmpty(I18n.format("block.fungusforage." + shroom.getName()));
        this.unknown =ITextComponent.getTextComponentOrEmpty("???");
        this.screen = screen;

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        this.capability.ifPresent(iResearch -> {
            this.shroomName =   iResearch.isDiscovered(this.shroom)? this.fullName:this.unknown;
            Minecraft.getInstance().getTextureManager().bindTexture(iResearch.isDiscovered(this.shroom)? this.textureLocation:this.undiscoveredTextureLocation);
        });



        this.isHovered = mouseX >= this.x && mouseY >= this.y && mouseX < this.x + this.SIZE && mouseY < this.y + this.SIZE;
        blit(matrixStack, x, y, 0, 0, this.SIZE,this.SIZE, this.SIZE, this.SIZE);

        if(isHovered){
            Minecraft.getInstance().getTextureManager().bindTexture(highlighter);
            blit(matrixStack, x, y, 0, 0, this.SIZE,this.SIZE, this.SIZE, this.SIZE);
        }


        if(isHovered){
            drawCenteredString(matrixStack,Minecraft.getInstance().fontRenderer, this.shroomName,this.x+this.width/2,this.y-this.height,0x00ffff);
        }



    }



}
