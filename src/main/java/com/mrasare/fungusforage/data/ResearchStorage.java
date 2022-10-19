package com.mrasare.fungusforage.data;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

import javax.annotation.Nullable;

public class ResearchStorage {

    @CapabilityInject(IResearch.class)
    public static Capability<IResearch> RESEARCH_CAPABILITY = null;


    public static void register() {
        CapabilityManager.INSTANCE.register(IResearch.class,
                new ResearchStorage.Storage(), Research::new);
    }

    public static class Storage implements Capability.IStorage<IResearch> {

        @Nullable
        @Override
        public CompoundNBT writeNBT(Capability<IResearch> capability, IResearch instance, Direction side) {
            CompoundNBT nbt = new CompoundNBT();

            instance.getBoolList().keySet().forEach(key -> {
                CompoundNBT topicNBT = new CompoundNBT();

                topicNBT.putBoolean("isDiscovered",instance.isDiscovered(key));



//                Registration.SHROOM_LIST.get(key).forEach(property -> {
//                    topicNBT.putInt(property.getName(),instance.getPropertyValue(key,property.getName()));
//                });

                nbt.put(key.getName(),topicNBT);


            });

            return nbt;
        }

        @Override
        public void readNBT(Capability<IResearch> capability, IResearch instance, Direction side, INBT nbt) {
            CompoundNBT list = ((CompoundNBT) nbt);
            instance.getBoolList().keySet().forEach(key -> {
                CompoundNBT topicNBT = list.getCompound(key.getName());
                instance.setShroomState(key,(topicNBT.getBoolean("isDiscovered")));
//                Registration.SHROOM_LIST.get(key).forEach(property -> instance.setPropertyValue(key,property.getName(),topicNBT.getInt(property.getName())));
            });
        }
    }
}
