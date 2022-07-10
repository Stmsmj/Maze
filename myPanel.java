package com.company;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
public class myPanel extends JPanel implements ActionListener,ChangeListener{
    private final int mazeSize = 600;
    private int cellSize = 40;
    private int slowestDelay = 110;
    private int delay = 10;
    private int windowW,windowH;
    private int running = -1;
    public int code;
    private int switchCaseVar = 0;
    private myMaze maze;
    private Timer tm;
    private JPanel smallPanel;
    private myButton startButton;
    private myButton resetButton;
    private myButton reMazeButton;
    private JLabel cellLabel;
    private JSlider cellSlider;
    private JLabel speedLabel;
    private JSlider speedSlider;
    private JCheckBox mazeCheckbox;
    private JCheckBox BFSCheckbox;
    private JComboBox algoBox;
    private int mode ;
    private JLabel algoBoxLabel;
    private myButton startSolvingButton;
    private boolean flag = true;
    private JPanel panel2;
    myPanel(int windowW,int windowH){
        this.windowW = windowW;
        this.windowH = windowH;
        tm = new Timer(delay,this);
        this.setPreferredSize(new Dimension(windowW,windowH));
        this.setBounds(200, 0, windowW, windowH);
        this.setBackground(new Color(250,250,250));
        this.setLayout(null);
        //cái bảng bên phải
        smallPanel = new JPanel();
        smallPanel.setPreferredSize(new Dimension((windowW-mazeSize)+200,windowH));
        smallPanel.setBounds(mazeSize+400, 0, windowW-mazeSize-300, windowH);
        smallPanel.setLayout(null);
        this.add(smallPanel);
        //nút start
        startButton = new myButton("Start",(int)(smallPanel.getSize().width/2)-120-30-50, 30, 120, 50,Color.PINK);
        smallPanel.add(startButton);
        startButton.addActionListener(this);
        //nút re-maze
        reMazeButton = new myButton("Re-Maze",(int)(smallPanel.getSize().width/2)+30-50, 30, 120, 50,Color.CYAN);
        smallPanel.add(reMazeButton);
        reMazeButton.addActionListener(this);
        reMazeButton.setEnabled(false);
        //Bảng lựa chọn cellSlider + Label của nó
        cellSlider = new JSlider(10,100,40);
        cellSlider.setBounds((windowW-mazeSize)/2-(350/2)-200, 150, 350, 50);
        cellSlider.setPaintTicks(true);
        cellSlider.setMinorTickSpacing(5);
        cellSlider.setPaintTrack(true);
        cellSlider.setMajorTickSpacing(10);
        cellSlider.setSnapToTicks(true);
        cellSlider.setPaintLabels(true);
        cellSlider.addChangeListener(this);
        cellLabel = new JLabel();
        cellLabel.setText("Cell's size adjustment: " + cellSlider.getValue());
        cellLabel.setBounds(130-100, 130, 250, 20);
        cellLabel.setFont(new Font("",Font.BOLD,18));
        smallPanel.add(cellLabel);
        smallPanel.add(cellSlider);
        //Bảng lựa chọn speed va label cua no
        speedSlider = new JSlider(1,5,5);
        speedSlider.setBounds((windowW-mazeSize)/2-(350/2)-200, 230, 350, 40);
        speedSlider.setPaintTrack(true);
        speedSlider.setMajorTickSpacing(1);
        speedSlider.setSnapToTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this);

        speedLabel = new JLabel();
        speedLabel.setText("Speed: " + speedSlider.getValue());
        speedLabel.setBounds(130-100, 210, 180, 20);
        speedLabel.setFont(new Font("",Font.BOLD,18));

        smallPanel.add(speedLabel);
        smallPanel.add(speedSlider);
        //generate Check box
        mazeCheckbox = new JCheckBox();
        mazeCheckbox.setBounds((int)(smallPanel.getSize().width/2)-120-30-50, 95, 250, 20);
        mazeCheckbox.setText("Generate instantly");
        mazeCheckbox.setFont(new Font("",Font.BOLD,15));
        mazeCheckbox.setFocusable(false);

        smallPanel.add(mazeCheckbox);

		//Check box
		/*BFSCheckbox = new JCheckBox();
		BFSCheckbox.setBounds(smallPanel.getSize().width/2-30, 285, 250, 30);
		BFSCheckbox.setText("Breadth First Search");
		BFSCheckbox.setFont(new Font("",Font.BOLD,15));
		BFSCheckbox.setFocusable(false);
		smallPanel.add(BFSCheckbox);*/

