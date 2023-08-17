import models.Student;
import pages.ClassPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton changePasswordButton;

    private List<Student> students;

    public LoginFrame(List<Student> students) {
        this.students = students;

        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 850);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        add(panel);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);


        JLabel welcomeLabel = new JLabel("Welcome To Online Education");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.PAGE_START;
        panel.add(welcomeLabel, constraints);
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.LINE_START;

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Username:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        usernameField = new JTextField(20);
        panel.add(usernameField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Password:"), constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        loginButton = new JButton("Log In");
        panel.add(loginButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        changePasswordButton = new JButton("Change Password");
        panel.add(changePasswordButton, constraints);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                char[] password = passwordField.getPassword();
                String passwordString = new String(password);

                if (isValidLogin(username, passwordString)) {
                    setVisible(false);
                    new ClassPage().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                            "Invalid username or password.",
                            "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePasswordDialog dialog = new ChangePasswordDialog();
                dialog.setLocationRelativeTo(LoginFrame.this);
                dialog.setVisible(true);
            }
        });
    }

    private boolean isValidLogin(String username, String password) {
        for (Student student : students) {
            if (username.equals(student.getUserName()) && password.equals(student.getPassword())) {
                return true;
            }
        }
        return false;
    }

    private class ChangePasswordDialog extends JDialog {
        private JTextField usernameField;
        private JTextField oldPasswordField;
        private JTextField newPasswordField;
        private JButton changeButton;

        public ChangePasswordDialog() {
            setTitle("Change Password");
            setSize(400, 250);
            setModal(true);
            setResizable(false);

            JPanel panel = new JPanel(new GridBagLayout());
            add(panel);

            GridBagConstraints constraints = new GridBagConstraints();
            constraints.insets = new Insets(10, 10, 10, 10);

            constraints.gridx = 0;
            constraints.gridy = 0;
            panel.add(new JLabel("Username:"), constraints);

            constraints.gridx = 1;
            constraints.gridy = 0;
            usernameField = new JTextField(20);
            panel.add(usernameField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 1;
            panel.add(new JLabel("Old Password:"), constraints);

            constraints.gridx = 1;
            constraints.gridy = 1;
            oldPasswordField = new JTextField(20);
            panel.add(oldPasswordField, constraints);

            constraints.gridx = 0;
            constraints.gridy = 2;
            panel.add(new JLabel("New Password:"), constraints);

            constraints.gridx = 1;
            constraints.gridy = 2;
            newPasswordField = new JTextField(20);
            panel.add(newPasswordField, constraints);

            constraints.gridx = 1;
            constraints.gridy = 3;
            changeButton = new JButton("Change");
            panel.add(changeButton, constraints);

            changeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String username = usernameField.getText();
                    String oldPassword = oldPasswordField.getText();
                    String newPassword = newPasswordField.getText();

                    if (changePassword(username, oldPassword, newPassword)) {
                        JOptionPane.showMessageDialog(ChangePasswordDialog.this,
                                "Password changed successfully.",
                                "Password Changed", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(ChangePasswordDialog.this,
                                "Invalid username or old password.",
                                "Password Change Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        }

        private boolean changePassword(String username, String oldPassword, String newPassword) {
            try {
                File file = new File(System.getProperty("user.dir") + "/src/files/UserName&Password.txt");
                BufferedReader reader = new BufferedReader(new FileReader(file));
                List<String> lines = new ArrayList<>();
                String line;
                while ((line = reader.readLine()) != null) {
                    lines.add(line);
                }
                reader.close();

                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                boolean passwordChanged = false;
                for (int i = 0; i < lines.size(); i += 2) {
                    String existingUsername = lines.get(i);
                    String existingPassword = lines.get(i + 1);
                    if (existingUsername.equals(username) && existingPassword.equals(oldPassword)) {
                        writer.write(username);
                        writer.newLine();
                        writer.write(newPassword);
                        writer.newLine();
                        passwordChanged = true;

                        for (Student student : students) {
                            if (student.getUserName().equals(username)) {
                                student.setPassword(newPassword);
                                break;
                            }
                        }
                    } else {
                        writer.write(existingUsername);
                        writer.newLine();
                        writer.write(existingPassword);
                        writer.newLine();
                    }
                }
                writer.close();
                return passwordChanged;
            } catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            List<Student> students = new ArrayList<>();
            students.add(new Student("username1", "password1"));
            students.add(new Student("username2", "password2"));

            new LoginFrame(students).setVisible(true);
        });
    }
}
