package com.mrasare.fungusforage.block.mushroom;

import com.mrasare.fungusforage.FungusForage;
import com.mrasare.fungusforage.data.Research;
import com.mrasare.fungusforage.entity.custom.EntityMinishroom;
import com.mrasare.fungusforage.setup.ClientSetup;
import com.mrasare.fungusforage.setup.init.EntityInit;
import com.mrasare.fungusforage.util.MushroomEffect;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BushBlock;
import net.minecraft.block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Food;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;


public abstract class AbstractShroom extends BushBlock {

    public Research.Mushrooms mushroom;

    public static final DirectionProperty FACING = BlockStateProperties.FACING;


    public static final HashMap<String, ArrayList<MushroomEffect>> EFFECT_MAP = new HashMap<>();
    protected final ArrayList<MushroomEffect> effectsList = new ArrayList<>();
    //    public  ArrayList<IntegerProperty> mushroomProperties = new ArrayList<>();
    private final VoxelShape SHAPE = VoxelShapes.create(0.2, 0, 0.2, 0.8, 0.7, 0.8);

    public AbstractShroom(Properties properties, Research.Mushrooms mushroom) {
        super(properties.hardnessAndResistance(10f).setRequiresTool().sound(SoundType.SLIME).doesNotBlockMovement());
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
        this.mushroom = mushroom;
    }


    @Nullable
    @Override
    public final BlockState getStateForPlacement(BlockItemUseContext context) {
//        if(!context.getWorld().isRemote) {
//            AtomicReference<BlockState> context2 = new AtomicReference<>(super.getStateForPlacement(context));
//            getMushroomProperties().forEach(integerProperty -> {
//                Collection<Integer> collection = integerProperty.getAllowedValues();
//                context2.set(context2.get().with(integerProperty, RANDOM.nextInt(Collections.max(collection) - Collections.min(collection)) + Collections.min(collection) + 1));
//            });
//            return context2.get().with(FACING, context.getPlacementHorizontalFacing());

//        }
        return super.getStateForPlacement(context).with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        if (!worldIn.isRemote) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientSetup.updateBool(mushroom, !placer.isCrouching()));
        }
        super.onBlockPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    public Food.Builder applyMushroomEffects(Research.Mushrooms shroom) {
        Food.Builder builder = new Food.Builder();
        builder.setAlwaysEdible();
        EFFECT_MAP.get(shroom.getName()).forEach(mushroomEffect -> {
            builder.effect(() -> new EffectInstance(mushroomEffect.getEffect(), mushroomEffect.getDuration(), mushroomEffect.getAmplifier()), 1f);
        });
        return builder;
    }

    public abstract Food.Builder getFoodValues(Food.Builder builder);

    public CompoundNBT getEffects() {
        CompoundNBT nbt = new CompoundNBT();

        EFFECT_MAP.get(mushroom.getName()).forEach(mushroomEffect -> {
            CompoundNBT stats = new CompoundNBT();
            stats.putShort("duration", (short) mushroomEffect.getDuration());
            stats.putShort("amplifier", (short) mushroomEffect.getAmplifier());
            nbt.put(String.valueOf(Effect.getId(mushroomEffect.getEffect())),stats);

        });

        return nbt;
    }
}
