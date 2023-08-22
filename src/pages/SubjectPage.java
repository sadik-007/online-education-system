package pages;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SubjectPage extends JFrame {
    private String className;

    public SubjectPage(String className) {
        this.className = className;
        setTitle(className + " Subjects");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.insets = new Insets(10, 0, 10, 0);

        JLabel titleLabel = new JLabel("Subjects for " + className);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel, constraints);

        String[] subjects = {"Math", "Physics", "Chemistry", "Biology"};
        for (String subject : subjects) {
            JButton subjectButton = new JButton(subject);
            subjectButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            subjectButton.setPreferredSize(new Dimension(180, 50));
            subjectButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openContentDisplayPage(subject);
                }
            });
            panel.add(subjectButton, constraints);
        }

        JButton backButton = new JButton("Back to Class Page");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(180, 50));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ClassPage().setVisible(true);
                dispose();
            }
        });
        panel.add(backButton, constraints);

        add(panel);
    }

    private void openContentDisplayPage(String subject) {
        String filePath = System.getProperty("user.dir") + "/src/files/" + subject + ".txt";
        String questionFilePath = System.getProperty("user.dir") + "/src/files/" + subject + " Question.txt";
        new ContentDisplayPage(subject, filePath, questionFilePath, className).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] classNames = {"Class 9", "Class 10", "Class 11", "Class 12", "Admission"};
            for (String className : classNames) {
                new SubjectPage(className).setVisible(true);
            }
        });
    }
}
