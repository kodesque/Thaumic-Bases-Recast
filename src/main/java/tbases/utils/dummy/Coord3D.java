package tbases.utils.dummy;

public class Coord3D {
    public float x;
    public float y;
    public float z;

    public Coord3D(double posX, double posY, double posZ) {
        this.x = (float)posX;
        this.y = (float)posY;
        this.z = (float)posZ;
    }

    public Coord3D(float i, float k, float j) {
        this.x = i;
        this.y = k;
        this.z = j;
    }

    public Coord3D() {
        this(0.0F, 0.0F, 0.0F);
    }

    public String toString() {
        return "||x:" + this.x + "||y:" + this.y + "||z:" + this.z;
    }

    public static Coord3D fromString(String data) {
        DummyData[] dt = DataStorage.parseData(data);
        float cX = Float.parseFloat(dt[0].fieldValue);
        float cY = Float.parseFloat(dt[1].fieldValue);
        float cZ = Float.parseFloat(dt[2].fieldValue);
        return new Coord3D(cX, cY, cZ);
    }

    public boolean equals(Object obj) {
        return obj instanceof Coord3D ? this.x == ((Coord3D)obj).x && this.y == ((Coord3D)obj).y && this.z == ((Coord3D)obj).z : super.equals(obj);
    }

    public int hashCode() {
        return Float.hashCode(this.x) + Float.hashCode(this.y) ^ 3 + Float.hashCode(this.z) ^ 9;
    }
}