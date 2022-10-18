package com.mrasare.fungusforage.gui;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.block.mushrooms.AbstractShroom;
import com.mrasare.fungusforage.data.ResearchStorage;
import com.mrasare.fungusforage.setup.Registration;
import com.mrasare.fungusforage.util.Utils;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.lwjgl.opengl.GL11;

@Mod.EventBusSubscriber(modid = FungusForage.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class GuiClientEventHandler {



    @SubscribeEvent
    public static void init(final FMLClientSetupEvent event) {
    }


    @SubscribeEvent
    public static void drawText(final RenderGameOverlayEvent.Text event) {


        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

            Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch -> {

                RayTraceResult block = Minecraft.getInstance().player.pick(5.0D, 0.0F, false);

                if (Minecraft.getInstance().player.getHeldItemMainhand().getItem() == Registration.MAGNIFYING_GLASS.get() &&
                        block.getType() == RayTraceResult.Type.BLOCK) {

                    BlockState blockStateLookedAt = Minecraft.getInstance().world.getBlockState(new BlockPos(block.getHitVec()));

                    if (blockStateLookedAt.getBlock() instanceof AbstractShroom) {
                        AbstractShroom shroom = (AbstractShroom) blockStateLookedAt.getBlock();
                        StringTextComponent string = new StringTextComponent((iResearch.isDiscovered(shroom.shroom)? "§l§n" + I18n.format("block.fungusforage." + shroom.shroom.getName()):"§l§n???")+ "\n \n");

                        if (Registration.SHROOM_LIST.get(shroom) != null) {


//                            Registration.SHROOM_LIST.get(shroom).forEach(tProperty -> {
//                                string.appendString(tProperty.getName() + " : "+((blockStateLookedAt.get(tProperty)>iResearch.getPropertyValue(shroom.shroom,tProperty.getName()))?"§a":"§c") +  blockStateLookedAt.get(tProperty) +"§1" +
//                                        ((Utils.isLargestValue(tProperty, blockStateLookedAt.get(tProperty)) ? " (MAX)" : "")+ "\n"));
//                            });


                        }
                        Utils.drawCompoundString(Minecraft.getInstance().fontRenderer,
                                event.getMatrixStack(), string.getString(),
                                "\n", 0,0, 0x0000ff);
                    }
                }

            });


        }
    }
    @SubscribeEvent
    public static void highlightBlock(final RenderGameOverlayEvent.Post event) {

        if (event.getType() == RenderGameOverlayEvent.ElementType.TEXT) {

            Minecraft.getInstance().player.getCapability(ResearchStorage.RESEARCH_CAPABILITY).ifPresent(iResearch -> {


                RayTraceResult block = Minecraft.getInstance().player.pick(5.0D, 0.0F, false);

                if (Minecraft.getInstance().player.getHeldItemMainhand().getItem() == Registration.MAGNIFYING_GLASS.get() &&
                        block.getType() == RayTraceResult.Type.BLOCK) {

                    BlockState blockStateLookedAt = Minecraft.getInstance().world.getBlockState(new BlockPos(block.getHitVec()));

                    if (blockStateLookedAt.getBlock() instanceof AbstractShroom) {
                        GL11.glPushMatrix();
                        GL11.glScalef(4f, 4f, 4f);
                        GL11.glTranslatef(40f, 2, 100);
                        GL11.glRotatef((System.currentTimeMillis() / 15) % 360, 0, 1, 0);
                        GL11.glTranslatef(-40f, 2, -100);
                        Minecraft.getInstance().getItemRenderer().renderItemIntoGUI(new ItemStack(blockStateLookedAt.getBlock().asItem()),32,1);
                        GL11.glPopMatrix();


                    }
                }

            });
        }
    }


}
