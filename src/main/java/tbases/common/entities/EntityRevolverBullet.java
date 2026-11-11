package tbases.common.entities;

import akka.japi.Pair;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import tbases.common.items.ItemRevolver;
import tbases.init.custom.RevolverUpgrade;
import thaumcraft.client.fx.FXDispatcher;

import java.util.ArrayList;

public class EntityRevolverBullet extends EntityThrowable {
    public Entity shooter;
    public ItemStack revolver;
    ArrayList<Pair<RevolverUpgrade, Integer>> upgrades = new ArrayList();
    public boolean isPrimal;

    public EntityRevolverBullet(World w) {
        super(w);
    }

    public EntityRevolverBullet(World w, EntityLivingBase shooter) {
        super(w, shooter);
        this.revolver = shooter.getHeldItemMainhand();
        this.shooter = shooter;
        if (!this.revolver.isEmpty() && this.revolver.getItem() instanceof ItemRevolver) {
            this.upgrades = ItemRevolver.getAllUpgradesFor(this.revolver);
            boolean allowNoclip = false;
            float speedIndex = 1.0F;

            for(Pair<RevolverUpgrade, Integer> p : this.upgrades) {
                if (!allowNoclip) {
                    allowNoclip = ((RevolverUpgrade)p.first()).bulletNoclip((EntityPlayer)shooter, this.revolver, (Integer)p.second());
                }

                speedIndex = (float)((RevolverUpgrade)p.first()).modifySpeed((EntityPlayer)shooter, this.revolver, speedIndex, (Integer)p.second());
                if (p.first() == RevolverUpgrade.primal) {
                    this.isPrimal = true;
                }
            }

            if (allowNoclip) {
                this.noClip = true;
            }

            this.motionX *= (double)3.0F;
            this.motionY *= (double)3.0F;
            this.motionZ *= (double)3.0F;
            this.motionX *= (double)speedIndex;
            this.motionY *= (double)speedIndex;
            this.motionZ *= (double)speedIndex;
        }

    }

    public void onUpdate() {
        if (this.world.isRemote) {
            FXDispatcher.INSTANCE.sparkle((float)this.posX, (float)this.posY, (float)this.posZ, 4.0F, 0.0F, 0.0F);
        }

        if (this.world.isRemote) {
            FXDispatcher.INSTANCE.sparkle((float)(this.posX - this.motionX / (double)20.0F), (float)(this.posY - this.motionY / (double)20.0F), (float)(this.posZ - this.motionZ / (double)20.0F), 4.0F, 0.0F, 0.0F);
        }

        if (this.ticksExisted >= 200) {
            this.setDead();
        }

        super.onUpdate();
        if (this.isPrimal && !this.isDead) {
            ++this.ticksExisted;
            this.onUpdate();
        }

    }

    protected float getGravityVelocity() {
        return this.isPrimal ? 0.0F : 0.01F;
    }

    protected void onImpact(RayTraceResult object) {
        if (!this.isDead) {
            if (!this.world.isRemote) {
                if (!this.world.isRemote && object.typeOfHit == RayTraceResult.Type.BLOCK) {
                    if (this.noClip) {
                        return;
                    }

                    if (this.world.isBlockNormalCube(object.getBlockPos(), true)) {
                        this.setDead();
                    }
                }

                if (object.typeOfHit == RayTraceResult.Type.ENTITY) {
                    Entity e = object.entityHit;
                    if (e == this.shooter) {
                        return;
                    }

                    if (e instanceof EntityLivingBase && e != this.shooter && !(e instanceof EntityRevolverBullet)) {
                        EntityLivingBase elb = (EntityLivingBase)e;
                        float initialDamage = 14.0F;

                        for(Pair<RevolverUpgrade, Integer> p : this.upgrades) {
                            initialDamage = ((RevolverUpgrade)p.first()).modifyDamage_start(elb, this.revolver, initialDamage, (Integer)p.second());
                        }

                        for(Pair<RevolverUpgrade, Integer> p : this.upgrades) {
                            initialDamage = ((RevolverUpgrade)p.first()).modifyDamage_end(elb, this.revolver, initialDamage, (Integer)p.second());
                        }

                        elb.attackEntityFrom(new EntityRevolverBullet.RevolverDamage("revolver", this.shooter), initialDamage);
                        boolean destroy = true;

                        for(Pair<RevolverUpgrade, Integer> p : this.upgrades) {
                            if (destroy) {
                                destroy = ((RevolverUpgrade)p.first()).afterhit(elb, (EntityPlayer)this.shooter, this.revolver, initialDamage, (Integer)p.second());
                            } else {
                                ((RevolverUpgrade)p.first()).afterhit(elb, (EntityPlayer)this.shooter, this.revolver, initialDamage, (Integer)p.second());
                            }
                        }

                        if (destroy) {
                            this.setDead();
                        }
                    }
                }

            }
        }
    }

    public void writeEntityToNBT(NBTTagCompound tag) {
        super.writeEntityToNBT(tag);
        if (this.revolver != null) {
            NBTTagCompound revolverTag = new NBTTagCompound();
            this.revolver.writeToNBT(revolverTag);
            tag.setTag("revolverTag", revolverTag);
        }

        tag.setBoolean("noclip", this.noClip);
        tag.setBoolean("primal", this.isPrimal);
    }

    public void readEntityFromNBT(NBTTagCompound tag) {
        super.readEntityFromNBT(tag);
        if (tag.hasKey("revolverTag")) {
            this.revolver = new ItemStack(tag.getCompoundTag("revolverTag"));
            this.upgrades = ItemRevolver.getAllUpgradesFor(this.revolver);
        }

        this.noClip = tag.getBoolean("noclip");
        this.isPrimal = tag.getBoolean("primal");
    }

    public static class RevolverDamage extends EntityDamageSource {
        public RevolverDamage(String damage, Entity sourceEntity) {
            super(damage, sourceEntity);
        }
    }
}
