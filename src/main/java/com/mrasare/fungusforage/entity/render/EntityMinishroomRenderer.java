package com.mrasare.fungusforage.entity.render;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.entity.custom.EntityMinishroom;
import com.mrasare.fungusforage.entity.model.EntityMinishroomModel;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;


public class EntityMinishroomRenderer extends MobRenderer<EntityMinishroom,EntityMinishroomModel<EntityMinishroom>>
{
    protected static final ResourceLocation TEXTURE =
            new ResourceLocation(FungusForage.MODID, "textures/entity/minishroom.png");

    public EntityMinishroomRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new EntityMinishroomModel<>(), 0.7F);
    }

    @Override
    public ResourceLocation getEntityTexture(EntityMinishroom entity) {
        return TEXTURE;
    }
}