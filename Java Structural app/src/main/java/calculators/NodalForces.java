package calculators;

import main.Main;
import objects.Element;
import objects.Node;

import java.util.List;

public class NodalForces {
    public static double[] globalNodalForces(Element el) {
        List<Node> nodeList = Main.getNodes();
        double[][] localNodalForces = localNodalForces(el);

        double dX = nodeList.get(el.getNode2() - 1).getX() - nodeList.get(el.getNode1() - 1).getX();
        double dZ = nodeList.get(el.getNode2() - 1).getZ() - nodeList.get(el.getNode1() - 1).getZ();

        double c1 = dX / el.getL();
        double c2 = dZ / el.getL();

        double[][] dirCos;
        if (c1 != 0) {
            dirCos = new double[][]{
                    {c1, c2*-1, 0, 0, 0, 0},
                    {c2, c1, 0, 0, 0, 0},
                    {0, 0, 1, 0, 0, 0},
                    {0, 0, 0, c1, c2*-1, 0},
                    {0, 0, 0, c2, c1, 0},
                    {0, 0, 0, 0, 0, 1}
            };
        } else {
            dirCos = new double[][]{
                    {c1, c2, 0, 0, 0, 0},
                    {c2*-1, c1, 0, 0, 0, 0},
                    {0, 0, -1, 0, 0, 0},
                    {0, 0, 0, c1, c2, 0},
                    {0, 0, 0, c2*-1, c1, 0},
                    {0, 0, 0, 0, 0, -1}
            };
        }

        double[] globalNodalForces = new double[6];
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++)
                globalNodalForces[i] += dirCos[i][j] * localNodalForces[j][0];
        }

        return globalNodalForces;
    }

    public static double[][] localNodalForces(Element el) {
        double[][] localNodalForces = new double[6][1];

        if (el.isPinnedAtNodeI() && el.isPinnedAtNodeJ()) {
            localNodalForces[0][0] = 0;
            localNodalForces[1][0] = el.getUdlZ() * el.getL() / 2;
            localNodalForces[2][0] = 0;
            localNodalForces[3][0] = 0;
            localNodalForces[4][0] = el.getUdlZ() * el.getL() / 2;
            localNodalForces[5][0] = 0;
        }
        if (el.isPinnedAtNodeI() && !el.isPinnedAtNodeJ()) {
            localNodalForces[0][0] = 0;
            localNodalForces[1][0] = 3 * el.getUdlZ() * el.getL() / 8;
            localNodalForces[2][0] = 0;
            localNodalForces[3][0] = 0;
            localNodalForces[4][0] = 5 * el.getUdlZ() * el.getL() / 8;
            localNodalForces[5][0] = el.getUdlZ() * Math.pow(el.getL(), 2) / 8;
        }
        if (!el.isPinnedAtNodeI() && el.isPinnedAtNodeJ()) {
            localNodalForces[0][0] = 0;
            localNodalForces[1][0] = 5 * el.getUdlZ() * el.getL() / 8;
            localNodalForces[2][0] = (el.getUdlZ() * -1) * Math.pow(el.getL(), 2) / 8;
            localNodalForces[3][0] = 0;
            localNodalForces[4][0] = 3 * el.getUdlZ() * el.getL() / 8;
            localNodalForces[5][0] = 0;
        }
        if (!el.isPinnedAtNodeI() && !el.isPinnedAtNodeJ()) {
            localNodalForces[0][0] = 0;
            localNodalForces[1][0] = el.getUdlZ() * el.getL() / 2;
            localNodalForces[2][0] = (el.getUdlZ() * -1) * Math.pow(el.getL(), 2) / 12;
            localNodalForces[3][0] = 0;
            localNodalForces[4][0] = el.getUdlZ() * el.getL() / 2;
            localNodalForces[5][0] = el.getUdlZ() * Math.pow(el.getL(), 2) / 12;
        }
        return localNodalForces;
    }
}