package inputs;

import calculators.Matrix;
import main.Main;
import objects.Element;
import objects.Globals;
import objects.Node;
import objects.Section;

import javax.swing.*;
import java.util.List;

public class ElementInput extends JFrame {
    private JPanel elementInput;
    private JButton addElementButton;
    private JButton exitButton;

    private JRadioButton nodeOnePinned;
    private JRadioButton nodeTwoPinned;

    private JTextField targetSection;
    private JTextField nodeOne;
    private JTextField nodeTwo;
    private JTextField udl;

    public ElementInput() {
        setContentPane(elementInput);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        buttonListeners();
    }

    private void buttonListeners() {
        addElementButton.addActionListener(e -> {
            try {
                List<Section> sectionList = Main.getSections();
                int sectionIndex = Integer.parseInt(targetSection.getText()) - 1;
                Section sec = sectionList.get(sectionIndex);
                List<Node> nodeList = Main.getNodes();
                Node firstNode = nodeList.get(Integer.parseInt(nodeOne.getText()) - 1);
                Node secondNode = nodeList.get(Integer.parseInt(nodeTwo.getText()) - 1);
                double l = Matrix.calculateLength(firstNode, secondNode);
                double nodeTarOne = (nodeList.get(Integer.parseInt(nodeTwo.getText()) - 1).getX() -
                        nodeList.get(Integer.parseInt(nodeOne.getText()) - 1).getX()) / l;
                double nodeTarTwo = (nodeList.get(Integer.parseInt(nodeTwo.getText()) - 1).getZ() -
                        nodeList.get(Integer.parseInt(nodeOne.getText()) - 1).getZ()) / l;
                Element el = new Element(
                        Integer.parseInt(nodeOne.getText()),
                        Integer.parseInt(nodeTwo.getText()),
                        Matrix.calculateMomentInertiaY(sec.getH(), sec.getB()),
                        Matrix.calculateArea(sec.getH(), sec.getB()),
                        l,
                        Matrix.calculateLocStiffMatrix(
                                sec.getE(),
                                Matrix.calculateArea(sec.getH(), sec.getB()),
                                Matrix.calculateMomentInertiaY(sec.getH(), sec.getB()),
                                nodeTarOne,
                                nodeTarTwo,
                                Matrix.calculateLength(firstNode, secondNode),
                                nodeOnePinned.isSelected(),
                                nodeTwoPinned.isSelected()),
                        nodeOnePinned.isSelected(),
                        nodeTwoPinned.isSelected(),
                        Double.parseDouble(udl.getText()),
                        sectionIndex
                );
                Main.addElement(el);
                Globals.addToGlobalMatrix(el);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(ElementInput.this,
                        "Missing data in Element\n" + err);
            }
        });
        exitButton.addActionListener(e -> ElementInput.this.dispose());
    }
}