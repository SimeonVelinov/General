package objects;

import calculators.NodalForces;
import main.Main;

import java.util.Arrays;
import java.util.List;

public class Globals {
    private static final List<Node> nodeList = Main.getNodes();
    private static final List<Element> elementList = Main.getElements();

    private static double[][] K = new double[nodeList.size() * 3][nodeList.size() * 3];
    private static double[] fFromUDL = new double[nodeList.size() * 3];
    private static double[] forces = new double[nodeList.size() * 3];

    public static double[][] getK() {
        return K;
    }

    public static double[] getForces() {
        return forces;
    }

    public static double[] generateGlobVecDispl() {
        double[] u = new double[nodeList.size() * 3];
        Arrays.fill(u, 0.1);

        for (int i = 0; i < nodeList.size(); i++) {
            if (nodeList.get(i).isSupportX())
                u[i * 3] = 0;
            if (nodeList.get(i).isSupportZ())
                u[i * 3 + 1] = 0;
            if (nodeList.get(i).isSupportMy())
                u[i * 3 + 2] = 0;
        }
        return u;
    }

    public static void addToGlobalMatrix(Element el) {

        MergeElementIntoGlobalStiff(el.getKe(), el.getEftab(), K);
    }

    public static double[] assembleForceVector() {
        updateFromUDL();
        for (int i = 0; i < nodeList.size(); i++) {
            forces[i * 3] = nodeList.get(i).getX();
            forces[i * 3 + 1] = nodeList.get(i).getZ();
            forces[i * 3 + 2] = nodeList.get(i).getmY();
        }
        for (int i = 0; i < nodeList.size() * 3; i++)
            forces[i] += fFromUDL[i];
        return forces;
    }

    private static void updateFromUDL() {
        for (Element el : elementList) {
            double[] globalNF = NodalForces.globalNodalForces(el);
            fFromUDL[(el.getNode1() - 1) * 3] += globalNF[0];
            fFromUDL[(el.getNode1() - 1) * 3 + 1] += globalNF[1];
            fFromUDL[(el.getNode1() - 1) * 3 + 2] += globalNF[2];
            fFromUDL[(el.getNode2() - 1) * 3] += globalNF[3];
            fFromUDL[(el.getNode2() - 1) * 3 + 1] += globalNF[4];
            fFromUDL[(el.getNode2() - 1) * 3 + 2] += globalNF[5];
        }
    }

    private static void MergeElementIntoGlobalStiff(double[][] el, int[] eftab, double[][] K) {

        for (int i = 0; i < eftab.length; i++) {
            int ii = eftab[i];
            for (int j = 0; j < eftab.length; j++) {
                int jj = eftab[j];
                K[ii][jj] += el[i][j];
                if (ii != jj) K[ii][jj] += el[i][j];
            }
        }
    }
}