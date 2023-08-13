import models.Student;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Sadik", "1234");
        Student s2 = new Student("Khaled_321", "kmp456@");
        List<Student> studentList=new ArrayList<Student>();
        studentList.add(s1);
        studentList.add(s2);
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame(studentList);
            loginFrame.setVisible(true);
        });
    }
}
