package com.company.appScreen;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.xml.soap.Text;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.io.*;

public class ReminderScreen extends JFrame {

    private JLabel welcomeLb = new JLabel("Welcome!");
    private JTextField textField = new JTextField();
    private ClockPanel panelClock;
    private DigitalClock digit;
    private ChoiceSignal panelChoiceSignal;
    private JLabel labelClock;
    private JSpinnerTimerPanel timerClock;

    private JButton aboutAuthorButton = new JButton("About author");
    private JButton helpButton = new JButton("Help");
    private JLabel createLb = new JLabel("Create reminder");
    private JLabel enterLb = new JLabel("Enter your message: ");
    private JLabel setTimeLb = new JLabel("Set time: ");
    private TextArea message = new TextArea("Wake up!");

    public void javaStart() throws IOException {
        String command = "java " + getClass().getName();
        BufferedReader buf = new BufferedReader(new StringReader(command));
        PrintWriter out = null;
        out = new PrintWriter(new BufferedWriter(new FileWriter("JavaAlarmClock.bat")));
        out.write(message.getText());
        out.write("\n");
        out.write(timerClock.getTime());
        String str;
        while ((str = buf.readLine()) != null) {
            out.println(command);
        }
        buf.close();
        out.close();
    }

    public ReminderScreen() throws IOException {
            this.setTitle("Reminder");
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setSize(1040,650);
            setLocationRelativeTo(null);
            setUndecorated(false);

            setLayout(null);
            Container content = getContentPane();
            content.setBackground(new Color (255,255, 255));

            panelClock = new ClockPanel(80);
            panelClock.startClock();
            panelClock.setBounds(50, 100, 300, 300);


            labelClock = new JLabel("" + panelClock.getFullDate());
            labelClock.setForeground(Color.BLACK);
            labelClock.setFont(new Font("", Font.PLAIN,24));
            labelClock.setBounds(600, 120, 350, 100);

            createLb.setBounds( 400, 50, 500, 100);
            createLb.setForeground(Color.BLACK);
            createLb.setFont(new Font("", Font.CENTER_BASELINE,30));

            enterLb.setFont(new Font("", Font.PLAIN,24));
            enterLb.setForeground(Color.BLACK);
            enterLb.setBounds( 140, 130, 300, 50);

            setTimeLb.setFont(new Font("", Font.PLAIN,24));
            setTimeLb.setForeground(Color.BLACK);
            setTimeLb.setBounds( 140, 350, 300, 50);

            message.setSize(200,200);
            message.setBounds(135, 190, 250, 150);
            message.setBackground(Color.decode("#EEE7E7"));
            message.setFont(new Font("", Font.PLAIN,24));

            panelChoiceSignal = new ChoiceSignal(this);
            panelChoiceSignal.setBounds(600, 350, 350, 85);


            timerClock = new JSpinnerTimerPanel();
            timerClock.setBounds(140, 400, 133, 38);

            digit = new DigitalClock();
            digit.setBounds(550, 190, 250, 150);

            aboutAuthorButton.setSize(30,10);
            aboutAuthorButton.setBackground(Color.decode("#00A1F1"));
            aboutAuthorButton.setForeground(Color.WHITE);
            aboutAuthorButton.setBorderPainted(false);
            aboutAuthorButton.setBounds(5, 15, 150,40);

            helpButton.setSize(30,10);
            helpButton.setBackground(Color.decode("#00A1F1"));
            helpButton.setForeground(Color.WHITE);
            helpButton.setBorderPainted(false);
            helpButton.setBounds(157, 15, 150,40);


            content.add(aboutAuthorButton);
            content.add(helpButton);
            content.add(labelClock);
            content.add(createLb);
            content.add(enterLb);
            content.add(message);
            content.add(setTimeLb);
            content.add(timerClock);
            content.add(digit);
            content.add(panelChoiceSignal);

            setVisible(true);

    }

}



