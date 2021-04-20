package com.company.appScreen;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class JSpinnerTimerPanel extends JPanel {
    private String point = "1";
    private JSpinner spinner;
    private static JTextArea text1;
    private static JTextArea text2;
    private static JTextArea text3;
    private JTextArea firstPoint;
    private JTextArea secondPoint;

    public String getTime() {
        return text1.getText() + ":" + text2.getText() + ":" + text3.getText();
    }

    public JSpinnerTimerPanel() {
        setLayout(null);
        setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.decode("#CBC9C9"), 1),
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        text1 = new JTextArea();
        text1.setEditable(false);
        text1.setBackground(Color.decode("#EEE7E7"));
        text1.setForeground(Color.decode("#00A1F1"));
        text1.setText("00");
        text1.setFont(new Font("Arial", Font.BOLD, 24));
        text1.setBounds(2, 2, 32, 34);
        add(text1);

        firstPoint = new JTextArea(":");
        firstPoint.setBackground(Color.decode("#EEE7E7"));
        firstPoint.setForeground(Color.decode("#00A1F1"));
        firstPoint.setFont(new Font("Arial", Font.BOLD, 24));
        firstPoint.setEditable(false);
        firstPoint.setBounds(34, 2, 10, 34);
        add(firstPoint);

        text2 = new JTextArea();
        text2.setEditable(false);
        text2.setBackground(Color.decode("#EEE7E7"));
        text2.setForeground(Color.decode("#00A1F1"));
        text2.setText("00");
        text2.setFont(new Font("Arial", Font.BOLD, 24));
        text2.setBounds(44, 2, 30, 34);
        add(text2);

        secondPoint = new JTextArea(":");
        secondPoint.setBackground(Color.decode("#EEE7E7"));
        secondPoint.setForeground(Color.decode("#00A1F1"));
        secondPoint.setFont(new Font("Arial", Font.BOLD, 24));
        secondPoint.setEditable(false);
        secondPoint.setBounds(74, 2, 10, 34);
        add(secondPoint);

        text3 = new JTextArea();
        text3.setEditable(false);
        text3.setBackground(Color.decode("#EEE7E7"));
        text3.setForeground(Color.decode("#00A1F1"));
        text3.setText("00");
        text3.setFont(new Font("Arial", Font.BOLD, 24));
        text3.setBounds(84, 2, 30, 34);
        add(text3);

        spinner = new JSpinner();
//----------------------------------------------------------------
        final String[] hours = new String[26];
        for (int i = 0, j = -1; i < hours.length; i++, j++) {
            if (i == -1) {
                hours[i] = "" + i;
            }
            if (j >= 0 && j < 10) {
                hours[i] = "0" + j;
            } else {
                hours[i] = "" + j;
            }
        }
        final SpinnerListModel model1 = new SpinnerListModel(hours) {
            public Object getNextValue() {
                if (super.getNextValue().toString().equals("24")) {
                    spinner.setValue(hours[1]);
                    text1.setText(super.getValue().toString());
                    return super.getValue();
                }
                if (point.equals("1")) {
                    text1.setText(super.getNextValue().toString());
                }
                return super.getNextValue();
            }

            public Object getPreviousValue() {
                if (super.getPreviousValue().toString().equals("-1")) {
                    spinner.setValue(hours[24]);
                    text1.setText(super.getValue().toString());
                    return super.getValue();
                }
                if (point.equals("1")) {
                    text1.setText(super.getPreviousValue().toString());
                }
                return super.getPreviousValue();
            }
        };
        spinner.setModel(model1);
        spinner.setValue(hours[1]);
        spinner.setBounds(114, 1, 17, 35);
        add(spinner);
//----------------------------------------------------------------
        final String[] minutesAndSeconds = new String[62];
        for (int i = 0, j = -1; i < minutesAndSeconds.length; i++, j++) {
            if (i == -1) {
                minutesAndSeconds[i] = "" + i;
            }
            if (j >= 0 && j < 10) {
                minutesAndSeconds[i] = "0" + j;
            } else {
                minutesAndSeconds[i] = "" + j;
            }
        }
        final SpinnerListModel model2 = new SpinnerListModel(minutesAndSeconds) {
            public Object getNextValue() {
                if (super.getNextValue().toString().equals("60")) {
                    spinner.setValue(minutesAndSeconds[1]);
                    if (point.equals("2")) {
                        text2.setText(super.getValue().toString());
                    } else if (point.equals("3")) {
                        text3.setText(super.getValue().toString());
                    }
                    return super.getValue();
                }
                if (point.equals("2")) {
                    text2.setText(super.getNextValue().toString());
                } else if (point.equals("3")) {
                    text3.setText(super.getNextValue().toString());
                }
                return super.getNextValue();
            }

            public Object getPreviousValue() {
                if (super.getPreviousValue().toString().equals("-1")) {
                    spinner.setValue(minutesAndSeconds[60]);
                    if (point.equals("2")) {
                        text2.setText(super.getValue().toString());
                    } else if (point.equals("3")) {
                        text3.setText(super.getValue().toString());
                    }
                    return super.getValue();
                }
                if (point.equals("2")) {
                    text2.setText(super.getPreviousValue().toString());
                } else if (point.equals("3")) {
                    text3.setText(super.getPreviousValue().toString());
                }
                return super.getPreviousValue();
            }
        };
//----------------------------------------------------------------
        text1.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                spinner.setModel(model1);
                spinner.setValue(hours[1]);
                text1.setText("00");
                point = "1";
            }

            public void focusLost(FocusEvent e) {
            }
        });
        text2.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                spinner.setModel(model2);
                spinner.setValue(minutesAndSeconds[1]);
                text2.setText("00");
                point = "2";
            }

            public void focusLost(FocusEvent e) {
            }
        });
        text3.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                spinner.setModel(model2);
                spinner.setValue(minutesAndSeconds[1]);
                text3.setText("00");
                point = "3";
            }

            public void focusLost(FocusEvent e) {
            }
        });
    }
}
