package tbases.common.blocks;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;

public class FluidPyro extends Fluid {
    public FluidPyro() {
        super("pyro", new ResourceLocation("thaumicbases:blocks/fluid_pyro_still"), new ResourceLocation("thaumicbases:blocks/fluid_pyro_flow"));
        this.setUnlocalizedName("pyro");
    }

    public String getUnlocalizedName() {
        return "fluid.pyro";
    }
}
