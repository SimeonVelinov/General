package inputs;

import main.Main;
import objects.Node;

import javax.swing.*;

public class NodeInput extends JFrame {
    private JPanel NodeInput;
    private JButton addNodeButton;

    private JButton exitButton;

    private JRadioButton supportX;
    private JRadioButton supportZ;
    private JRadioButton supportMy;

    private JTextField xPosInput;
    private JTextField zPosInput;
    private JTextField forceX;
    private JTextField forceZ;
    private JTextField momY;

    public NodeInput() {
        setContentPane(NodeInput);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        buttonListeners();
    }

    private void buttonListeners() {
        addNodeButton.addActionListener(e -> {
            try {
                Node node = new Node(
                        Double.parseDouble(xPosInput.getText()),
                        Double.parseDouble(zPosInput.getText()),
                        Double.parseDouble(forceX.getText()),
                        Double.parseDouble(forceZ.getText()),
                        Double.parseDouble(momY.getText()),
                        supportX.isSelected(),
                        supportZ.isSelected(),
                        supportMy.isSelected());
                Main.addNode(node);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(NodeInput.this,
                        "Missing data in node\n" + err);
            }
        });
        exitButton.addActionListener(e -> NodeInput.this.dispose());
    }
}