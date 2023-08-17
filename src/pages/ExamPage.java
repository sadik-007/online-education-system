package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ExamPage extends JFrame {
    private List<String> questions = new ArrayList<>();
    private List<String[]> options = new ArrayList<>();
    private List<ButtonGroup> buttonGroups = new ArrayList<>();

    public ExamPage(String title, String questionFilePath) {
        setTitle(title + " Exam");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        loadQuestionsAndOptions(questionFilePath);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Exam: " + title);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(10));

        for (int i = 0; i < questions.size(); i++) {
            JLabel questionLabel = new JLabel(questions.get(i));
            questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(questionLabel);

            JPanel optionsPanel = new JPanel();
            optionsPanel.setLayout(new GridLayout(0, 1));

            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroups.add(buttonGroup);

            String[] currentOptions = options.get(i);
            for (int j = 0; j < currentOptions.length; j++) {
                JRadioButton optionButton = new JRadioButton(currentOptions[j]);
                buttonGroup.add(optionButton);
                optionsPanel.add(optionButton);
            }

            panel.add(optionsPanel);
            panel.add(Box.createVerticalStrut(10));
        }

        JButton submitButton = new JButton("Submit");
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(submitButton);

        add(panel);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < buttonGroups.size(); i++) {
                    ButtonGroup group = buttonGroups.get(i);
                    for (Enumeration<AbstractButton> buttons = group.getElements(); buttons.hasMoreElements();) {
                        AbstractButton button = (AbstractButton) ((Enumeration<?>) buttons).nextElement();
                        if (button.isSelected()) {

                            System.out.println("Question " + (i + 1) + ": " + button.getText());
                            break;
                        }
                    }
                }
            }
        });
    }

    private void loadQuestionsAndOptions(String questionFilePath) {
        try {
            Path file = Path.of(questionFilePath);
            List<String> lines = Files.readAllLines(file);

            int currentQuestionIndex = -1;

            for (String line : lines) {
                if (line.startsWith("Question")) {
                    currentQuestionIndex++;
                    questions.add(line);
                } else if (line.matches("[a-d]\\) .*")) {
                    String[] currentOptions = options.get(currentQuestionIndex);
                    currentOptions[currentOptions.length - 1] += line;
                } else {
                    options.add(line.split(","));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] subjects = {"Math", "Physics", "Chemistry", "Biology"};
            for (String subject : subjects) {
                String questionFilePath = "E:/Java Practice/online-education-system/" + subject + " Question.txt";
                new ExamPage(subject, questionFilePath).setVisible(true);
            }
        });
    }
}
