package tbases.common.blocks.plants;

import net.minecraft.block.BlockCrops;
import net.minecraft.block.IGrowable;
import net.minecraft.block.SoundType;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import tbases.init.TBItems;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockTobacco extends BlockCrops implements IGrowable {
    public int growthStages;
    public int growthDelay;
    public boolean requiresFarmland;
    public PropertyInteger AGE;
    public ItemStack dropItem;
    public ItemStack dropSeed;

    public BlockTobacco(int stages, int delay, boolean isCrop) {
        this.growthStages = stages;
        this.growthDelay = delay;
        this.requiresFarmland = isCrop;
        this.setTickRandomly(true);
        this.setHardness(0.0F);
        this.setSoundType(SoundType.PLANT);
        this.disableStats();
    }

    protected Item getSeed() {
        return TBItems.tobaccoseed;
    }

    protected Item getCrop() {
        return TBItems.tobacco_leaves;
    }

    protected BlockStateContainer createBlockState() {
        if (this.AGE == null) {
            this.AGE = PropertyInteger.create("age", 0, 7);
        }

        return new BlockStateContainer(this, new IProperty[]{this.AGE});
    }

    public int getMetaFromState(IBlockState state) {
        return (Integer)state.getValue(this.AGE);
    }

    public IBlockState getStateFromMeta(int meta) {
        return this.getDefaultState().withProperty(this.AGE, Math.min(this.growthStages, meta));
    }

    public int tickRate(World worldIn) {
        return 1200 + worldIn.rand.nextInt(1200);
    }

    public int getGrowthStages() {
        return this.growthStages;
    }

    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return !this.isMaxAge(state);
    }

    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return !this.isMaxAge(state);
    }

    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        int i = this.getAge(state) + this.getBonemealAgeIncrease(worldIn);
        int j = this.getMaxAge();
        if (i > j) {
            i = j;
        }

        worldIn.setBlockState(pos, this.withAge(i), 2);
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

    public List<ItemStack> getDrops(IBlockAccess w, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList();
        if (w instanceof World) {
            World world = (World)w;
            int metadata = (Integer)state.getValue(this.AGE);
            if (metadata < this.growthStages) {
                ret.add(new ItemStack(TBItems.tobaccoseed, 1));
            } else if (metadata >= this.growthStages - 1) {
                if (world.rand.nextInt(this.growthStages) <= metadata && this.dropSeed != ItemStack.EMPTY) {
                    ret.add(new ItemStack(TBItems.tobacco_leaves));
                    ret.add(new ItemStack(TBItems.tobaccoseed));
                }

                for(int i = 0; i < 2 + fortune; ++i) {
                    if (world.rand.nextBoolean()) {
                        ret.add(new ItemStack(TBItems.tobacco_leaves));
                        ret.add(new ItemStack(TBItems.tobaccoseed));
                    }
                }
            }
        }

        return ret;
    }
}
