import javax.swing.*;
public class myFrame extends JFrame{
	//public myPanel panel;

	public final int Window_Width = 1000;
	public final int Window_Height = 600;
	myFrame(){
		
		this.setTitle("Maze Project");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(Window_Width, Window_Height);
		this.setResizable(false);
		//panel = new myPanel(Window_Width,Window_Height);
		//this.add(panel);
		this.pack();
		this.setLayout(null);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	
	}

}
class a{
	public static void main(String[] args) {
		new myFrame();
	}
}