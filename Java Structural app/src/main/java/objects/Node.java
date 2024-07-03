package objects;

public class Node {
    private final double x;
    private final double z;
    private final double fX;
    private final double fZ;
    private final double mY;
    private final boolean supportX;
    private final boolean supportZ;
    private final boolean supportMy;

    public Node(double x, double z, double fX, double fZ, double mY,
                boolean supportX, boolean supportZ, boolean supportMy) {
        this.x = x;
        this.z = z;
        this.fX = fX;
        this.fZ = fZ;
        this.mY = mY;
        this.supportX = supportX;
        this.supportZ = supportZ;
        this.supportMy = supportMy;
    }

    public double getX() {
        return x;
    }

    public double getZ() {
        return z;
    }

    public double getfX() {
        return fX;
    }

    public double getfZ() {
        return fZ;
    }

    public double getmY() {
        return mY;
    }

    public boolean isSupportX() {
        return supportX;
    }

    public boolean isSupportZ() {
        return supportZ;
    }

    public boolean isSupportMy() {
        return supportMy;
    }
}
