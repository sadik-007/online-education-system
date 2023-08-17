import models.Student;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> studentList = readStudentsFromFile(System.getProperty("user.dir") + "/src/files/UserName&Password.txt");

        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(studentList);
            loginFrame.setVisible(true);
        });
    }

    private static List<Student> readStudentsFromFile(String filePath) {
        List<Student> students = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            String username = null;
            String password = null;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                if (lineNumber % 2 == 0) {
                    username = line.trim();
                } else {
                    password = line.trim();
                    students.add(new Student(username, password));
                }
                lineNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return students;
    }
}
