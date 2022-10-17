package com.mrasare.fungusforage.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ResearchProvider implements ICapabilitySerializable<CompoundNBT> , ICapabilityProvider {

    private final Research charge = new Research();
    private final LazyOptional<IResearch> chargeOptional = LazyOptional.of(() -> charge);





    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return chargeOptional.cast();
    }

    @Override
    public CompoundNBT serializeNBT() {
        if (chargeOptional== null) {
            throw new RuntimeException("ChargeOptional was null");
        } else {
            return (CompoundNBT) ResearchStorage.RESEARCH_CAPABILITY.writeNBT(charge,null);
        }

    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        if (ResearchStorage.RESEARCH_CAPABILITY != null) {
            ResearchStorage.RESEARCH_CAPABILITY.readNBT(charge, null, nbt);
        }
    }
    public void invalidate() {
        chargeOptional.invalidate();
    }



}
