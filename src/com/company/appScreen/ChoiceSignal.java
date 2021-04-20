package com.company.appScreen;

import com.company.messageScreen.MessageScreen;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ChoiceSignal extends JPanel {
    private Box choice;
    private JPanel left;
    private JPanel right;
    private JPanel south;
    private ButtonGroup choiceRadioGroup;
    private JRadioButton simpleSignal;
    private JRadioButton soundSignal;
    private JLabel songFileName;
    private JCheckBox activeTimer;
    private JFileChooser fileChooser;
    private String fileName;
    private JSpinnerTimerPanel spinnerTime;
    private ClockPanel panelClock;
    private Clip clip;
    private MessageScreen messageScreen;
    private ReminderScreen reminderScreen;


    public void playSong(final String fileName) {
        new Thread() {
            public void run() {
                try {
                    int start = fileName.lastIndexOf("\\") + 1;
                    int end = fileName.length();
                    char buf[] = new char[end - start];
                    fileName.getChars(start, end, buf, 0);
                    String song = new String(buf);

                    buf = new char[fileName.length() - song.length()];
                    fileName.getChars(0, buf.length, buf, 0);
                    String dirTemp = new String(buf);
                    String dir = dirTemp.replace("\\", "\\\\");

                    activeTimer.setEnabled(false);
                    File soundFile = new File(dir, song);
                    AudioInputStream source = AudioSystem.getAudioInputStream(soundFile);
                    DataLine.Info clipInfo = new DataLine.Info(Clip.class, source.getFormat());
                    if (AudioSystem.isLineSupported(clipInfo)) {
                        clip = (Clip) AudioSystem.getLine(clipInfo);
                        clip.open(source);
                        clip.loop(0);
                        int waitTime = (int) Math.ceil(clip.getMicrosecondLength() / 1000.0);
                        Thread.sleep(waitTime);
                    }
                    activeTimer.setSelected(false);
                    activeTimer.setEnabled(true);
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }.start();
    }


    public ChoiceSignal(ReminderScreen reminderScreen) throws IOException {
        this.reminderScreen = reminderScreen;
        setLayout(new BorderLayout());
        setBackground(Color.white);
        setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.decode("#CBC9C9"), 1),
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        left = new JPanel();
        left.setBackground(Color.white);
        left.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.decode("#CBC9C9"), 1),
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        choice = Box.createVerticalBox();
        choiceRadioGroup = new ButtonGroup();
        simpleSignal = new JRadioButton("Sound signal");
        simpleSignal.setSelected(true);
        simpleSignal.setBackground(Color.white);
        simpleSignal.setFocusable(false);
        soundSignal = new JRadioButton("Play file");
        soundSignal.setBackground(Color.white);
        soundSignal.setFocusable(false);

        choiceRadioGroup.add(soundSignal);
        choiceRadioGroup.add(simpleSignal);
        choice.add(simpleSignal);
        choice.add(soundSignal);
        left.add(choice);

        south = new JPanel();
        south.setLayout(new BorderLayout());
        south.setBackground(Color.white);
        songFileName = new JLabel();
        songFileName.setText("");
        songFileName.setFont(new Font("Arial", Font.BOLD, 12));
        songFileName.setBackground(Color.white);
        south.add(songFileName);

        right = new JPanel();
        right.setLayout(null);
        right.setBackground(Color.white);
        right.setBounds(500, 500, 200, 200);
        right.setBorder(new CompoundBorder(BorderFactory.createLineBorder(Color.decode("#CBC9C9"), 1),
                BorderFactory.createBevelBorder(BevelBorder.RAISED)));
        activeTimer = new JCheckBox("Activate");
        activeTimer.setBackground(Color.white);
        activeTimer.setFocusable(false);
        activeTimer.setBounds(25, 5, 150, 30);
        right.add(activeTimer);

        spinnerTime = new JSpinnerTimerPanel();
        panelClock = new ClockPanel(80);

        activeTimer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        for (; ; ) {
                            try {
                                if (activeTimer.isSelected()) {
                                    String currentTime = panelClock.twentyFourthHourFormat();
                                    String signalTime = spinnerTime.getTime();
                                    reminderScreen.dispose();
                                    if (soundSignal.isSelected()) {
                                        if (signalTime.equals(currentTime)) {
                                            playSong(fileName);
                                            messageScreen = new MessageScreen(reminderScreen);
                                            break;
                                        }
                                    } else {
                                        if (signalTime.equals(currentTime)) {
                                            activeTimer.setEnabled(false);
                                            messageScreen = new MessageScreen(reminderScreen);
                                            for (int i = 0; i < 10; i++) {
                                                Toolkit.getDefaultToolkit().beep();
                                                Thread.sleep(300);
                                            }
                                            activeTimer.setSelected(false);
                                            activeTimer.setEnabled(true);
                                            break;
                                        }
                                    }
                                }
                                Thread.sleep(500);
                            } catch (InterruptedException | FileNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }.start();
            }
        });

        fileChooser = new JFileChooser();

        fileChooser.setCurrentDirectory(new File("."));
        soundSignal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int r = fileChooser.showOpenDialog(null);
                if (r == JFileChooser.APPROVE_OPTION) {
                    fileName = fileChooser.getSelectedFile().getPath();
                    String song = fileName.toLowerCase();
                    if (song.endsWith(".au") || song.endsWith(".aif") || song.endsWith(".wav")) {
                        fileName = fileChooser.getSelectedFile().getPath();
                        songFileName.setText(fileName);
                    } else {
                        songFileName.setText("");
                        Toolkit.getDefaultToolkit().beep();
                        simpleSignal.setSelected(true);
                        JOptionPane.showMessageDialog(null,
                                "   Выберите аудиофайл \nформата .au .aif или .wav",
                                "MessageFormatInformation", JOptionPane.INFORMATION_MESSAGE, null);
                    }
                } else if (r == JFileChooser.CANCEL_OPTION) {
                    songFileName.setText("");
                    simpleSignal.setSelected(true);
                }
            }
        });

        simpleSignal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                songFileName.setText("");
            }
        });

        add(left, BorderLayout.WEST);
        add(south, BorderLayout.SOUTH);
        add(right, BorderLayout.CENTER);

        setVisible(true);

    }
    public JCheckBox getActiveTimer() {
            return activeTimer;
    }
}





