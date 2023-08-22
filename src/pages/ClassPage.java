package pages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClassPage extends JFrame {
    public ClassPage() {
        setTitle("Class Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel messageLabel = new JLabel("Which class's elements do you need?");
        messageLabel.setFont(new Font("Arial", Font.BOLD, 18));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(messageLabel, constraints);

        JButton class9Button = new JButton("Class 9");
        JButton class10Button = new JButton("Class 10");
        JButton class11Button = new JButton("Class 11");
        JButton class12Button = new JButton("Class 12");
        JButton admissionButton = new JButton("Admission");

        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton clickedButton = (JButton) e.getSource();
                String className = clickedButton.getText();

                new SubjectPage(className).setVisible(true);
                setVisible(false);
            }
        };

        class9Button.addActionListener(buttonListener);
        class10Button.addActionListener(buttonListener);
        class11Button.addActionListener(buttonListener);
        class12Button.addActionListener(buttonListener);
        admissionButton.addActionListener(buttonListener);

        Dimension buttonSize = new Dimension(200, 50);
        class9Button.setPreferredSize(buttonSize);
        class10Button.setPreferredSize(buttonSize);
        class11Button.setPreferredSize(buttonSize);
        class12Button.setPreferredSize(buttonSize);
        admissionButton.setPreferredSize(buttonSize);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(class9Button, constraints);

        constraints.gridy = 2;
        panel.add(class10Button, constraints);

        constraints.gridy = 3;
        panel.add(class11Button, constraints);

        constraints.gridy = 4;
        panel.add(class12Button, constraints);

        constraints.gridy = 5;
        panel.add(admissionButton, constraints);

        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ClassPage().setVisible(true);
        });
    }
}