package com.mrasare.fungusforage.entity.custom;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.util.MushroomEffect;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class EntityMinishroom extends CreatureEntity {

    public EntityMinishroom(EntityType<? extends CreatureEntity> type, World worldIn) {
        super(type, worldIn);
    }

    public static AttributeModifierMap.MutableAttribute setCustomAttributes() {
        return MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
                .createMutableAttribute(Attributes.ATTACK_DAMAGE, 2.0D)
                .createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D)
                .createMutableAttribute(Attributes.ARMOR,2.0D);
    }


    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0D, true));
        this.targetSelector.addGoal( 1, (new HurtByTargetGoal(this)));
    }


    @Override
    public void setAttackingPlayer(@Nullable PlayerEntity p_230246_1_) {
        super.setAttackingPlayer(p_230246_1_);
    }

    @Override
    protected int getExperiencePoints(PlayerEntity player)
    {
        return 3 + this.world.rand.nextInt(5);
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundEvents.ENTITY_HOGLIN_AMBIENT;
    }


    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundEvents.ENTITY_HOGLIN_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return SoundEvents.ENTITY_IRON_GOLEM_HURT;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn)
    {
        this.playSound(SoundEvents.ENTITY_HOGLIN_STEP, 0.20F, 0.5F);
    }

    @Override
    public boolean attackEntityAsMob(Entity entityIn) {
        if (!super.attackEntityAsMob(entityIn)) {
            return false;
        } else {
            if (entityIn instanceof LivingEntity) {

            }
            return true;
        }
    }

}
