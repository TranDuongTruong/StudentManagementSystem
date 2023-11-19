package view.Student;


import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Rules extends JFrame implements ActionListener{

    JButton start, back;
    private String classCode;  // Mã lớp học
    
    Rules(String classCode) {
    	this.classCode = classCode;
        getContentPane().setBackground(Color.WHITE);
        getContentPane().setLayout(null);
        
        JLabel heading = new JLabel("Welcome " + "Students " + " to Quiz Online");
        heading.setBounds(50, 20, 700, 30);
        heading.setFont(new Font("Viner Hand ITC", Font.BOLD, 28));
        heading.setForeground(new Color(30, 144, 254));
        getContentPane().add(heading);
        
        JLabel rules = new JLabel();
        rules.setBounds(20, 90, 700, 420);
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
        	    "<html>" +
        	        "1. **Preparation**: Ensure a stable computer and internet connection. Install required testing software." + "<br><br>" +
        	        "2. **Prepare Materials**: Use only allowed materials; no textbooks or notes." + "<br><br>" +
        	        "3. **Maintain Integrity**: Use only your knowledge; avoid copying or referencing others' work." + "<br><br>" +
        	        "4. **Time Management**: Each question has 15 seconds; answer quickly." + "<br><br>" +
        	        "5. **Use the 'Next' Button**: After answering, click 'Next' to proceed." + "<br><br>" +
        	        "6. **Avoid Distractions**: Stay focused, don't open unrelated apps/websites." + "<br><br>" +
        	        "7. **Follow Question Order**: Answer in order, easier to harder. Submit after all questions." + "<br><br>" +
        	        "8. **Careful Answering**: Read questions carefully, check for errors." + "<br><br>" +
        	        "9. **Submit on Time**: Submit after all questions." + "<br><br>" +
        	        "Good luck, follow the rules for a fair test-taking experience." + "<br><br>" +
        	    "<html>"
        	);


        getContentPane().add(rules);
        
        back = new JButton("Back");
        back.setBounds(434, 535, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.WHITE);
        back.addActionListener(this);
        getContentPane().add(back);
        
        start = new JButton("Start");
        start.setBounds(153, 535, 100, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.WHITE);
        start.addActionListener(this);
        getContentPane().add(start);
        
        setSize(800, 650);
        setLocation(350, 100);
        setVisible(true);
//      JScrollPane scrollPane = new JScrollPane((Component) null);
//        scrollPane.setBounds(0, 88, 738, 369);
//        getContentPane().add(scrollPane);
//        
//        scrollPane.setViewportView(rules);

                
    }

        
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(classCode);
            
        } 
        else {
        	StudentAccountMainView studentAccountMainView = new StudentAccountMainView();
        	studentAccountMainView.setVisible(true);
        	setVisible(false);
        }
    }
    
    public static void main(String[] args) {
        new Rules("C105");
    }
}