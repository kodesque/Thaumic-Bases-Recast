package tbases.init;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import tbases.common.blocks.FluidPyro;
import tbases.util.OnetimeCaller;

public class TBFluids {
    public static final FluidPyro FLUID_PYRO = new FluidPyro();
    public static final OnetimeCaller init = new OnetimeCaller(TBFluids::$init);

    public TBFluids() {
    }

    private static void $init() {
        addFluid(FLUID_PYRO);
    }

    private static void addFluid(Fluid fl) {
        FluidRegistry.registerFluid(fl);
        FluidRegistry.addBucketForFluid(fl);
    }
}
