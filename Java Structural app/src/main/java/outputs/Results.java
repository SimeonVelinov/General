package outputs;

import calculators.Special;
import main.Main;
import objects.Element;
import objects.Globals;
import objects.Result;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Results extends JFrame {
    private JPanel Result;
    private JScrollPane resultPane;
    private JButton exitButton;

    public Results() {
        setContentPane(Result);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        buttonListeners();
        generateResults();
        setVisible(true);
    }

    private void buttonListeners() {
        exitButton.addActionListener(e -> Results.this.dispose());
    }

    private void generateResults() {
        List<Element> elementsList = Main.getElements();
        List<Result> resultList = new ArrayList<>();
        for (Element el : elementsList) {
            double[] res = Special.internalForces(el);
            resultList.add(new Result(Special.calculateSupportReactions(
                    Special.calculateKu(Globals.getK(), Globals.generateGlobVecDispl())),
                    Special.calculateKu(Globals.getK(), Globals.generateGlobVecDispl()),
                    Globals.generateGlobVecDispl(),
                    res[0],
                    res[1],
                    res[2]));
        }
        String[] columnNames = {"Support Reactions", "Ku", "Displacements",
                "Axial Force", "Bend Mom One", "Bend Mom Two"};
        DefaultTableModel model = new DefaultTableModel();
        JTable nodeTable = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        for (Result result : resultList) {
            Object[] row = new Object[6];
            row[0] = Arrays.toString(result.getSuppReactions());
            row[1] = Arrays.toString(result.getKu());
            row[2] = Arrays.toString(result.getDisplacements());
            row[3] = result.getAxialForce();
            row[4] = result.getBendingMomentOne();
            row[5] = result.getBendingMomentTwo();
            model.addRow(row);
        }
        resultPane.getViewport().add(nodeTable);
    }
}