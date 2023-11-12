package view.Student;


import javax.swing.*;

import controller.DatabaseConnection;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Quiz extends JFrame implements ActionListener {
    
//    String questions[][] = new String[10][5];
    private ArrayList<String[]> questionsList = new ArrayList<>();
    String answers[][] = new String[10][2];
    String useranswers[][] = new String[10][1];
    JLabel qno, question;
    JRadioButton opt1, opt2, opt3, opt4;
    ButtonGroup groupoptions;
    JButton next, submit, lifeline;
    
    public static int timer = 15;
    public static int ans_given = 0;
    public static int count = 0;
    public static int score = 0;
    
    private Connection connection;
    private ResultSet questionsResultSet;
    
    Quiz() {
    	  // Khởi tạo kết nối đến CSDL
    	 try {
             connection = DatabaseConnection.connectToBB();
             String sql = "SELECT * FROM questions";
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
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
    	
        setExtendedState(JFrame.MAXIMIZED_BOTH);  // Thiet lập cửa sổ là full màn hình

        
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
        next.setBounds(1115, 501, 200, 40);
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
        
        start(count);
        
        setVisible(true);
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
                    score += 10;
                } else {
                    score += 0;
                }
            }
            setVisible(false);
            new Score(score);
        }
    }
    
    public void paint(Graphics g) {
        super.paint(g);
        
        String time = "Time left - " + timer + " seconds"; // 15
        g.setColor(Color.RED);
        g.setFont(new Font("Tahoma", Font.BOLD, 25));
        
        if (timer > 0) { 
            g.drawString(time, 1100, 500);
        } else {
            g.drawString("Times up!!", 1100, 500);
        }
        
        timer--; // 14
        
        try {
            Thread.sleep(1000);
            repaint();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if (ans_given == 1) {
            ans_given = 0;
            timer = 15;
        } else if (timer < 0) {
            timer = 15;
            opt1.setEnabled(true);
            opt2.setEnabled(true);
            opt3.setEnabled(true);
            opt4.setEnabled(true);
            
            if (count == questionsList.size() - 2) {
                next.setEnabled(false);
                submit.setEnabled(true);
            }
            if (count == questionsList.size() - 1 ) { // submit button
                if (groupoptions.getSelection() == null) {
                   useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();
                }
                
                for (int i = 0; i < useranswers.length; i++) {
                    if (useranswers[i][0].equals(answers[i][1])) {
                        score += 10;
                    } else {
                        score += 0;
                    }
                }
                setVisible(false);
                new Score(score);
            } else { // next button
                if (groupoptions.getSelection() == null) {
                   useranswers[count][0] = "";
                } else {
                    useranswers[count][0] = groupoptions.getSelection().getActionCommand();
                }
                count++; // 0 // 1
                start(count);
            }
        }
        
        
    }
    
    public void start(int questionIndex) {
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
        }
    }
    
    public static void main(String[] args) {
        new Quiz();
    }
}