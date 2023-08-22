package pages;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResultPage extends JFrame {
    private String subject;

    public ResultPage(String subject, Map<Integer, String> userAnswers, String className) {
        this.subject = subject;
        setTitle(subject + " Exam Result");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new BorderLayout());

        JTextArea resultTextArea = new JTextArea();
        resultTextArea.setEditable(false);

        int totalQuestions = userAnswers.size();
        int correctAnswers = checkAnswers(subject, userAnswers);

        resultTextArea.append("Subject: " + subject + "\n");
        resultTextArea.append( className + "\n");
        resultTextArea.append("Total Questions: " + totalQuestions + "\n");
        resultTextArea.append("Correct Answers: " + correctAnswers + "\n");
        resultTextArea.append("Incorrect Answers: " + (totalQuestions - correctAnswers) + "\n");

        panel.add(resultTextArea, BorderLayout.CENTER);

        JButton backButton = new JButton("Back to Exam Selection");
        backButton.addActionListener(e -> {
            openSubjectPage(className);
            dispose();
        });
        panel.add(backButton, BorderLayout.SOUTH);

        add(panel);
    }

    private int checkAnswers(String subject, Map<Integer, String> userAnswers) {
        int correctAnswers = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(getQuestionAnswerFilePath(subject)))) {
            String line;
            int questionNumber = 1;
            while ((line = reader.readLine()) != null) {
                String correctAnswer = line.trim();

                String userAnswer = userAnswers.get(questionNumber);

                if (userAnswer != null && userAnswer.equalsIgnoreCase(correctAnswer)) {
                    correctAnswers++;
                }
                questionNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return correctAnswers;
    }

    private String getQuestionAnswerFilePath(String subject) {
        return System.getProperty("user.dir") + "/src/files/" + subject + " QuestionAnswer.txt";
    }

    private void openSubjectPage(String className) {
        SwingUtilities.invokeLater(() -> new SubjectPage(className).setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            String[] classes = {"Class 9", "Class 10", "Class 11", "Class 12", "Admission"};
            String[] subjects = {"Math", "Physics", "Chemistry", "Biology"};

            for (String className : classes) {
                for (String subject : subjects) {
                    String filePath = System.getProperty("user.dir") + "/src/files/" + subject + ".txt";
                    String questionFilePath = System.getProperty("user.dir") + "/src/files/" + subject + " Question.txt";
                    Map<Integer, String> userAnswers = readUserAnswersFromFile(subject);
                    new ResultPage(subject, userAnswers, className).setVisible(true);
                }
            }
        });
    }

    private static Map<Integer, String> readUserAnswersFromFile(String subject) {
        Map<Integer, String> userAnswers = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getUserAnswersFilePath(subject)))) {
            String line;
            int questionNumber = 1;
            while ((line = reader.readLine()) != null) {
                userAnswers.put(questionNumber, line.trim());
                questionNumber++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userAnswers;
    }

    private static String getUserAnswersFilePath(String subject) {
        return System.getProperty("user.dir") + "/src/files/" + subject + " Answers.txt";
    }
}
