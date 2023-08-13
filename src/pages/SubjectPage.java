package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectPage extends JFrame {
    public SubjectPage(String className) {
        setTitle(className);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));

        JButton mathButton = new JButton("Math");
        JButton physicsButton = new JButton("Physics");
        JButton chemistryButton = new JButton("Chemistry");
        JButton biologyButton = new JButton("Biology");

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String buttonText = clickedButton.getText();


                JOptionPane.showMessageDialog(null, "You clicked: " + buttonText);
            }
        };

        mathButton.addActionListener(buttonListener);
        physicsButton.addActionListener(buttonListener);
        chemistryButton.addActionListener(buttonListener);
        biologyButton.addActionListener(buttonListener);

        panel.add(mathButton);
        panel.add(physicsButton);
        panel.add(chemistryButton);
        panel.add(biologyButton);

        add(panel);
    }
}