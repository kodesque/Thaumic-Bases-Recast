package tbases.utils.dummy;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MathUtils {
    public MathUtils() {
    }

    public static int pixelatedTextureSize(int current, int max, int textureSize) {
        if (current > max) {
            current = max;
        }

        float m = (float)current / (float)max * 100.0F;
        float n = m / 100.0F * (float)textureSize;
        return (int)n;
    }

    public static int getPercentage(int current, int max) {
        float m = (float)current / (float)max * 100.0F;
        return (int)m;
    }

    public static Coord2D polarOffset(Coord2D position, float angle, float distance) {
        float d0 = (float)((double)position.x + Math.cos((double)angle * Math.PI / (double)180.0F) * (double)distance);
        float d1 = (float)((double)position.z + Math.sin((double)angle * Math.PI / (double)180.0F) * (double)distance);
        return new Coord2D(d0, d1);
    }

    public static double randomDouble(Random rand) {
        return rand.nextDouble() - rand.nextDouble();
    }

    public static float randomFloat(Random rand) {
        return rand.nextFloat() - rand.nextFloat();
    }

    public static float getDifference(float pos1, float pos2) {
        float diff = pos1 - pos2;
        return Math.abs(diff);
    }

    public static double getDifference(double pos1, double pos2) {
        double diff = pos1 - pos2;
        return Math.abs(diff);
    }

    /** @deprecated */
    @Deprecated
    public static int[] swap(int a, int b) {
        return new int[]{b, a};
    }

    public static int convertToHex(int a) {
        return Integer.parseInt(Integer.toString(a), 16);
    }

    /** @deprecated */
    @Deprecated
    public static double module(double a) {
        if (a < (double)0.0F) {
            a = -a;
        }

        return a;
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

    public static int getIntInArray(int[] array, int searched) {
        for(int i = 0; i < array.length; ++i) {
            if (array[i] == searched) {
                return i;
            }
        }

        return -1;
    }

    public static boolean isArrayTheSame(boolean[] array) {
        boolean previous = array[0];

        for(boolean element : array) {
            if (element != previous) {
                return false;
            }

            previous = element;
        }

        return true;
    }

    @SafeVarargs
    public static <T extends Comparable<T>> T max(T... is) {
        T mI = is.length == 0 ? null : is[0];

        for(T i : is) {
            if (i.compareTo(mI) > 0) {
                mI = i;
            }
        }

        return mI;
    }

    public static <T> T randomElement(Iterable<? extends T> elements, Random rnd) {
        if (elements instanceof List) {
            List<T> lst = (List)elements;
            return (T)(lst.size() == 0 ? null : lst.get(rnd.nextInt(lst.size())));
        } else {
            ArrayList<T> lst = Lists.newArrayList(elements);
            return (T)(lst.size() == 0 ? null : lst.get(rnd.nextInt(lst.size())));
        }
    }

    public static float map(float valueIn, float inMin, float inMax, float outMin, float outMax) {
        return (valueIn - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }

    public static double map(double valueIn, double inMin, double inMax, double outMin, double outMax) {
        return (valueIn - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
    }
}