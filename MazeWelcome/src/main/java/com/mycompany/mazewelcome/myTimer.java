package com.mycompany.mazewelcome;

//import needed modules
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

//creating the myTimer class
public class myTimer 
{
    //initializing variables
    protected static myTimer obj;
    private JLabel label;
    private Timer timer;
    private Helper task;
    static int minute = 0;
    static int second = 0;

    //Helper is a timer task class that we use that 
    //in our timer object later
    class Helper extends TimerTask 
    {

        //this func sets label and adds minute
        //when the time reaches 60 seconds
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

    //this method sets label for our timer
    //this method used in myPanel line 85
    public void setLabel(JLabel label) 
    {
        this.label = label;
    }

    //this method resets the timer 
    //and start it again
    public void start() 
    {
        second=0;
        minute=0;
        timer = new Timer();

        TimerTask task = new Helper();
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }

    //this method cancels the timer
    //used in myPanel line 256
    public void pause() 
    {
        if (timer != null) 
        {
            timer.cancel();
        }
    }

    //this methode resets the timer
    //used in myPanel line 621
    public void reset(){

        pause();
        second = 0;
        minute = 0;
        timer = new Timer();
        task = new Helper();
        timer.schedule(task, 0, 10000000);

    }
}
