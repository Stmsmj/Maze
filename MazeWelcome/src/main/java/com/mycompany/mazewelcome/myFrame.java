package com.mycompany.mazewelcome;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class myFrame extends JFrame implements KeyListener
{
	public myPanel panel;
	private static int code;

	public final int Window_Width = 1000;
	public final int Window_Height = 600;

	// creating a frame and its configurations
	myFrame()
	{
		this.setTitle("Maze Project");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Window_Width, Window_Height);
		this.setResizable(false);
		addKeyListener(this);
		this.setFocusable(true);
		panel = new myPanel(Window_Width,Window_Height);
		this.add(panel);
		this.pack();
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		code=e.getKeyCode();
	}
	@Override
	public void keyReleased(KeyEvent e)
	{	
		code=1;
	}

	public static int getCode()
	{
		return code; 	
	}

}
