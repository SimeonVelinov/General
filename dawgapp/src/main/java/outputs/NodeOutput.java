package outputs;

import main.Main;
import objects.Node;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;

public class NodeOutput extends JFrame {
    private JPanel nodeOutput;
    private JButton exitButton;
    private JScrollPane nodeScroll;

    public NodeOutput() {
        setContentPane(nodeOutput);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        buttonListeners();
        generateNodesList();
        setVisible(true);
    }

    private void buttonListeners() {
        exitButton.addActionListener(e -> NodeOutput.this.dispose());
    }

    private void generateNodesList() {
        List<Node> nodeList = Main.getNodes();
        String[] columnNames = {"Node Num", "PosX", "PosZ", "ForX", "ForZ", "MomY", "SuppX", "SuppZ", "SuppY"};
        DefaultTableModel model = new DefaultTableModel();
        JTable nodeTable = new JTable(model);
        model.setColumnIdentifiers(columnNames);
        for (Node node : nodeList) {
            Object[] row = new Object[8];
            row[0] = node.getX();
            row[1] = node.getZ();
            row[2] = node.getfX();
            row[3] = node.getfZ();
            row[4] = node.getmY();
            row[5] = node.isSupportX();
            row[6] = node.isSupportZ();
            row[7] = node.isSupportMy();
            model.addRow(row);
        }
        nodeScroll.getViewport().add(nodeTable);
    }
}