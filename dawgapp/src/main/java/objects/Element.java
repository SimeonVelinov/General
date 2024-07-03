package objects;

public class Element {
    private int Node1;
    private int Node2;
    private int section;
    private double Iy;
    private double A;
    private double L;
    private double[][] Ke;
    private boolean isPinnedAtNodeI;
    private boolean isPinnedAtNodeJ;
    private double udlZ;
    private int[] eftab;

    public Element(int node1, int node2, double iy, double a, double l,
                   double[][] ke, boolean isPinnedAtNodeI,
                   boolean isPinnedAtNodeJ, double udlZ, int secId) {
        Node1 = node1;
        Node2 = node2;
        Iy = iy;
        A = a;
        L = l;
        Ke = ke;
        this.isPinnedAtNodeI = isPinnedAtNodeI;
        this.isPinnedAtNodeJ = isPinnedAtNodeJ;
        this.udlZ = udlZ;
        this.section = secId;
        eftab = new int[]{
                (getNode1() - 1) * 3, (getNode1() - 1) * 3 + 1, (getNode1() - 1) * 3 + 2,
                (getNode2() - 1) * 3, (getNode2() - 1) * 3 + 1, (getNode2() - 1) * 3 + 2,
        };
    }

    public int getNode1() {
        return Node1;
    }

    public int getNode2() {
        return Node2;
    }

    public int getSection() {
        return section;
    }

    public double getIy() {
        return Iy;
    }

    public double getA() {
        return A;
    }

    public double getL() {
        return L;
    }

    public double[][] getKe() {
        return Ke;
    }

    public boolean isPinnedAtNodeI() {
        return isPinnedAtNodeI;
    }

    public boolean isPinnedAtNodeJ() {
        return isPinnedAtNodeJ;
    }

    public double getUdlZ() {
        return udlZ;
    }

    public int[] getEftab() {
        return eftab;
    }
}