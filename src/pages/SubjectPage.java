package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectPage extends JFrame {
    public SubjectPage(String buttonText) {
        setTitle("Subjects");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel messageLabel = new JLabel("Which subject's elements do you need?");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(messageLabel, constraints);

        JButton mathButton = new JButton("Math");
        JButton physicsButton = new JButton("Physics");
        JButton chemistryButton = new JButton("Chemistry");
        JButton biologyButton = new JButton("Biology");

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String buttonText = clickedButton.getText();

                String filePath = System.getProperty("user.dir") +"/src/files/"+ buttonText + ".txt";
                String questionFilePath = System.getProperty("user.dir") +"/src/files/" + buttonText + " Question.txt";
                new ContentDisplayPage(buttonText, filePath, questionFilePath).setVisible(true);
                setVisible(false);
            }
        };

        // Set preferred size for buttons to make them bigger
        Dimension buttonSize = new Dimension(200, 50);
        mathButton.setPreferredSize(buttonSize);
        physicsButton.setPreferredSize(buttonSize);
        chemistryButton.setPreferredSize(buttonSize);
        biologyButton.setPreferredSize(buttonSize);

        mathButton.addActionListener(buttonListener);
        physicsButton.addActionListener(buttonListener);
        chemistryButton.addActionListener(buttonListener);
        biologyButton.addActionListener(buttonListener);

        panel.add(mathButton, constraints);
        panel.add(physicsButton, constraints);
        panel.add(chemistryButton, constraints);
        panel.add(biologyButton, constraints);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String buttonText = null;
            new SubjectPage(buttonText).setVisible(true);
        });
    }
}
