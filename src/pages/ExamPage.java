package pages;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.File;

public class ExamPage extends JFrame {
    private String subject;
    private String className;

    public ExamPage(String subject, String questionFilePath, String className) {
        this.subject = subject;
        this.className = className;

        setTitle(subject + " Exam");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(10, 10, 10, 10);

        try (BufferedReader reader = new BufferedReader(new FileReader(questionFilePath))) {
            String line;
            int questionNumber = 1;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Question " + questionNumber + ":")) {
                    JLabel questionLabel = new JLabel(line);
                    constraints.anchor = GridBagConstraints.WEST;
                    panel.add(questionLabel, constraints);

                    ButtonGroup optionGroup = new ButtonGroup();
                    for (int i = 0; i < 4; i++) {
                        line = reader.readLine();
                        if (line != null && line.matches("[abcd]\\).*")) {
                            JRadioButton optionButton = new JRadioButton(line);
                            optionButton.setActionCommand(line.substring(0, 1));
                            constraints.anchor = GridBagConstraints.WEST;
                            panel.add(optionButton, constraints);
                            optionGroup.add(optionButton);
                        }
                    }
                    questionNumber++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 20));
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            Map<Integer, String> userAnswers = collectUserAnswers(panel);
            new ResultPage(subject, userAnswers, className).setVisible(true);
            dispose();
        });
        submitPanel.add(submitButton);

        JScrollPane scrollPane = new JScrollPane(panel);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(submitPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private Map<Integer, String> collectUserAnswers(JPanel panel) {
        Map<Integer, String> userAnswers = new HashMap<>();
        String[] answers= new String[10];
        Component[] components = panel.getComponents();
        int questionNumber = 0;
        String selectedAnswer = null;
        for (Component component : components) {
            if (component instanceof JRadioButton) {
                JRadioButton radioButton = (JRadioButton) component;
                if (radioButton.isSelected()) {
                    selectedAnswer = radioButton.getText();
                }
            }
            if (component instanceof JLabel && ((JLabel) component).getText().startsWith("Question")) {
                if (selectedAnswer != null) {
                    userAnswers.put(questionNumber, selectedAnswer);
                }
                selectedAnswer = null;
                questionNumber++;
            }
        }
        return userAnswers;
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] subjects = {"Math", "Physics", "Chemistry", "Biology"};
            for (String subject : subjects) {
                String filePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "files" + File.separator + subject + ".txt";
                String questionFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + "files" + File.separator + subject + " Question.txt";
                new ExamPage(subject, questionFilePath, "Class 9").setVisible(true);
            }
        });
    }
}
