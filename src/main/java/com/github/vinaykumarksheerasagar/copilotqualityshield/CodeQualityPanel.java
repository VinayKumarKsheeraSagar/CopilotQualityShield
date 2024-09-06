package com.github.vinaykumarksheerasagar.copilotqualityshield;

import javax.swing.*;
import java.util.List;

public class CodeQualityPanel extends JPanel {
    private JTextArea textArea;

    public CodeQualityPanel() {
        textArea = new JTextArea();
        add(new JScrollPane(textArea));
    }

    public void displayIssues(List<String> issues) {
        textArea.setText(String.join("\n", issues));
    }
}