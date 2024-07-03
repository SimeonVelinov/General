package outputs;

import main.Main;
import objects.Section;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class SectionOutput extends JFrame {
    private JPanel sectionOutput;
    private JButton exitButton;
    private JScrollPane sectionScroll;

    public SectionOutput() {
        setContentPane(sectionOutput);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        buttonListeners();
        generateSectionsList();
        setVisible(true);
    }

    private void buttonListeners() {
        exitButton.addActionListener(e -> SectionOutput.this.dispose());
    }

    private void generateSectionsList() {
        List<Section> sectionList = Main.getSections();
        String[] columnNames = {"Young Mod", "Height", "Width"};
        DefaultTableModel model = new DefaultTableModel();
        JTable sectionTable = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        for (Section sec : sectionList) {
            Object[] row = new Object[3];
            row[0] = sec.getE();
            row[1] = sec.getH();
            row[2] = sec.getB();
            model.addRow(row);
        }
        sectionScroll.getViewport().add(sectionTable);
    }
}