package com.mrasare.fungusforage.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.setup.ClientSetup;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;

import java.util.ArrayList;
import java.util.Arrays;

public class ShroomSelect extends ShroomBookScreen {

    private final ResourceLocation backPage = new ResourceLocation(FungusForage.MODID,"textures/gui/background.png");
    private  int BGWidth;
    private  int BGHeight;
    private final Minecraft instance;
    private final int mushroomIconSize = 16;
    private int counter =0;
    private ArrayList<Widget> buttonList = new ArrayList<>();


    public ShroomSelect() {
        super(new StringTextComponent("ShroomSelect"));
        instance = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        Arrays.stream(Research.Mushrooms.values()).forEachOrdered(shroom ->
        {
            addButton(new ShroomButton(100 + counter*(mushroomIconSize*2),75,mushroomIconSize, p_onPress_1_ -> {
                ClientSetup.showBookScreen(shroom);
            }, shroom,this));
            counter++;
        });

        buttonList.addAll(this.buttons);
        counter =0;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }

}
