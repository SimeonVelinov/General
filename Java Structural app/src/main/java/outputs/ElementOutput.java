package outputs;

import main.Main;
import objects.Element;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Arrays;
import java.util.List;

public class ElementOutput extends JFrame {
    private JPanel ElementOutput;
    private JButton exitButton;
    private JScrollPane elementScroll;

    public ElementOutput() {
        setContentPane(ElementOutput);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        buttonListeners();
        generateElementsList();
        setVisible(true);
    }

    private void buttonListeners() {
        exitButton.addActionListener(e -> ElementOutput.this.dispose());
    }

    private void generateElementsList() {
        List<Element> elementList = Main.getElements();
        String[] columnNames = {"Node One", "Node Two", "Iy", "Area", "Length", "Ke",
                "Is Pinned to Node One", "Is Pinned to Node Two", "UDL Z"};
        DefaultTableModel model = new DefaultTableModel();
        JTable elementTable = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        for (Element el : elementList) {
            Object[] row = new Object[9];
            row[0] = el.getNode1();
            row[1] = el.getNode2();
            row[2] = el.getIy();
            row[3] = el.getA();
            row[4] = el.getL();
            row[5] = Arrays.deepToString(el.getKe());
            row[6] = el.isPinnedAtNodeI();
            row[7] = el.isPinnedAtNodeJ();
            row[8] = el.getUdlZ();
            model.addRow(row);
        }
        elementScroll.getViewport().add(elementTable);
    }
}