package calculators;

import main.Main;
import objects.Element;
import objects.Globals;
import objects.Node;
import objects.Section;

import java.util.List;

import static calculators.NodalForces.localNodalForces;

public class Special {
    static List<Node> nodes = Main.getNodes();

    public static double[] gaussSeidel(double[][] K, double[] f, double[] u) {
        double tolerance = 1e-10;

        boolean converged = true;
        double[] out = new double[f.length];
        while (converged) {
            System.arraycopy(u, 0, out, 0, f.length);
            for (int i = 0; i < f.length; i++) {
                double sigma = 0;
                for (int j = 0; j < f.length; j++) {
                    if (j != i)
                        sigma += K[i][j] * u[j];
                }
                out[i] = (f[i] - sigma) / K[i][i];
            }
            for (int i = 0; i < f.length; i++) {
                if (Math.abs(out[i] - u[i]) > tolerance) {
                    converged = false;
                    break;
                }
            }
        }
        return out;
    }

    public static double[] calculateKu(double[][] K, double[] u) {
        double[] Ku = new double[nodes.size() * 3];
        for (int i = 0; i < nodes.size() * 3; i++) {
            for (int j = 0; j < nodes.size() * 3; j++)
                Ku[i] += K[i][j] * u[j];
        }
        return Ku;
    }

    public static double[] calculateSupportReactions(double[] Ku) {
        double[] f = Globals.assembleForceVector();
        double[] out = new double[nodes.size() * 3];
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).isSupportX())
                out[i * 3] = Ku[i * 3] - f[i * 3];
            if (nodes.get(i).isSupportZ())
                out[i * 3 + 1] = Ku[i * 3 + 1] - f[i * 3 + 1];
            if (nodes.get(i).isSupportMy())
                out[i * 3 + 2] = Ku[i * 3 + 2] - f[i * 3 + 2];
        }
        return out;
    }

    public static double[] internalForces(Element el) {
        List<Section> sections = Main.getSections();
        double[][] localNodalForces = localNodalForces(el);
        double dx = nodes.get(el.getNode2() - 1).getX() - nodes.get(el.getNode1() - 1).getX();
        double dz = nodes.get(el.getNode2() - 1).getZ() - nodes.get(el.getNode1() - 1).getZ();
        double c1 = dx / el.getL();
        double c2 = dz / el.getL();
        int[] eftab = el.getEftab();
        double[] u = gaussSeidel(Globals.getK(), Globals.getForces(), Globals.generateGlobVecDispl());
        double exLocal = ((u[eftab[3]] - u[eftab[0]]) * c1 + (u[eftab[4]] - u[eftab[1]]) * c2);
        double ezLocal = ((u[eftab[4]] - u[eftab[1]]) * c1 + (u[eftab[0]] - u[eftab[3]]) * c2);
        double axialF = sections.get(el.getSection()).getE() * el.getA() * exLocal / el.getL();
        double bendMomOne = 0;
        double bendMomTwo = 0;
        if (c1 != 0) {
            if (el.isPinnedAtNodeI() && !el.isPinnedAtNodeJ())
                bendMomTwo = ezLocal * 3 * sections.get(el.getSection()).getE() * el.getIy() / (Math.pow(el.getL(), 2))
                        + u[eftab[5]] * 3 * sections.get(el.getSection()).getE() * el.getIy() / el.getL() - localNodalForces[5][0];
            if (!el.isPinnedAtNodeI() && el.isPinnedAtNodeJ())
                bendMomOne = ezLocal * 3 * sections.get(el.getSection()).getE() * el.getIy() / (Math.pow(el.getL(), 2))
                        + u[eftab[2]] * 3 * sections.get(el.getSection()).getE() * el.getIy() / el.getL() - localNodalForces[2][0];
            if (!el.isPinnedAtNodeI() && !el.isPinnedAtNodeJ()) {
                bendMomOne = ((4 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[5]]) / el.getL()) +
                        ((2 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[2]]) / el.getL()) +
                        ((ezLocal * 6 * sections.get(el.getSection()).getE() * el.getIy()) / (Math.pow(el.getL(), 2))) -
                        localNodalForces[2][0];
                bendMomTwo = ((2 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[5]]) / el.getL()) +
                        ((4 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[2]]) / el.getL()) +
                        ((ezLocal * 6 * sections.get(el.getSection()).getE() * el.getIy()) / (Math.pow(el.getL(), 2))) -
                        localNodalForces[5][0];
            }
        } else {
            if (el.isPinnedAtNodeI() && !el.isPinnedAtNodeJ())
                bendMomTwo = ezLocal * 3 * sections.get(el.getSection()).getE() * el.getIy() / (Math.pow(el.getL(), 2))
                        + u[eftab[5]] * 3 * sections.get(el.getSection()).getE() * el.getIy() / el.getL() + localNodalForces[5][0];
            if (!el.isPinnedAtNodeI() && el.isPinnedAtNodeJ())
                bendMomOne = ezLocal * 3 * sections.get(el.getSection()).getE() * el.getIy() / (Math.pow(el.getL(), 2))
                        + u[eftab[2]] * 3 * sections.get(el.getSection()).getE() * el.getIy() / el.getL() + localNodalForces[2][0];
            if (!el.isPinnedAtNodeI() && !el.isPinnedAtNodeJ()) {
                bendMomOne = ((4 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[5]]) / el.getL()) +
                        ((2 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[2]]) / el.getL()) +
                        ((ezLocal * 6 * sections.get(el.getSection()).getE() * el.getIy()) / (Math.pow(el.getL(), 2))) +
                        localNodalForces[2][0];
                bendMomTwo = ((2 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[5]]) / el.getL()) +
                        ((4 * sections.get(el.getSection()).getE() * el.getIy() * u[eftab[2]]) / el.getL()) +
                        ((ezLocal * 6 * sections.get(el.getSection()).getE() * el.getIy()) / (Math.pow(el.getL(), 2))) +
                        localNodalForces[5][0];
            }
        }
        return new double[]{axialF, bendMomOne, bendMomTwo};
    }
}