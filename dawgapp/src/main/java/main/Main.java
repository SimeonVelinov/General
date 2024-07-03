package main;

import inputs.ElementInput;
import inputs.NodeInput;
import inputs.SectionInput;
import objects.Element;
import objects.Node;
import objects.Section;
import outputs.ElementOutput;
import outputs.NodeOutput;
import outputs.Results;
import outputs.SectionOutput;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main extends JFrame {
    private JPanel Main;

    private JButton newNodeButton;
    private JButton newSectionButton;
    private JButton newElementButton;

    private JButton showNodesButton;
    private JButton showElementsButton;
    private JButton showSectionsButton;

    private JButton exitButton;
    private JButton resultsButton;

    private static final List<Node> nodeList = new ArrayList<>();
    private static final List<Element> elementList = new ArrayList<>();
    private static final List<Section> sectionList = new ArrayList<>();

    public Main() {
        setContentPane(Main);
        setTitle("Placeholder Title");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        setVisible(true);
        buttonListeners();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void buttonListeners() {
        newNodeButton.addActionListener(e -> new NodeInput());
        newSectionButton.addActionListener(e -> new SectionInput());
        newElementButton.addActionListener(e -> new ElementInput());

        showNodesButton.addActionListener(e -> new NodeOutput());
        showSectionsButton.addActionListener(e -> new SectionOutput());
        showElementsButton.addActionListener(e -> new ElementOutput());

        resultsButton.addActionListener(e -> new Results());
        exitButton.addActionListener(e -> Main.this.dispose());
    }

    public static void addNode(Node node) {
        nodeList.add(node);
    }

    public static void addSection(Section sec) {
        sectionList.add(sec);
    }

    public static void addElement(Element el) {
        elementList.add(el);
    }

    public static List<Node> getNodes() {
        return nodeList;
    }

    public static List<Section> getSections() {
        return sectionList;
    }

    public static List<Element> getElements() {
        return elementList;
    }
}