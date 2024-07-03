package calculators;

import objects.Node;

public class Matrix {
    private static double[][] MatrixMulti(double[][] matrixA, double[][] matrixB) {
        double[][] result = new double[6][6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                double sum = 0;
                for (int k = 0; k < 6; k++) {
                    sum += matrixA[i][k] * matrixB[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    public static double calculateMomentInertiaY(double h, double b) {
        return (h * h * h * b) / 12;
    }

    public static double calculateArea(double h, double b) {
        return h * b;
    }

    public static double calculateLength(Node nodeOne, Node nodeTwo) {
        return Math.sqrt(
                Math.pow((nodeTwo.getX() - nodeOne.getX()), 2) +
                        Math.pow((nodeTwo.getZ() - nodeOne.getZ()), 2));
    }

    public static double[][] calculateLocStiffMatrix(double Em, double A, double Iy, double cOne, double cTwo,
                                                     double L, boolean isPinnedAtNodeI, boolean isPinnedAtNodeJ) {
        double[][] KeLocal = new double[6][6];
        if (isPinnedAtNodeI && isPinnedAtNodeJ)
            KeLocal = new double[][]{
                    {Em * A / L, 0, 0, -Em * A / L, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {-Em * A / L, 0, 0, Em * A / L, 0, 0},
                    {0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0}
            };
        if (isPinnedAtNodeI && !isPinnedAtNodeJ)
            KeLocal = new double[][]{
                    {Em * A / L, 0, 0, -Em * A / L, 0, 0},
                    {0, 3 * Em * Iy / Math.pow(L, 3), 0, 0, -3 * Em * Iy / Math.pow(L, 3), -3 * Em * Iy / Math.pow(L, 2)},
                    {0, 0, 0, 0, 0, 0},
                    {-Em * A / L, 0, 0, Em * A / L, 0, 0},
                    {0, -3 * Em * Iy / Math.pow(L, 3), 0, 0, 3 * Em * Iy / Math.pow(L, 3), 3 * Em * Iy / Math.pow(L, 2)},
                    {0, -3 * Em * Iy / Math.pow(L, 2), 0, 0, 3 * Em * Iy / Math.pow(L, 2), 3 * Em * Iy / L}
            };
        if (isPinnedAtNodeI && isPinnedAtNodeJ)
            KeLocal = new double[][]{
                    {Em * A / L, 0, 0, -Em * A / L, 0, 0},
                    {0, 3 * Em * Iy / Math.pow(L, 3), -3 * Em * Iy / Math.pow(L, 2), 0, -3 * Em * Iy / Math.pow(L, 3), 0},
                    {0, -3 * Em * Iy / Math.pow(L, 2), 3 * Em * Iy / L, 0, 3 * Em * Iy / Math.pow(L, 2), 0},
                    {-Em * A / L, 0, 0, Em * A / L, 0, 0},
                    {0, -3 * Em * Iy / Math.pow(L, 3), 3 * Em * Iy / Math.pow(L, 2), 0, 3 * Em * Iy / Math.pow(L, 3), 0},
                    {0, 0, 0, 0, 0, 0}
            };
        if (!isPinnedAtNodeI && !isPinnedAtNodeJ)
            KeLocal = new double[][]{
                    {Em * A / L, 0, 0, -Em * A / L, 0, 0},
                    {0, 12 * Em * Iy / Math.pow(L, 3), -6 * Em * Iy / Math.pow(L, 2), 0, -12 * Em * Iy / Math.pow(L, 3), -6 * Em * Iy / Math.pow(L, 2)},
                    {0, -6 * Em * Iy / Math.pow(L, 2), 4 * Em * Iy / L, 0, 6 * Em * Iy / Math.pow(L, 2), 2 * Em * Iy / L},
                    {-Em * A / L, 0, 0, Em * A / L, 0, 0},
                    {0, -12 * Em * Iy / Math.pow(L, 3), 6 * Em * Iy / Math.pow(L, 2), 0, 12 * Em * Iy / Math.pow(L, 3), 6 * Em * Iy / Math.pow(L, 2)},
                    {0, -6 * Em * Iy / Math.pow(L, 2), 2 * Em * Iy / L, 0, 6 * Em * Iy / Math.pow(L, 2), 4 * Em * Iy / L}
            };

        double[][] T = new double[][]{
                {cOne, cTwo, 0, 0, 0, 0},
                {-cTwo, cOne, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, cOne, cTwo, 0},
                {0, 0, 0, -cTwo, cOne, 0},
                {0, 0, 0, 0, 0, 1}
        };

        double[][] Tt = new double[][]{
                {cOne, -cTwo, 0, 0, 0, 0},
                {cTwo, cOne, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0},
                {0, 0, 0, cOne, -cTwo, 0},
                {0, 0, 0, cTwo, cOne, 0},
                {0, 0, 0, 0, 0, 1}
        };
        double[][] multi = new double[][]{
                MatrixMulti(KeLocal, T)[0],
                MatrixMulti(KeLocal, T)[1],
                MatrixMulti(KeLocal, T)[2],
                MatrixMulti(KeLocal, T)[3],
                MatrixMulti(KeLocal, T)[4],
                MatrixMulti(KeLocal, T)[5]};
        return new double[][]{
                MatrixMulti(Tt, multi)[0],
                MatrixMulti(Tt, multi)[1],
                MatrixMulti(Tt, multi)[2],
                MatrixMulti(Tt, multi)[3],
                MatrixMulti(Tt, multi)[4],
                MatrixMulti(Tt, multi)[5]};
    }

}