package maze.h;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.*;

public class T {
    protected static T obj;
    private JLabel label;
    private Timer timer;
    public static int j =0;
    public static int i =0;
    class Helper extends TimerTask {

        public void run() {
            if (label != null) {
                label.setText("Timer ran " + j + " : "+i++);
            }
            // System.out.println("Timer ran " + j + " : "+i++);
            if(i == 60) {
                j++;
                i=0;
            }
        }
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }

    public void start() {
        i=0;
        j=0;
        timer = new Timer();
        Date date = new Date();
        TimerTask task = new Helper();
        timer.scheduleAtFixedRate(task, 1000, 1000);

    }

    public void pause() {
        if (timer != null) {
            timer.cancel();
        }
    }

    //public T() {
         //obj = new T();

         //synchronized(obj) {
             //obj.wait();
             //timer.cancel();
             //System.out.println(timer.purge());
         //}
    //}
}
