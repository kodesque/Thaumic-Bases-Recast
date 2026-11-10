package tbases.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.IPlantable;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.client.fx.FXDispatcher;

import java.util.Random;

public class BlockAshroom extends BlockBush implements IGrowable {
    public int growthStages;
    public int growthDelay;
    public boolean requiresFarmland;
    public PropertyInteger AGE;
    public ItemStack dropItem;

    public BlockAshroom(int stages, int delay, boolean isCrop) {
        this.growthStages = stages;
        this.growthDelay = delay;
        this.requiresFarmland = isCrop;
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }

    public int getGrowthStages() {
        return this.growthStages;
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return true;
    }

    protected BlockStateContainer createBlockState() {
        if (this.AGE == null) {
            this.AGE = PropertyInteger.create("age", 0, 3);
        }

        return new BlockStateContainer(this, new IProperty[]{this.AGE});
    }

    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(this.AGE);
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(this.AGE, Math.min(this.growthStages, meta));
    }

    public void updateTick(World w, BlockPos pos, IBlockState state, Random rnd) {
        super.updateTick(w, pos, state, rnd);
        if (w.isAreaLoaded(pos, 1)) {
            if (w.getLightFromNeighbors(pos.up()) >= 9) {
                int i = (Integer)state.getValue(this.AGE);
                if (i < this.getGrowthStages()) {
                    float f = getGrowthChance(this, w, pos);
                    if (ForgeHooks.onCropsGrowPre(w, pos, state, rnd.nextInt((int)(25.0F / f) + 1) == 0)) {
                        w.setBlockState(pos, state.withProperty(this.AGE, i + 1));
                        ForgeHooks.onCropsGrowPost(w, pos, state, w.getBlockState(pos));
                    }
                }
            }

        }
    }

    protected static float getGrowthChance(Block blockIn, World worldIn, BlockPos pos) {
        float f = 1.0F;
        BlockPos blockpos = pos.down();

        for(int i = -1; i <= 1; ++i) {
            for(int j = -1; j <= 1; ++j) {
                float f1 = 0.0F;
                IBlockState iblockstate = worldIn.getBlockState(blockpos.add(i, 0, j));
                if (iblockstate.getBlock().canSustainPlant(iblockstate, worldIn, blockpos.add(i, 0, j), EnumFacing.UP, (IPlantable)blockIn)) {
                    f1 = 1.0F;
                    if (iblockstate.getBlock().isFertile(worldIn, blockpos.add(i, 0, j))) {
                        f1 = 3.0F;
                    }
                }

                if (i != 0 || j != 0) {
                    f1 /= 4.0F;
                }

                f += f1;
            }
        }

        BlockPos blockpos1 = pos.north();
        BlockPos blockpos2 = pos.south();
        BlockPos blockpos3 = pos.west();
        BlockPos blockpos4 = pos.east();
        boolean flag = blockIn == worldIn.getBlockState(blockpos3).getBlock() || blockIn == worldIn.getBlockState(blockpos4).getBlock();
        boolean flag1 = blockIn == worldIn.getBlockState(blockpos1).getBlock() || blockIn == worldIn.getBlockState(blockpos2).getBlock();
        if (flag && flag1) {
            f /= 2.0F;
        } else {
            boolean flag2 = blockIn == worldIn.getBlockState(blockpos3.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.north()).getBlock() || blockIn == worldIn.getBlockState(blockpos4.south()).getBlock() || blockIn == worldIn.getBlockState(blockpos3.south()).getBlock();
            if (flag2) {
                f /= 2.0F;
            }
        }

        return f;
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return (Integer)state.getValue(this.AGE) != 3;
    }

    protected boolean canSustainBush(IBlockState state) {
        return true;
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
    }

    protected int getBonemealAgeIncrease(World worldIn) {
        return MathHelper.getInt(worldIn.rand, 1, 2);
    }

    public int getMaxAge() {
        return this.growthStages;
    }

    protected int getAge(IBlockState state) {
        return (Integer)state.getValue(this.getAgeProperty());
    }

    protected PropertyInteger getAgeProperty() {
        return this.AGE;
    }

    public IBlockState withAge(int age) {
        return this.getDefaultState().withProperty(this.getAgeProperty(), age);
    }

    public void onPlayerDestroy(World world, BlockPos pos, IBlockState state) {
        if ((Integer)state.getValue(this.AGE) >= 3) {
            ThaumcraftApi.internalMethods.addVis(world, pos, (float)(5 + world.rand.nextInt(10)));
        }

        if (world.isRemote) {
            FXDispatcher.INSTANCE.burst((double)pos.getX() + (double)0.5F, (double)pos.getY() + (double)0.5F, (double)pos.getZ() + (double)0.5F, 1.0F);
        }

    }

    public EnumPlantType getPlantType(IBlockAccess world, BlockPos pos) {
        return EnumPlantType.Plains;
    }
}
