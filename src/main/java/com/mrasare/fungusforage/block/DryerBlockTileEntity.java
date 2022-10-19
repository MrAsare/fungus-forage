package com.mrasare.fungusforage.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.item.MushroomItem;
import com.mrasare.fungusforage.setup.init.ItemInit;
import com.mrasare.fungusforage.setup.init.TileEntityInit;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DryerBlockTileEntity extends TileEntity implements ITickableTileEntity {

    public static final int SLOTS = 1;
    public int timer = 0;
    public final int CONVERT_TIME = 200;
    private ItemStackHandler handler;

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOTS,ItemStack.EMPTY);

    public DryerBlockTileEntity(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    public DryerBlockTileEntity(){
        this(TileEntityInit.DRYER_TILE_ENTITY.get());
    }


    @Override
    public void tick() {
        if(!world.isRemote && getItem()!=ItemStack.EMPTY){
            timer++;

            if(timer==CONVERT_TIME){
                setItem(new ItemStack(ItemInit.MUSHROOM_SAMPLE.get(), getItem().getCount()));
                timer =0;
            }
        }
    }


    public ItemStack getItem(){
        return getHandler().getStackInSlot(0);
    }
    public void setItem(ItemStack stack){
        getHandler().setStackInSlot(0,stack);
    }


    private ItemStackHandler getHandler(){
        if(handler==null){
            handler = new ItemStackHandler(1){

                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    return stack.getItem() instanceof MushroomItem;
                }


                @Nonnull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    if(getItem().getItem() instanceof MushroomItem)
                        return ItemStack.EMPTY;

                    return super.extractItem(slot,amount,simulate);
                }

                @Override
                protected void onContentsChanged(int slot) {
                    markDirty();
                    world.notifyBlockUpdate(getPos(), getBlockState(), getBlockState(), 3);
                    timer =0;
                }
            };
        }
        return handler;
    }

    public SUpdateTileEntityPacket getUpdatePacket()
    {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        int tileEntityType = 0;
        return new SUpdateTileEntityPacket(this.pos, tileEntityType, nbtTagCompound);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        BlockState blockState = world.getBlockState(pos);
        read(blockState, pkt.getNbtCompound());   // read from the nbt in the packet
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        CompoundNBT nbtTagCompound = new CompoundNBT();
        write(nbtTagCompound);
        return nbtTagCompound;
    }

    @Override
    public void handleUpdateTag(BlockState blockState, CompoundNBT parentNBTTagCompound)
    {
        this.read(blockState, parentNBTTagCompound);
    }

    @Override
    public void read(BlockState state, CompoundNBT tag) {
        CompoundNBT invTag = tag.getCompound("inv");
        getHandler().deserializeNBT(invTag);
        super.read(state, tag);
    }



    @Override
    public CompoundNBT write(CompoundNBT tag) {
        CompoundNBT compound = getHandler().serializeNBT();
        tag.put("inv",compound);
        return super.write(tag);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(()-> (T)getHandler());
        }
        return super.getCapability(cap,side);
    }




}
