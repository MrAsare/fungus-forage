package com.mrasare.fungusforage.block.mushrooms;

import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.setup.ClientSetup;
import com.mrasare.fungusforage.setup.Registration;
import com.mrasare.fungusforage.util.MushroomEffect;
import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;


public abstract class AbstractShroom extends BushBlock {

    public Research.Shrooms shroom;
    public static final DirectionProperty FACING = BlockStateProperties.FACING;

    public static final HashMap<String, ArrayList<MushroomEffect>> EFFECT_MAP = new HashMap<>();
    protected   ArrayList<MushroomEffect> effectsList = new ArrayList<>();

    public AbstractShroom(Properties properties) {
        super(properties.hardnessAndResistance(10f).setRequiresTool().sound(SoundType.SLIME).doesNotBlockMovement());
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING,Direction.NORTH));
    }


    @Nullable
    @Override
    public final BlockState getStateForPlacement(BlockItemUseContext context) {
        if(!context.getWorld().isRemote) {
            AtomicReference<BlockState> context2 = new AtomicReference<>(super.getStateForPlacement(context));
            shroom.getPropertiesList().forEach(integerProperty -> {
                Collection<Integer> collection = integerProperty.getAllowedValues();
                context2.set(context2.get().with(integerProperty, RANDOM.nextInt(Collections.max(collection) - Collections.min(collection)) + Collections.min(collection) + 1));
            });
            return context2.get().with(FACING, context.getPlacementHorizontalFacing());

        }
        return super.getStateForPlacement(context).with(FACING,context.getPlacementHorizontalFacing());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if(!worldIn.isRemote){
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT,()->()->ClientSetup.updateBool(shroom, !placer.isCrouching()));
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BlockStateProperties.FACING);


    }

    public abstract Item.Properties getItemProperties();


}
