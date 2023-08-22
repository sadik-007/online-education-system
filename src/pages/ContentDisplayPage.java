package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ContentDisplayPage extends JFrame {
    public ContentDisplayPage(String subjectName, String filePath, String questionFilePath, String className) {
        setTitle(subjectName + " Content");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JTextArea contentTextArea = new JTextArea();
        contentTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(contentTextArea);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                contentTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton participateButton = new JButton("Participate Exam");
        participateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openExamPage(subjectName, questionFilePath, className);
            }
        });

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(participateButton, BorderLayout.SOUTH);

        add(panel);
    }

    private void openExamPage(String subjectName, String questionFilePath, String className) {
        new ExamPage(subjectName, questionFilePath, className).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] subjects = {"Math", "Physics", "Chemistry", "Biology"};
            for (String subject : subjects) {
                String filePath = System.getProperty("user.dir") + "/src/files/" + subject + ".txt";
                String questionFilePath = System.getProperty("user.dir") + "/src/files/" + subject + " Question.txt";
                new ContentDisplayPage(subject, filePath, questionFilePath, "Class 9").setVisible(true);
            }
        });
    }
}
