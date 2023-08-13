package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassPage extends JFrame {
    public ClassPage() {
        setTitle("Next Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton class9Button = new JButton("Class 9");
        JButton class10Button = new JButton("Class 10");
        JButton class11Button = new JButton("Class 11");
        JButton class12Button = new JButton("Class 12");
        JButton admissionButton = new JButton("Admission");

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String buttonText = clickedButton.getText();

                setVisible(false);
                new SubjectPage(buttonText).setVisible(true);
            }
        };

        class9Button.addActionListener(buttonListener);
        class10Button.addActionListener(buttonListener);
        class11Button.addActionListener(buttonListener);
        class12Button.addActionListener(buttonListener);
        admissionButton.addActionListener(buttonListener);

        panel.add(class9Button);
        panel.add(class10Button);
        panel.add(class11Button);
        panel.add(class12Button);
        panel.add(admissionButton);

        add(panel);
    }
}