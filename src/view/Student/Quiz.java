package view.Student;

import javax.swing.*;
import controller.DatabaseConnection;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quiz extends JFrame implements ActionListener {

    private ArrayList<String[]> questionsList = new ArrayList<>();
    private String answers[][] = new String[10][2];
    private String useranswers[][] = new String[10][1];
    private JLabel qno, question, timerLabel;
    private JRadioButton opt1, opt2, opt3, opt4;
    private ButtonGroup groupoptions;
    private JButton next, submit, lifeline;

    public static int totalTimer = 30; // 15 minutes in seconds
    public static int ans_given = 0;
    public static int count = 0;
    public static double score = 0;

    private Connection connection;
    private ResultSet questionsResultSet;
    private String classCode;  // Mã lớp học

    public Quiz(String classCode) {
        this.classCode = classCode;
        try {
            connection = DatabaseConnection.connectToBB();

            String sql = "SELECT * FROM questions WHERE classCode = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, classCode);
            questionsResultSet = preparedStatement.executeQuery();

            while (questionsResultSet.next()) {
                String[] questionData = {
                        questionsResultSet.getString("question_text"),
                        questionsResultSet.getString("option1"),
                        questionsResultSet.getString("option2"),
                        questionsResultSet.getString("option3"),
                        questionsResultSet.getString("option4"),
                        questionsResultSet.getString("correct_option")
                };
                questionsList.add(questionData);
            }

            answers = new String[questionsList.size()][2];
            useranswers = new String[questionsList.size()][1];

        } catch (SQLException e) {
            e.printStackTrace();
        }

        setBounds(50, 0, 1440, 850);
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);

        setExtendedState(JFrame.MAXIMIZED_BOTH);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Assert/student/Examination/quiz.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 1440, 392);
        getContentPane().add(image);

        qno = new JLabel();
        qno.setBounds(100, 450, 50, 30);
        qno.setFont(new Font("Tahoma", Font.PLAIN, 24));
        getContentPane().add(qno);

        question = new JLabel();
        question.setBounds(150, 450, 900, 30);
        question.setFont(new Font("Tahoma", Font.PLAIN, 24));
        getContentPane().add(question);

        opt1 = new JRadioButton();
        opt1.setBounds(170, 520, 700, 30);
        opt1.setBackground(Color.WHITE);
        opt1.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt1);

        opt2 = new JRadioButton();
        opt2.setBounds(170, 560, 700, 30);
        opt2.setBackground(Color.WHITE);
        opt2.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt2);

        opt3 = new JRadioButton();
        opt3.setBounds(170, 600, 700, 30);
        opt3.setBackground(Color.WHITE);
        opt3.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt3);

        opt4 = new JRadioButton();
        opt4.setBounds(170, 640, 700, 30);
        opt4.setBackground(Color.WHITE);
        opt4.setFont(new Font("Dialog", Font.PLAIN, 20));
        getContentPane().add(opt4);

        groupoptions = new ButtonGroup();
        groupoptions.add(opt1);
        groupoptions.add(opt2);
        groupoptions.add(opt3);
        groupoptions.add(opt4);

        next = new JButton("Next");
        next.setBounds(1115, 498, 200, 40);
        next.setFont(new Font("Tahoma", Font.PLAIN, 22));
        next.setBackground(new Color(30, 144, 255));
        next.setForeground(Color.WHITE);
        next.addActionListener(this);
        getContentPane().add(next);

        lifeline = new JButton("50-50 Lifeline");
        lifeline.setBounds(1115, 581, 200, 40);
        lifeline.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lifeline.setBackground(new Color(30, 144, 255));
        lifeline.setForeground(Color.WHITE);
        lifeline.addActionListener(this);

        getContentPane().add(lifeline);

        submit = new JButton("Submit");
        submit.setBounds(1115, 661, 200, 40);
        submit.setFont(new Font("Tahoma", Font.PLAIN, 22));
        submit.setBackground(new Color(30, 144, 255));
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        submit.setEnabled(false);
        getContentPane().add(submit);

        timerLabel = new JLabel();
        timerLabel.setBounds(1110, 423, 250, 30);
        timerLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
        timerLabel.setForeground(Color.RED);
        getContentPane().add(timerLabel);

        start(count);

        setVisible(true);
        startTimer();
    }

    private void startTimer() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (totalTimer > 0) {
                    totalTimer--;
                    updateTimerLabel();
                } else {
                    // Time's up, end the quiz
                    timerLabel.setText("Time's up!!");
                    disableOptions();
                    submit.setEnabled(true);
                }
            }
        });
        timer.start();
    }

    private void updateTimerLabel() {
        int minutes = totalTimer / 60;
        int seconds = totalTimer % 60;
        timerLabel.setText("Time left - " + String.format("%02d:%02d", minutes, seconds));

        if (totalTimer <= 0) {
            timerLabel.setText("Time's up!!");
            autoSubmit();
        }
    }

    private void autoSubmit() {
        if (ans_given == 0) {
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            for (int i = 0; i < useranswers.length; i++) {
                if (useranswers[i][0].equals(answers[i][1])) {
                    score += 10.0 / questionsList.size();
                } else {
                    score += 0;
                }
            }

            ans_given = 0;
            totalTimer = 0; // Stop the timer
            setVisible(false);
            new Score(score);
            score = 0;
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == next) {
            repaint();
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);

            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            if (count == questionsList.size() - 2) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }

            count++;
            start(count);
        } else if (ae.getSource() == lifeline) {
            if (count < questionsList.size()) {
                String correctAnswer = answers[count][1];

                if (opt1.getText().equals(correctAnswer) || opt3.getText().equals(correctAnswer)) {
                    opt2.setEnabled(false);
                    opt4.setEnabled(false);
                } else if (opt2.getText().equals(correctAnswer) || opt4.getText().equals(correctAnswer)) {
                    opt1.setEnabled(false);
                    opt3.setEnabled(false);
                }
                lifeline.setEnabled(false);
            }
        } else if (ae.getSource() == submit) {
            ans_given = 1;
            if (groupoptions.getSelection() == null) {
                useranswers[count][0] = "";
            } else {
                useranswers[count][0] = groupoptions.getSelection().getActionCommand();
            }

            for (int i = 0; i < useranswers.length; i++) {
                if (useranswers[i][0].equals(answers[i][1])) {
                    score += 10.0 / questionsList.size();
                } else {
                    score += 0;
                }
            }
            ans_given = 0;
            totalTimer = 0; // Stop the timer
            setVisible(false);
            new Score(score);
            score = 0;
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (totalTimer <= 0) {
            timerLabel.setText("Time's up!!");
            disableOptions();
        }
    }

    private void disableOptions() {
        opt1.setEnabled(false);
        opt2.setEnabled(false);
        opt3.setEnabled(false);
        opt4.setEnabled(false);
    }

    private void start(int questionIndex) {
        if (questionIndex < questionsList.size()) {
            String[] questionData = questionsList.get(questionIndex);
            qno.setText("" + (questionIndex + 1) + ". ");
            question.setText(questionData[0]);
            opt1.setText(questionData[1]);
            opt1.setActionCommand(questionData[1]);
            opt2.setText(questionData[2]);
            opt2.setActionCommand(questionData[2]);
            opt3.setText(questionData[3]);
            opt3.setActionCommand(questionData[3]);
            opt4.setText(questionData[4]);
            opt4.setActionCommand(questionData[4]);
            answers[questionIndex][1] = questionData[5];
            groupoptions.clearSelection();
            ans_given = 0;
        }
    }

    public static void main(String[] args) {
        new Quiz("C104");
    }
}

