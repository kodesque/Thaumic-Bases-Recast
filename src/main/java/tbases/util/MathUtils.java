package tbases.util;

import java.util.Random;

public class MathUtils {
    public MathUtils() {
    }

    public static boolean arrayContains(Object[] array, Object searched) {
        for(Object element : array) {
            if (element.equals(searched)) {
                return true;
            }
        }

        return false;
    }

    public static boolean arrayContains(int[] array, int searched) {
        for(int element : array) {
            if (element == searched) {
                return true;
            }
        }

        return false;
    }

    public static double randomDouble(Random rand) {
        return rand.nextDouble() - rand.nextDouble();
    }
}
