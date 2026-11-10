package tbases.common.tiles;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import tbases.common.blocks.BlockBraizer;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.common.lib.events.EssentiaHandler;

public class TileBraizer extends TileEntity implements ITickable {
    public int burnTime;
    public int uptime;

    public TileBraizer() {
    }

    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() == newSate.getBlock() ? false : super.shouldRefresh(world, pos, oldState, newSate);
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.burnTime = compound.getInteger("burnTime");
    }

    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        super.writeToNBT(compound);
        return compound;
    }

    public void update() {
        ++this.uptime;
        if (this.burnTime > 0) {
            --this.burnTime;
        } else if (EssentiaHandler.drainEssentia(this, Aspect.FIRE, (EnumFacing)null, 6, false, 6)) {
            this.burnTime = 1600;
            this.world.setBlockState(this.getPos(), this.world.getBlockState(this.getPos()).withProperty(BlockBraizer.STATE, 1));
        }

        if (!this.world.isRemote && this.burnTime <= 0) {
            int metadata = (Integer)this.getWorld().getBlockState(this.getPos()).getValue(BlockBraizer.STATE);
            if (metadata == 1) {
                this.world.setBlockState(this.getPos(), this.world.getBlockState(this.getPos()).withProperty(BlockBraizer.STATE, 0));
            }

        } else {
            if (!this.world.isRemote) {
                for(EntityLivingBase elb : this.world.getEntitiesWithinAABB(EntityLivingBase.class, (new AxisAlignedBB((double)this.pos.getX(), (double)this.pos.getY(), (double)this.pos.getZ(), (double)(this.pos.getX() + 1), (double)(this.pos.getY() + 1), (double)(this.pos.getZ() + 1))).expand((double)12.0F, (double)6.0F, (double)12.0F))) {
                    if (elb instanceof EntityPlayer) {
                        elb.addPotionEffect(new PotionEffect(MobEffects.RESISTANCE, 2500, 0, true, false));
                        elb.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 2500, 0, true, false));
                    }

                    if (elb instanceof EntityMob && !(elb instanceof EntityPlayer)) {
                        if (elb.isEntityUndead()) {
                            elb.addPotionEffect(new PotionEffect(MobEffects.INSTANT_HEALTH, 200, 2, true, false));
                        } else {
                            elb.addPotionEffect(new PotionEffect(MobEffects.INSTANT_DAMAGE, 200, 2, true, false));
                        }
                    }
                }
            }

        }
    }
}