        algoBoxLabel = new JLabel("Pathfinding Algorithms");
        algoBoxLabel.setBounds(smallPanel.getSize().width/2-155-50, 370, 220, 30);
        algoBoxLabel.setFont(new Font("",Font.BOLD,15));
        smallPanel.add(algoBoxLabel);

        // malle man
        String[] algoList = {"Breadth First Search(BFS)","Depth First Search(DFS)","Play"};// (0 == BFS , 1 = DFS)
        algoBox = new JComboBox(algoList);
        algoBox.setBounds(smallPanel.getSize().width/2-160-50, 400, 220, 30);
        algoBox.setFont(new Font("",Font.BOLD,15));
        algoBox.setFocusable(false);
        algoBox.addActionListener(this);
        //algoBox.setEnabled(false);
        mode = algoBox.getSelectedIndex(); // (0 == BFS , 1 = DFS)
        smallPanel.add(algoBox);

        //BFS button
        startSolvingButton = new myButton("Start",smallPanel.getSize().width/2-130-20-50, 300, 130, 60,new Color(255, 231, 122));
        smallPanel.add(startSolvingButton);
        startSolvingButton.addActionListener(this);
        startSolvingButton.setEnabled(false);

        //nút resetMaze
        resetButton = new myButton("Reset Maze",smallPanel.getSize().width/2+20-50, 300, 130, 60,new Color(44, 95, 45));
        smallPanel.add(resetButton);
        resetButton.addActionListener(this);

        initMaze();
        tm.start();
        panel2 = new JPanel();
        panel2.setBounds(0,0,395,600);
        panel2.setBackground(Color.red);
        this.add(panel2);

    }
    private void initMaze() {
        maze = new myMaze(mazeSize,cellSize);
    }

    public void paintComponent(Graphics g) {

        if(maze.checkFinished()) {
            reMazeButton.setEnabled(true);
            startSolvingButton.setEnabled(true);
            algoBox.setEnabled(true);
        }else {
            startSolvingButton.setEnabled(false);
            resetButton.setEnabled(false);
            algoBox.setEnabled(false);
        }

        super.paintComponent(g);

        maze.drawMaze(g);
        if(!flag) {
            maze.drawPathFinder(g,mode);
            if(maze.finish) {
                mazeCheckbox.setEnabled(true);
                startSolvingButton.setEnabled(true);
                resetButton.setEnabled(true);
                tm.stop();
            }
        }

        if(!mazeCheckbox.isSelected()) {
            maze.mazeAlgorithm(g);
        }
        else {
            maze.drawMazeInstantly();
        }

    }
    private void reset() {
        tm.start();
        running = -1;
        startButton.setEnabled(true);
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        //nhan nut re-maze, tạo lại maze mới
        if(e.getSource()==reMazeButton) {
            flag = true;
            mazeCheckbox.setEnabled(true);
            initMaze();
            reset();
            startButton.setText("Start");
        }



        //nhấn nút start
        if (e.getSource()==startButton) {
            running *= -1;
            if(running == 1)
                startButton.setText("Pause");
            else
                startButton.setText("Start");
        }

        if(e.getSource()==algoBox) {
            mode = algoBox.getSelectedIndex();
        }
        //start của bên thuật toán tìm đường
        if(e.getSource() == startSolvingButton) {
            mazeCheckbox.setSelected(false);
            mazeCheckbox.setEnabled(false);

            if(flag) {
                maze.initStartAndEnd();
                flag = false;
            }

        }
        //nút reset maze
        if(e.getSource()==resetButton) {
            flag = true;
            maze.resetMaze();
            tm.start();
        }
        if(running==1)
            repaint();
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        //cellSlider
        if(e.getSource()==cellSlider) {
            if(cellSlider.getValue()%5==0) {
                mazeCheckbox.setEnabled(true);
                cellLabel.setText("Cell's size adjustment: " + cellSlider.getValue());
                cellSize = cellSlider.getValue();
                startButton.setText("Start");
                flag = true;
                initMaze();
                reset();
            }
        }

        //SpeedSlider
        if(e.getSource()==speedSlider) {
            speedLabel.setText("Speed: " + speedSlider.getValue());
            delay = slowestDelay - (speedSlider.getValue()-1)* ((slowestDelay-10)/4) ;
            tm.setDelay(delay);
            System.out.println(delay);
        }





    }






}
