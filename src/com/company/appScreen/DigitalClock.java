package com.company.appScreen;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;

class DigitalClock extends JPanel {
    private JLabel labelDigitTime;
    private JRadioButton rbTwelveHourFormat;
    private JRadioButton rbTwentyFourthHourFormat;
    private ButtonGroup choiceFormatRadioGroup;
    private String time;
    private ClockPanel panelClock;
    public DigitalClock(){
        setBackground(Color.WHITE);
       // setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
              //  BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        labelDigitTime = new JLabel();
        choiceFormatRadioGroup = new ButtonGroup();
        rbTwelveHourFormat = new JRadioButton("12 hour format    ");
        choiceFormatRadioGroup.add(rbTwelveHourFormat);
        rbTwelveHourFormat.setBackground(Color.white);
        rbTwelveHourFormat.setFocusable(false);
        rbTwentyFourthHourFormat = new JRadioButton("24 hour format    ");
        rbTwentyFourthHourFormat.setSelected(true);
        choiceFormatRadioGroup.add(rbTwentyFourthHourFormat);
        rbTwentyFourthHourFormat.setBackground(Color.white);
        rbTwentyFourthHourFormat.setFocusable(false);

       new Thread(){
            public void run(){
                for(;;){
                    try{
                        repaint();
                        panelClock = new ClockPanel(80);
                        if(rbTwelveHourFormat.isSelected()){
                            time = panelClock.twelveHourFormat();
                        }
                        else{
                            time = panelClock.twentyFourthHourFormat();
                        }
                        labelDigitTime.setText(time);
                        Thread.sleep(500);
                    }catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
        }.start();
        labelDigitTime.setFont(new Font("Arial", Font.BOLD, 40));
        labelDigitTime.setForeground(Color.decode("#00A1F1"));
        add(labelDigitTime);
        add(rbTwelveHourFormat);
        add(rbTwentyFourthHourFormat);
    }
}
