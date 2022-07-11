
import javax.swing.*;
import java.awt.event.*;

public class myFrame extends JFrame implements KeyListener {
    public myPanel panel;
    private static int code;
    public final int Window_Width = 1400;
    public final int Window_Height = 600;
    myFrame() {
        this.setTitle("Maze Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Window_Width, Window_Height);
        this.setResizable(false);
        panel = new myPanel(Window_Width, Window_Height);
        addKeyListener(this);
        this.setFocusable(true);
        this.add(panel);
        this.pack();
        this.setLayout(null);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
    @Override
    public void keyTyped(KeyEvent e){}
    @Override
    public void keyPressed(KeyEvent e){
        code=e.getKeyCode();
    }
    @Override
    public void keyReleased(KeyEvent e){
        code=1;
    }

    public static int getCode(){
        return code;
    }
}

