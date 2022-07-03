import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class myPanel extends JPanel implements ActionListener,ChangeListener{
	
	private final int Maze_Size = 600;
	private int Cell_Size = 40;
	private int Slowest_Delay = 110;
	private int delay = 10;
	private int Window_W,Window_H;
	private int running = -1;

	private int Switch_Case_Var = 0;
	
	private myMaze maze;
	private Timer tm;
	
	
	private JPanel Small_Panel;
	private myButton Start_Button;
	private myButton Reset_Button;
	private myButton reMaze_Button;
	
	private JLabel Cell_Label;
	private JComboBox DifficultyBox;
	
	private JLabel Speed_Label;
	private JSlider Speed_Slider;
	
	private JCheckBox Maze_CheckBox;
	
	private JCheckBox BFS_CheckBox;
	private JComboBox algoBox;
	private int mode ;
	private JLabel algoBox_Label;
	
	
	private myButton Start_Solving_Button;
	private boolean flag = true;


	
	
	myPanel(int Window_W,int Window_H){
		this.Window_W = Window_W;
		this.Window_H = Window_H;
		
		tm = new Timer(delay,this);
		
		this.setPreferredSize(new Dimension(Window_W,Window_H));
		this.setBounds(0, 0, Window_W, Window_H);
		this.setBackground(new Color(250,250,250));
		this.setLayout(null);
		
		
		
		Small_Panel = new JPanel();
		Small_Panel.setPreferredSize(new Dimension((Window_W-Maze_Size),Window_H));
		Small_Panel.setBounds(Maze_Size, 0, Window_W-Maze_Size, Window_H);
		Small_Panel.setLayout(null);
		this.add(Small_Panel);
		
		
		
		Start_Button = new myButton("Start",(int)(Small_Panel.getSize().width/2)-120-30, 30, 120, 50,new Color(102,80 ,153));
		Start_Button.setFont(new Font("MV Boli",Font.BOLD,15));
		Small_Panel.add(Start_Button);
		Start_Button.addActionListener(this);
		
		
		
		
		reMaze_Button = new myButton("Re-Maze",(int)(Small_Panel.getSize().width/2)+30, 30, 120, 50,Color.CYAN);
		reMaze_Button.setFont(new Font("MV Boli",Font.BOLD,15));
		Small_Panel.add(reMaze_Button);
		reMaze_Button.addActionListener(this);
		reMaze_Button.setEnabled(false);
		
		String[] Difficulties={"kids","medium","hard","legends","god mode"};
		DifficultyBox= new JComboBox<String>(Difficulties);
		DifficultyBox.setBounds((Window_W-Maze_Size)/2-(350/4), 150, 150, 50);
		DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
		DifficultyBox.setFocusable(false);
		DifficultyBox.addActionListener(this);
		
		
		
		

		Cell_Label = new JLabel();
		Cell_Label.setText("Cell's size adjustment: " + DifficultyBox.getSelectedItem());
		Cell_Label.setBounds(30, 130, 250, 20);
		Cell_Label.setFont(new Font("MV Boli",Font.BOLD,18));
		
		Small_Panel.add(Cell_Label);
		Small_Panel.add(DifficultyBox);
		
		
		
		Speed_Slider = new JSlider(1,5,5);
		Speed_Slider.setBounds((Window_W-Maze_Size)/2-(350/2), 230, 350, 40);
		Speed_Slider.setPaintTrack(true);
		Speed_Slider.setMajorTickSpacing(1);
		Speed_Slider.setSnapToTicks(true);
		Speed_Slider.setPaintLabels(true);
		Speed_Slider.addChangeListener(this);

		Speed_Label = new JLabel();
		Speed_Label.setText("Speed: " + Speed_Slider.getValue());
		Speed_Label.setBounds(30, 210, 180, 20);
		Speed_Label.setFont(new Font("MV Boli",Font.BOLD,18));
				
		Small_Panel.add(Speed_Label);
		Small_Panel.add(Speed_Slider);
		
		
		
		Maze_CheckBox = new JCheckBox();
		Maze_CheckBox.setBounds((int)(Small_Panel.getSize().width/2)-120-30, 95, 250, 20);
		Maze_CheckBox.setText("Generate instantly");
		Maze_CheckBox.setFont(new Font("MV Boli",Font.BOLD,15));
		Maze_CheckBox.setFocusable(false);
	
	
		Small_Panel.add(Maze_CheckBox);
		
		/*
		//Check box
		BFS_CheckBox = new JCheckBox();
		BFS_CheckBox.setBounds(Small_Panel.getSize().width/2-30, 285, 250, 30);
		BFS_CheckBox.setText("Breadth First Search");
		BFS_CheckBox.setFont(new Font("",Font.BOLD,15));
		BFS_CheckBox.setFocusable(false);
		Small_Panel.add(BFS_CheckBox);
		*/
		
		
		
		
		algoBox_Label = new JLabel("Pathfinding Algorithms");
		algoBox_Label.setBounds(Small_Panel.getSize().width/2-155, 370, 220, 30);
		algoBox_Label.setFont(new Font("MV Boli",Font.BOLD,15));
		Small_Panel.add(algoBox_Label);
		
		
		
		String[] algoList = {"Breadth First Search(BFS)","Depth First Search(DFS)"};// (0 == BFS , 1 = DFS)
		algoBox = new JComboBox(algoList);
		algoBox.setBounds(Small_Panel.getSize().width/2-160, 400, 220, 30);
		algoBox.setFont(new Font("MV Boli",Font.BOLD,15));
		algoBox.setFocusable(false);
		algoBox.addActionListener(this);
		mode = algoBox.getSelectedIndex(); // (0 == BFS , 1 = DFS)
		Small_Panel.add(algoBox);
		
		
	
		
		

		Start_Solving_Button = new myButton("Start",Small_Panel.getSize().width/2-130-20, 300, 130, 60,new Color(20, 115, 0));
		Small_Panel.add(Start_Solving_Button);
		Start_Solving_Button.setFont(new Font("MV Boli",Font.BOLD,15));
		Start_Solving_Button.addActionListener(this);
		Start_Solving_Button.setEnabled(false);
		

		Reset_Button = new myButton("Reset Maze",Small_Panel.getSize().width/2+20, 300, 130, 60,new Color(225, 120, 50));
		Reset_Button.setFont(new Font("MV Boli",Font.BOLD,15));
		Small_Panel.add(Reset_Button);
		Reset_Button.addActionListener(this);
				
		
		
		
		
		
		
		initMaze();
		tm.start();
	}
	
	
	private void initMaze() {
		maze = new myMaze(Maze_Size,Cell_Size);	
	}

	public void paintComponent(Graphics g) {
		
		if(maze.checkFinished()) {
			reMaze_Button.setEnabled(true);
			Start_Solving_Button.setEnabled(true);
			algoBox.setEnabled(true);
		}else {
			Start_Solving_Button.setEnabled(false);
			Reset_Button.setEnabled(false);
			algoBox.setEnabled(false);
		}
		
		super.paintComponent(g);
		
		
	
		
		
		maze.drawMaze(g);
		
	
		if(!flag) {
			maze.drawPathFinder(g,mode);
			if(maze.finish) {
				Maze_CheckBox.setEnabled(true);
				Start_Solving_Button.setEnabled(true);
				Reset_Button.setEnabled(true);
				tm.stop();
			}
		}
		
		if(!Maze_CheckBox.isSelected()) {
			maze.mazeAlgorithm(g);
		}	
		else {
			maze.drawMazeInstantly();
		}
		
		
		
		
		
	}
	private void reset() {
		tm.start();
		running = -1;	
		Start_Button.setEnabled(true);
		repaint();
	}
	
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
	

		if(e.getSource()==DifficultyBox) {
			if(DifficultyBox.getSelectedIndex()==0) {
				Maze_CheckBox.setEnabled(true);
				Cell_Label.setText("Cell's size adjustment: " + DifficultyBox.getSelectedItem());
				Cell_Size = 100;
				Start_Button.setText("Start");
				flag = true;
				initMaze();
				reset();
			}
			else if(DifficultyBox.getSelectedIndex()==1) {
				Maze_CheckBox.setEnabled(true);
				Cell_Label.setText("Cell's size adjustment: " + DifficultyBox.getSelectedItem());
				Cell_Size = 70;
				Start_Button.setText("Start");
				flag = true;
				initMaze();
				reset();
			}else if(DifficultyBox.getSelectedIndex()==2) {
				Maze_CheckBox.setEnabled(true);
				Cell_Label.setText("Cell's size adjustment: " + DifficultyBox.getSelectedItem());
				Cell_Size = 50;
				Start_Button.setText("Start");
				flag = true;
				initMaze();
				reset();
			}
			else if(DifficultyBox.getSelectedIndex()==3) {
				Maze_CheckBox.setEnabled(true);
				Cell_Label.setText("Cell's size adjustment: " + DifficultyBox.getSelectedItem());
				Cell_Size = 20;
				Start_Button.setText("Start");
				flag = true;
				initMaze();
				reset();
			}else if(DifficultyBox.getSelectedIndex()==4) {
				Maze_CheckBox.setEnabled(true);
				Cell_Label.setText("Cell's size adjustment: " + DifficultyBox.getSelectedItem());
				Cell_Size = 5;
				Start_Button.setText("Start");
				flag = true;
				initMaze();
				reset();
			}
		}
		
		
		
		if(e.getSource()==reMaze_Button) {
			flag = true;
			Maze_CheckBox.setEnabled(true);
			initMaze();
			reset();
			Start_Button.setText("Start");
		}
		
		
			
		
		if (e.getSource()==Start_Button) {
			running *= -1;
			if(running == 1)
				Start_Button.setText("Pause");
			else
				Start_Button.setText("Start");
		}
		
	

		if(e.getSource()==algoBox) {
			mode = algoBox.getSelectedIndex();
		}
	
		
		
		
		
		if(e.getSource() == Start_Solving_Button) {
			Maze_CheckBox.setSelected(false);
			Maze_CheckBox.setEnabled(false);
			
			if(flag) {
				maze.initStartAndEnd();
				flag = false;
			}
			
		}
		
		
		
		if(e.getSource()==Reset_Button) {
			flag = true;
			maze.resetMaze();
			tm.start();
		}
		
		
		
		
		
		
		
		
		
		if(running==1)
			repaint();
	}


	@Override
	public void stateChanged(ChangeEvent e) {
		
		
	
		
		
		
		
		if(e.getSource()==Speed_Slider) {
				Speed_Label.setText("Speed: " + Speed_Slider.getValue());
				delay = Slowest_Delay - (Speed_Slider.getValue()-1)* ((Slowest_Delay-5)/5) ;
				tm.setDelay(delay);
				System.out.println(delay);
		}
		
		
		
		
			
	}


	


}