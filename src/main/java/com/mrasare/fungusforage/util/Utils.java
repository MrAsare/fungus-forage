package com.mrasare.fungusforage.util;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrasare.fungusforage.FungusForage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

public class Utils {
    private static ResourceLocation bg = new ResourceLocation(FungusForage.MODID,"textures/gui/bg.png");

    public static void drawCompoundString(FontRenderer fontRenderer, MatrixStack matrixStack, String text, String separator, int xPos, int yPos, int color){



        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(text.split(separator)));
        Minecraft.getInstance().getTextureManager().bindTexture(bg);

//        AtomicInteger x = new AtomicInteger();
//        arrayList.forEach(s -> {
//            x.set(Math.max(x.get(),fontRenderer.getStringWidth(s)));
//        });
//
//        int stringWidth = x.get();
        int offset = 16;
        GL11.glPushMatrix();
        GL11.glPushAttrib(GL11.GL_ENABLE_BIT);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        Screen.blit(matrixStack,xPos,yPos, 0, 0,
                Minecraft.getInstance().getMainWindow().getScaledWidth()*4/9,
                16*arrayList.size() + 4*offset ,Minecraft.getInstance().getMainWindow().getScaledWidth()*4/9, 16*arrayList.size() + 4*offset);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
        AtomicInteger count = new AtomicInteger();
        arrayList.forEach(item->fontRenderer.drawString(matrixStack, org.codehaus.plexus.util.StringUtils.capitalise(item),xPos + offset*1.5f,yPos+offset + 16* (count.getAndIncrement()),color));


    }
//
//    public static boolean isLargestValue(IntegerProperty property,int val){
//        return(property.getAllowedValues().stream().noneMatch(integer ->integer>val));
//    }
//
//    public static int getMaxValFromProperty(IntegerProperty prop){
//        return Collections.max(prop.getAllowedValues());
//    }


}
