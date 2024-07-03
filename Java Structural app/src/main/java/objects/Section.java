package objects;

public class Section {
    private double E;
    private double H;
    private double B;

    public Section(double e, double h, double b) {
        setE(e);
        setH(h);
        setB(b);
    }

    public void setE(double e) {
        if (e > 0)
            E = e;
        else throw new IllegalArgumentException("Incorrect value");
    }

    public void setH(double h) {
        if (h > 0)
            H = h;
        else throw new IllegalArgumentException("Incorrect value");
    }

    public void setB(double b) {
        if (b > 0)
            B = b;
        else throw new IllegalArgumentException("Incorrect value");
    }

    public double getE() {
        return E;
    }

    public double getH() {
        return H;
    }

    public double getB() {
        return B;
    }
}