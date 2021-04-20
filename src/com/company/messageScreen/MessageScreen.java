package com.company.messageScreen;

import com.company.appScreen.Reminder;
import com.company.appScreen.ReminderScreen;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class MessageScreen extends JFrame {

    private ReminderScreen reminderScreen;
    private JLabel messageLb;


    public MessageScreen(ReminderScreen reminderScreen) throws FileNotFoundException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1040,650);
        setLocationRelativeTo(null);
        setUndecorated(false);
        JDialog dialog = new JDialog(this, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setSize(1040,650);
        dialog.setLocationRelativeTo(null);

        FileReader reader = new FileReader("JavaAlarmClock.bat");

        Reminder reminder = new Reminder();
        Scanner scan  = new Scanner(reader);

        while (scan.hasNextLine()){
            reminder.setMessage(scan.nextLine());
            reminder.setTime(scan.nextLine());
        }

        messageLb = new JLabel(reminder.getMessage());

        dialog.add(messageLb);

        dialog.setVisible(true);
    }

}
