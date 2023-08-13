import models.Student;
import pages.ClassPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginFrame(List<Student> students) {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        add(panel);

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        panel.add(new JLabel());
        loginButton = new JButton("Log In");
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);

                if ((username.equals(students.get(0).getUserName()) && passwordString.equals(students.get(0).getPassword())) ||
                        (username.equals(students.get(1).getUserName()) && passwordString.equals(students.get(1).getPassword()))) {
                    setVisible(false);
                    new ClassPage().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Invalid username or password.",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}