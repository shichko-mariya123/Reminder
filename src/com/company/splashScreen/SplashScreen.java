package com.company.splashScreen;

import com.company.appScreen.ChoiceSignal;
import com.company.appScreen.ReminderScreen;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class SplashScreen extends JFrame {

    private JLabel bntuLb = new JLabel("Belarusian National Technical University");
    private JLabel facultyLb = new JLabel("Faculty of Information Technology and Robotics");
    private JLabel departmentLb = new JLabel("Department of Software" +
            "information systems and technologies");
    private JLabel courseWorkLb = new JLabel("Course work");
    private JLabel disciplineLb = new JLabel("in the discipline \"Java Programming\"");
    private JLabel nameLb = new JLabel("Reminder");
    Font  f1  = new Font(Font.DIALOG, Font.BOLD,  30);
    private ReminderScreen reminderScreen;
    private ChoiceSignal choiceSignal;

    public SplashScreen() {
        super("Reminder");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1040,650);
        setLocationRelativeTo(null);
        setUndecorated(false);

        Container container = this.getContentPane();
        container.setBackground(new Color (255,255, 255));

        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;

        bntuLb.setFont(new Font("", Font.PLAIN,20));
        bntuLb.setForeground(Color.BLACK);
        bntuLb.setHorizontalAlignment(SwingConstants.CENTER);

        facultyLb.setFont(new Font("", Font.PLAIN,20));
        facultyLb.setForeground(Color.BLACK);
        facultyLb.setHorizontalAlignment(SwingConstants.CENTER);

        departmentLb.setFont(new Font("", Font.PLAIN,20));
        departmentLb.setForeground(Color.BLACK);
        departmentLb.setHorizontalAlignment(SwingConstants.CENTER);

        courseWorkLb.setFont(new Font("", Font.PLAIN,20));
        courseWorkLb.setForeground(Color.BLACK);
        courseWorkLb.setHorizontalAlignment(SwingConstants.CENTER);

        disciplineLb.setFont(new Font("", Font.PLAIN,20));
        disciplineLb.setForeground(Color.BLACK);
        disciplineLb.setHorizontalAlignment(SwingConstants.CENTER);

        nameLb.setFont(new Font("", Font.BOLD,30));
        nameLb.setForeground(Color.BLACK);
        nameLb.setHorizontalAlignment(SwingConstants.CENTER);

        ImageIcon icon = new ImageIcon("src\\com\\company\\images\\splashScreenIcon1.jpg");
        JLabel labelIcon = new JLabel(icon);
        labelIcon.setForeground(Color.WHITE);

        JLabel labelAuthor = new JLabel("<html>completed by: student group 10702418" +
                "<br>Mariya Shichko<br><br>" +
                "supervisor: Valery Sidorik</html>");
        labelAuthor.setFont(new Font("", Font.PLAIN,20));
        labelAuthor.setForeground(Color.BLACK);
        labelAuthor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelNow = new JLabel("Minsk 2021");
        labelNow.setFont(new Font("", Font.PLAIN,20));
        labelNow.setForeground(Color.BLACK);
        labelNow.setHorizontalAlignment(SwingConstants.CENTER);

        JButton startButton = initializeStartButton();
        startButton.setForeground(Color.WHITE);
        startButton.setFont(f1);
        startButton.setBackground(Color.decode("#039be5"));
        startButton.setSize(20,100);


        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets    = new Insets(0, 0, 15, 0);
        add(bntuLb, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets    = new Insets(0, 0, 0, 0);
        add(facultyLb, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets    = new Insets(0, 0, 40, 0);
        add(departmentLb, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets    = new Insets(0, 0, 10, 0);
        add(courseWorkLb, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets    = new Insets(0, 0, 15, 0);
        add(disciplineLb, constraints);
        constraints.insets    = new Insets(0, 0, 0, 0);
        add(nameLb, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;


        constraints.gridwidth = 1;
        constraints.gridheight = 2;
        constraints.weightx = 1;
        constraints.insets    = new Insets(0, 80, 0, 0);
        add(labelIcon, constraints);

        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.gridheight = 1;
        constraints.gridy = 6;
        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.insets    = new Insets(30, 0, 0, 0);
        add(labelAuthor, constraints);
        constraints.gridwidth = GridBagConstraints.REMAINDER;
        constraints.insets    = new Insets(0, 0, 10, 0);
        constraints.gridy = 9;
        constraints.gridx = 0;
        add(labelNow, constraints);


        constraints.gridwidth = 2;
        constraints.insets    = new Insets(-10, 700, -10, 100);
        add(startButton, constraints);

    }
    private JButton initializeStartButton() {
        JButton startButton = new JButton("Start");
        startButton.addActionListener(e -> {
            this.dispose();
            try {
                reminderScreen = new ReminderScreen();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                reminderScreen.javaStart();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        return startButton;
    }

}
