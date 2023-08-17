package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ContentDisplayPage extends JFrame {
    public ContentDisplayPage(String title, String filePath, String questionFilePath) {
        setTitle(title);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1000, 850);
        setLocationRelativeTo(null);

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        try {
            Path file = Path.of(filePath);
            String content = Files.readString(file);
            textArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JPanel buttonPanel = new JPanel();


        JButton participateButton = new JButton("Participate Exam");
        Dimension buttonSize = new Dimension(200, 50);
        participateButton.setPreferredSize(buttonSize);

        buttonPanel.add(participateButton);

        participateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ExamPage(title, questionFilePath).setVisible(true);

                setVisible(false);
            }
        });

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
