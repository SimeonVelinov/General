package inputs;

import main.Main;
import objects.Section;

import javax.swing.*;

public class SectionInput extends JFrame {
    private JPanel SectionInput;
    private JButton exitButton;
    private JTextField youngModInput;
    private JTextField heightInput;
    private JTextField widthInput;
    private JButton addSection;


    public SectionInput() {
        setContentPane(SectionInput);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        buttonListeners();
    }

    private void buttonListeners() {
        addSection.addActionListener(e -> {
            try {
                Section sec = new Section(
                        Double.parseDouble(youngModInput.getText()),
                        Double.parseDouble(heightInput.getText()),
                        Double.parseDouble(widthInput.getText()));
                Main.addSection(sec);
            } catch (Exception err) {
                JOptionPane.showMessageDialog(SectionInput.this,
                        "Missing data in section\n" + err);
            }
        });
        exitButton.addActionListener(e -> SectionInput.this.dispose());
    }
}