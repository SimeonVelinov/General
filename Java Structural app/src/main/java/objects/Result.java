package objects;

public class Result {
    private double[] suppReactions;
    private double[] Ku;
    double[] displacements;
    double axialForce;
    double bendingMomentOne;
    double BendingMomentTwo;

    public Result(double[] suppReactions, double[] ku, double[] displacements, double axialForce,
                  double bendingMomentOne, double bendingMomentTwo) {
        this.suppReactions = suppReactions;
        Ku = ku;
        this.displacements = displacements;
        this.axialForce = axialForce;
        this.bendingMomentOne = bendingMomentOne;
        BendingMomentTwo = bendingMomentTwo;
    }

    public double[] getSuppReactions() {
        return suppReactions;
    }

    public double[] getKu() {
        return Ku;
    }

    public double[] getDisplacements() {
        return displacements;
    }

    public double getAxialForce() {
        return axialForce;
    }

    public double getBendingMomentOne() {
        return bendingMomentOne;
    }

    public double getBendingMomentTwo() {
        return BendingMomentTwo;
    }
}

