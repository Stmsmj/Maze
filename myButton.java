package com.mycompany.mazewelcome;
import java.awt.Color;
import javax.swing.*;

public class myButton extends JButton
{
	myButton(String name,int x,int y,int Width,int Height,Color Color)
	{
		this.setText(name);
		this.setBounds(x, y, Width, Height);
		this.setLocation(x, y);
		this.setBackground(Color);
		this.setHorizontalTextPosition(JButton.CENTER);
		this.setVerticalTextPosition(JButton.CENTER);
		this.setFocusable(false);
	}
}
