package tbases.common.blocks.plants;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCactus;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;

import java.util.ArrayList;
import java.util.List;

public class BlockRainbowCactus extends BlockCactus {
    public static final int[] allowedDyes = new int[]{1, 2, 5, 2, 6, 7, 2, 8, 9, 10, 2, 11, 12, 13, 14, 2};

    public BlockRainbowCactus() {
        this.setSoundType(SoundType.CLOTH);
    }

    public void onEntityCollision(World worldIn, BlockPos pos, IBlockState state, Entity entityIn) {
    }

    public List<ItemStack> getDrops(IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ArrayList<ItemStack> ret = new ArrayList();
        if (world instanceof World) {
            World w = (World)world;
            if (w.getBlockState(pos.down()).getBlock() != this) {
                ret.add(new ItemStack(this, 1, 0));
                return ret;
            }

            for(int i = 0; i < 3 + w.rand.nextInt(8); ++i) {
                ret.add(new ItemStack(Items.DYE, 1, allowedDyes[w.rand.nextInt(allowedDyes.length)]));
            }
        }

        return ret;
    }

    public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction, IPlantable plantable) {
        Block plant = plantable.getPlant(world, pos.up()).getBlock();
        return plant == this ? true : super.canSustainPlant(state, world, pos, direction, plantable);
    }

    public List<IBlockState> listPossibleStates(Block b) {
        ArrayList<IBlockState> ret = new ArrayList();

        for(int i = 0; i < 15; ++i) {
            ret.add(this.getStateFromMeta(i));
        }

        return ret;
    }
}
