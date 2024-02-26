package com.mycompany.mazewelcome;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class myTimer 
{
    protected static myTimer obj;
    private JLabel label;
    private Timer timer;
    private Helper task;
    static int minute = 0;
    static int second = 0;
    class Helper extends TimerTask 
    {

        public void run() 
        {
            if (label != null) 
            {
                label.setText("Timer " + minute + " : "+second++);
            }
            if(second == 60) 
            {
                minute++;
                second=0;
            }
        }
    }

    public void setLabel(JLabel label) 
    {
        this.label = label;
    }

    public void start() 
    {
        second=0;
        minute=0;
        timer = new Timer();

        TimerTask task = new Helper();
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }

    public void pause() 
    {
        if (timer != null) 
        {
            timer.cancel();
        }
    }

    public void reset(){

        second = 0;
        minute = 0;
        timer = new Timer();
        task = new Helper();
        timer.schedule(task, 0, 9999999);

    }
}
