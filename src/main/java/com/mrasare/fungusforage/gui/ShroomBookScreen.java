package com.mrasare.fungusforage.gui;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.data.ResearchStorage;
import com.mrasare.fungusforage.setup.ClientSetup;
import com.mrasare.fungusforage.setup.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import org.codehaus.plexus.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public class ShroomBookScreen extends Screen {

    private final ResourceLocation backPage = new ResourceLocation(FungusForage.MODID,"textures/gui/background.png");
    private  int BGWidth;
    private  int BGHeight;
    private final Minecraft instance;
    private  String displayString;
    private Research.Shrooms shroom;
    private int stringGap = 12;

    public ShroomBookScreen(ITextComponent component) {
        super(component);
        instance = Minecraft.getInstance();

    }

    @Override
    protected void init() {
        super.init();

        BGWidth = instance.currentScreen.width-100;
        BGHeight =instance.currentScreen.height-50;

        addButton(new TexturedButton((Math.abs(instance.currentScreen.width-BGWidth))/2 + 16,(Math.abs(instance.currentScreen.height-BGHeight))/2 +16,16,16,new StringTextComponent(" "), p_onPress_1_ -> {
            ClientSetup.showBookScreen();
        },"homebutton.png"));

    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {

        Minecraft.getInstance().getTextureManager().bindTexture(backPage);
        blit(matrixStack,(Math.abs(instance.currentScreen.width-BGWidth))/2,(Math.abs(instance.currentScreen.height-BGHeight))/2,0,0,BGWidth,BGHeight,
                BGWidth,BGHeight);

        if(displayString!=null){
            ArrayList<String> arrayList = new ArrayList<>();


            Arrays.stream(displayString.split("\\|")).forEachOrdered(line->{
                if(font.getStringWidth(line)>200){
                    Iterator<String> iterator = Arrays.stream(line.split(" ")).iterator();
                    StringBuilder stringBuilder  = new StringBuilder();

                    while(iterator.hasNext()) {

                        while (font.getStringWidth(stringBuilder.toString()) < 200) {
                            if (!iterator.hasNext())
                                break;

                            stringBuilder.append(iterator.next()).append(" ");
                        }
                        arrayList.add(stringBuilder.toString());
                        stringBuilder.delete(0,stringBuilder.length());
                    }
                }else{
                    arrayList.add(line);
                }
            });


            AtomicInteger count = new AtomicInteger();
            arrayList.forEach(item->drawString(matrixStack,font,item,100, 40+ stringGap* (count.incrementAndGet()),0xffffff));

            count.incrementAndGet();
            count.incrementAndGet();

            Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(research -> {
                drawString(matrixStack,font, "§d§l§nBest Stats Discovered",100,40+ stringGap* (count.incrementAndGet()),0xffffff);
//                Registration.SHROOM_LIST.get(shroom).forEach(integerProperty -> {
//                    drawString(matrixStack,font, "§3"+StringUtils.capitalise(integerProperty.getName()) + " : " + "§a"+research.getPropertyValue(shroom,integerProperty.getName()),100,40+ stringGap* (count.incrementAndGet()),0xffffff);
//
//                });
            });




        }







        super.render(matrixStack,mouseX,mouseY,partialTicks);
    }


    public Screen addDesc(Research.Shrooms shroom){
        this.shroom = shroom;
        displayString = I18n.format("desc.fungusforage."+shroom.getName());
        return this;
    }


}
