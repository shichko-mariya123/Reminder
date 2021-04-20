package com.company.appScreen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;

public class ClockPanel extends JPanel implements ActionListener {
    private int radius;
    private int xh, yh, xm, ym, xs, ys;
    private int minutes, hours, seconds;
    private final float HOURS_WIDTH = 3.0F, MINUTES_WIDTH = 2.0F,
            SECONDS_WIDTH = 1.2F, CIRCLE_WIDTH = 3.0F, NUMBERS_WIDTH = 2.0F;
    private Color SECONDS_COLOR = new Color(15, 157, 88),
            MINUTES_COLOR = new Color(66, 133, 244),
            HOURS_COLOR = new Color(244, 180, 0),
            HOURS_POINTS_COLOR = new Color(15, 157, 88),
            CIRCLE_COLOR = new Color(255, 255, 255),
            NUMBERS_COLOR = new Color(0, 0, 0);
    private Point hoursPoint;
    private int minutesLength, secondsLength, hoursLength;
    private javax.swing.Timer timer;
    private String today;
    private Calendar calendar;
    private SimpleDateFormat formatter;
    private Formatter fmt;
    private Date currentDate;
    public ClockPanel(int radius){
        hoursLength = (int) ((double) radius * 0.6);
        minutesLength = (int) ((double) radius * 0.8);
        secondsLength = (int) ((double) radius * 0.9);
        hoursPoint = new Point();
        this.radius = radius;
    }
    public void startClock(){
        formatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss yyyy", Locale.getDefault());
        currentDate = new Date();

        formatter.applyPattern("s");
        try{
            seconds = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n){
            seconds = 25;
        }
        formatter.applyPattern("m");
        try{
            minutes = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n){
            minutes = 10;
        }
        formatter.applyPattern("h");
        try{
            hours = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n){
            hours = 21;
        }

        timer = new Timer(1000, this);
        timer.start();
    }
    public void stopClock(){
        timer.stop();
    }
    public void continueClock(){
        startClock();
    }
    public String getFullDate(){
       //fmt = new Formatter();
        calendar = Calendar.getInstance();
        return calendar.getTime().toString();
    }
    public String twelveHourFormat(){
        fmt = new Formatter();
        calendar = Calendar.getInstance();
        today = fmt.format("%tr",calendar).toString();
        return today;
    }
    public String twentyFourthHourFormat(){
        fmt = new Formatter();
        calendar = Calendar.getInstance();
        today = fmt.format("%tT",calendar).toString();
        return today;
    }
    public void actionPerformed(ActionEvent e){
        repaint();
        seconds++;
        if(seconds == 60){
            seconds = 0;
            minutes++;
            if(minutes == 60){
                minutes = 0;
                hours++;
            }
            if(hours == 13){
                hours = 1;
            }
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D) g;
        // use antialialising at visualisation for better quality
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Set position of the ends of the hands
        xs = (int) (Math.cos(seconds * Math.PI / 30 - Math.PI / 2) * secondsLength + radius);
        ys = (int) (Math.sin(seconds * Math.PI / 30 - Math.PI / 2) * secondsLength + radius);
        xm = (int) (Math.cos(minutes * Math.PI / 30 - Math.PI / 2) * minutesLength + radius);
        ym = (int) (Math.sin(minutes * Math.PI / 30 - Math.PI / 2) * minutesLength + radius);
        xh = (int) (Math.cos((hours*30 + minutes / 2) * Math.PI / 180 - Math.PI / 2) * hoursLength + radius);
        yh = (int) (Math.sin((hours*30 + minutes / 2) * Math.PI / 180 - Math.PI / 2) * hoursLength + radius);

        // draw circle of the clock
        g.setColor(CIRCLE_COLOR);
        g.fillOval(0, 0, radius * 2, radius * 2);
        g.setColor(NUMBERS_COLOR);
        g2D.setStroke(new BasicStroke(CIRCLE_WIDTH));
        g.drawOval(0, 0, radius * 2, radius * 2);

        // draw hands of clock
        g.setColor(SECONDS_COLOR);
        g2D.setStroke(new BasicStroke(SECONDS_WIDTH));
        g.drawLine(radius, radius, xs, ys);
        g.setColor(MINUTES_COLOR);
        g2D.setStroke(new BasicStroke(MINUTES_WIDTH));
        g.drawLine(radius, radius, xm, ym);
        g.setColor(HOURS_COLOR);

        g.drawLine(radius, radius, xh, yh);

        // draw numbers of circle
        g2D.setStroke(new BasicStroke(NUMBERS_WIDTH));
        g.setColor(NUMBERS_COLOR);
        g2D.setStroke(new BasicStroke(NUMBERS_WIDTH));
        g.drawString("12", radius-5, radius-65);
        g.drawString("1", 2 * radius - 45, radius / 3);
        g.drawString("2", 2 * radius - 20, radius * 2 / 3);
        g.drawString("3", 2 * radius - 10, radius + 5);
        g.drawString("4", 2 * radius - 20, radius + 42);
        g.drawString("5", 2 * radius - 45, radius + 65);
        g.drawString("6", radius - 3, 2 * radius - 5);
        g.drawString("7", radius / 2, radius + 65);
        g.drawString("8", radius / 3 - 10, radius + 42);
        g.drawString("9", radius - 75, radius + 5);
        g.drawString("11", radius / 2, radius / 3);
        g.drawString("10", radius / 3 - 12, radius * 2 / 3);

        // draw points of circle
        g.setColor(Color.BLACK);
        for (int i = 0; i < 12; i++){
            hoursPoint.x = radius-1+Math.round((float) (radius * Math.sin(Math.toRadians(30 * i))));
            hoursPoint.y = radius-1-Math.round((float) (radius * Math.cos(Math.toRadians(30 * i))));
            g2D.fill3DRect(hoursPoint.x, hoursPoint.y, 5, 5, true);
        }
        for (int i = 0; i <= 60; i++){
            hoursPoint.x = radius-1+Math.round((float) (77 * Math.sin(Math.toRadians(6 * i))));
            hoursPoint.y = radius-1-Math.round((float) (77 * Math.cos(Math.toRadians(6 * i))));
            g2D.fill3DRect(hoursPoint.x, hoursPoint.y, 2, 2, true);
        }
    }
}
