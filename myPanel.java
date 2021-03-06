import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class myPanel extends JPanel implements ActionListener,ChangeListener{

    private final int Maze_Size = 600;
    private int Cell_Size = 100;
    private int Slowest_Delay = 110;
    private int delay = 10;
    private int Window_W,Window_H;
    private int running = -1;
    String[] Difficulties;

    private int Switch_Case_Var = 0;

    private myMaze maze;
    private Timer tm;


    private JPanel Small_Panel;
    private myButton Start_Button;
    private myButton Reset_Button;
    private myButton reMaze_Button;

    private JLabel Cell_Label;
    private JComboBox DifficultyBox;
    private JComboBox Language_box;
    private JLabel Language_label;

    private JLabel Speed_Label;
    private JSlider Speed_Slider;

    private JCheckBox Maze_CheckBox;

    private JComboBox algoBox;
    private int mode;
    private JLabel algoBox_Label;


    private myButton Start_Solving_Button;
    private boolean flag = true;




    myPanel(int Window_W,int Window_H){
        this.Window_W = Window_W;
        this.Window_H = Window_H;

        tm = new Timer(delay,this);

        this.setPreferredSize(new Dimension(Window_W,Window_H));
        this.setBounds(0, 0, Window_W, Window_H);
        this.setBackground(new Color(50,50,50));
        this.setLayout(null);



        Small_Panel = new JPanel();
        Small_Panel.setPreferredSize(new Dimension((Window_W-Maze_Size),Window_H));
        Small_Panel.setBounds(Maze_Size, 0, Window_W-Maze_Size, Window_H);
        Small_Panel.setLayout(null);
        this.add(Small_Panel);



        Start_Button = new myButton("Start",Small_Panel.getSize().width/2-130-20, 240, 160, 60,new Color(100,149 ,237));
        Start_Button.setFont(new Font("MV Boli",Font.BOLD,15));
        Small_Panel.add(Start_Button);
        Start_Button.addActionListener(this);




        reMaze_Button = new myButton("Re-Maze",Small_Panel.getSize().width/2+20, 240, 160, 60,new Color(240,128,128));
        reMaze_Button.setFont(new Font("MV Boli",Font.BOLD,15));
        Small_Panel.add(reMaze_Button);
        reMaze_Button.addActionListener(this);
        reMaze_Button.setEnabled(false);

        Difficulties=new String[] {"kids","medium","hard","legends","god mode"};

        DifficultyBox= new JComboBox<String>(Difficulties);
        DifficultyBox.setBounds(30, 170, 150, 50);
        DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
        DifficultyBox.setFocusable(false);
        DifficultyBox.addActionListener(this);





        Cell_Label = new JLabel();
        Cell_Label.setText("Difficulty: " + DifficultyBox.getSelectedItem());
        Cell_Label.setBounds(30, 150, 250, 20);
        Cell_Label.setFont(new Font("MV Boli",Font.BOLD,18));

        Small_Panel.add(Cell_Label);
        Small_Panel.add(DifficultyBox);



        Speed_Slider = new JSlider(1,6,3);
        Speed_Slider.setBounds((Window_W-Maze_Size)/2-(350/2), 340, 350, 40);
        Speed_Slider.setPaintTrack(true);
        Speed_Slider.setMajorTickSpacing(1);
        Speed_Slider.setSnapToTicks(true);
        Speed_Slider.setPaintLabels(true);
        Speed_Slider.addChangeListener(this);

        Speed_Label = new JLabel();
        Speed_Label.setText("Speed: " + Speed_Slider.getValue());
        Speed_Label.setBounds(30, 270, 220, 20);
        Speed_Label.setFont(new Font("MV Boli",Font.BOLD,18));

        Small_Panel.add(Speed_Label);
        Small_Panel.add(Speed_Slider);

        Maze_CheckBox = new JCheckBox();
        Maze_CheckBox.setBounds((int)30, 110, 200, 20);
        Maze_CheckBox.setText("Generate instantly");
        Maze_CheckBox.setFont(new Font("MV Boli",Font.BOLD,15));
        Maze_CheckBox.setFocusable(false);
        Small_Panel.add(Maze_CheckBox);

        algoBox_Label = new JLabel("Pathfinding Algorithms:");
        algoBox_Label.setBounds(Small_Panel.getSize().width/2-155,540 , 280, 30);
        algoBox_Label.setFont(new Font("MV Boli",Font.BOLD,15));
        Small_Panel.add(algoBox_Label);



        String[] algoList = {"play","Breadth First Search(BFS)","Depth First Search(DFS)"};
        algoBox = new JComboBox<String>(algoList);
        algoBox.setBounds(Small_Panel.getSize().width/2-160,570 , 280, 30);
        algoBox.setFont(new Font("MV Boli",Font.BOLD,15));
        algoBox.setFocusable(false);
        algoBox.addActionListener(this);
        mode = algoBox.getSelectedIndex();



        Small_Panel.add(algoBox);

        Language_label = new JLabel("Language:");
        Language_label.setBounds(Small_Panel.getSize().width/2-155, 470, 220, 30);
        Language_label.setFont(new Font("MV Boli",Font.BOLD,15));
        Small_Panel.add(Language_label);



        String[] Language_list = {"English","Fran??ais","Deutsch","??????","Espa??ol","Russian","??????????"};
        Language_box = new JComboBox<String>(Language_list);
        Language_box.setBounds(Small_Panel.getSize().width/2-160, 500, 220, 30);
        Language_box.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
        Language_box.setFocusable(false);
        Language_box.addActionListener(this);
        Small_Panel.add(Language_box);



        Start_Solving_Button = new myButton("Start",Small_Panel.getSize().width/2-130-20, 400, 160, 60,new Color(127, 255, 212));
        Small_Panel.add(Start_Solving_Button);
        Start_Solving_Button.setFont(new Font("MV Boli",Font.BOLD,15));
        Start_Solving_Button.addActionListener(this);
        Start_Solving_Button.setEnabled(false);


        Reset_Button = new myButton("Reset Maze",Small_Panel.getSize().width/2+20, 400, 160, 60,new Color(112, 128, 144));
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
            Reset_Button.setEnabled(false);
            DifficultyBox.setEnabled(false);
            Start_Button.setEnabled(false);
            Start_Solving_Button.setEnabled(true);
            algoBox.setEnabled(true);
            Language_box.setEnabled(true);
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
                Maze_CheckBox.setEnabled(false);
                Start_Solving_Button.setEnabled(false);
                Reset_Button.setEnabled(true);
                reMaze_Button.setEnabled(true);
                Speed_Slider.setEnabled(true);

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
        DifficultyBox.setEnabled(true);
        repaint();
    }


    @Override
    public void actionPerformed(ActionEvent e) {


if (e.getSource()==Language_box) {
            if (Language_box.getSelectedIndex()==3) {
                // japanese
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(1);
                DifficultyBox.removeItemAt(2);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.addItem("??????");
                DifficultyBox.addItem("????????????");
                DifficultyBox.addItem("?????????");
                DifficultyBox.addItem("??????");
                DifficultyBox.addItem("????????????");

                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.addItem("???????????????(BFS)");
                algoBox.addItem("??????????????????(DFS)");

                algoBox_Label.setText("????????????????????????????????????????????????:");
                Speed_Label.setText("????????????: ");
                Maze_CheckBox.setText("???????????????: ");
                Start_Solving_Button.setText("?????????");
                Cell_Label.setText("????????? ");
                Language_label.setText("?????????");
                Reset_Button.setText("?????????????????????");
                reMaze_Button.setText("????????????");
                Start_Button.setText("?????????");
                DifficultyBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                algoBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                algoBox_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,13));
                Speed_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,18));
                Maze_CheckBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Start_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                reMaze_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Reset_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,13));
                Start_Solving_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                DifficultyBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Cell_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Language_label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));

            }else if (Language_box.getSelectedIndex()==0) {
                // english
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(1);
                DifficultyBox.removeItemAt(2);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.addItem("kids");
                DifficultyBox.addItem("medium");
                DifficultyBox.addItem("hard");
                DifficultyBox.addItem("legends");
                DifficultyBox.addItem("god mode");
                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.addItem("Breadth First Search(BFS)");
                algoBox.addItem("Depth First Search(DFS)");

                algoBox_Label.setText("Pathfinding Algorithms:");
                Speed_Label.setText("Speed: ");
                Maze_CheckBox.setText("Generate instantly: ");
                Start_Solving_Button.setText("Start");
                Cell_Label.setText("Difficulty: ");
                Language_label.setText("Language: ");
                Reset_Button.setText("Reset Maze");
                reMaze_Button.setText("Re-Maze");
                Start_Button.setText("Start");
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                algoBox.setFont(new Font("MV Boli",Font.BOLD,15));
                algoBox_Label.setFont(new Font("MV Boli",Font.BOLD,13));
                Speed_Label.setFont(new Font("MV Boli",Font.BOLD,18));
                Maze_CheckBox.setFont(new Font("MV Boli",Font.BOLD,15));
                Start_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                reMaze_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                Reset_Button.setFont(new Font("MV Boli",Font.BOLD,13));
                Start_Solving_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                Cell_Label.setFont(new Font("MV Boli",Font.BOLD,15));
                Language_label.setFont(new Font("MV Boli",Font.BOLD,15));
            }
            else if(Language_box.getSelectedIndex()==1){
                // french
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(1);
                DifficultyBox.removeItemAt(2);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.addItem("des gamins");
                DifficultyBox.addItem("moyen");
                DifficultyBox.addItem("dur");
                DifficultyBox.addItem("l??gendes");
                DifficultyBox.addItem("mode dieu");
                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.addItem("Recherche ??tendue d'abord(BFS)");
                algoBox.addItem("Premi??re recherche en profondeur(DFS)");

                algoBox_Label.setText("Algorithmes de recherche de chemin:");
                Speed_Label.setText("La rapidit??: ");
                Maze_CheckBox.setText("G??n??rez instantan??ment:");
                Start_Solving_Button.setText("Commencer");
                Cell_Label.setText("Difficult??:");
                Language_label.setText("Langue:");
                Reset_Button.setText("R??initialiser d??dale");
                reMaze_Button.setText("d??dale ?? nouveau");
                Start_Button.setText("Commencer");
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                algoBox.setFont(new Font("MV Boli",Font.BOLD,12));
                algoBox_Label.setFont(new Font("MV Boli",Font.BOLD,13));
                Speed_Label.setFont(new Font("MV Boli",Font.BOLD,18));
                Maze_CheckBox.setFont(new Font("MV Boli",Font.BOLD,13));
                Start_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                reMaze_Button.setFont(new Font("MV Boli",Font.BOLD,14));
                Reset_Button.setFont(new Font("MV Boli",Font.BOLD,13));
                Start_Solving_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                Cell_Label.setFont(new Font("MV Boli",Font.BOLD,15));
                Language_label.setFont(new Font("MV Boli",Font.BOLD,15));
            }else if (Language_box.getSelectedIndex()==2) {
                // German
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(1);
                DifficultyBox.removeItemAt(2);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.addItem("Kinder");
                DifficultyBox.addItem("Mittel");
                DifficultyBox.addItem("schwer");
                DifficultyBox.addItem("Legenden");
                DifficultyBox.addItem("Gott Modus");
                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.addItem("Breitensuche zuerst(BFS)");
                algoBox.addItem("Tiefensuche zuerst(DFS)");

                algoBox_Label.setText("Pfadfindungsalgorithmen:");
                Speed_Label.setText("Geschwindigkeit:");
                Maze_CheckBox.setText("Sofort generieren:");
                Start_Solving_Button.setText("Anfang");
                Cell_Label.setText("Schwierigkeit: ");
                Language_label.setText("Sprache:");
                Reset_Button.setText("Matze zur??cksetzen");
                reMaze_Button.setText("Labyrinth wieder");
                Start_Button.setText("Anfang");
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                algoBox.setFont(new Font("MV Boli",Font.BOLD,15));
                algoBox_Label.setFont(new Font("MV Boli",Font.BOLD,13));
                Speed_Label.setFont(new Font("MV Boli",Font.BOLD,18));
                Maze_CheckBox.setFont(new Font("MV Boli",Font.BOLD,15));
                Start_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                reMaze_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                Reset_Button.setFont(new Font("MV Boli",Font.BOLD,12));
                Start_Solving_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                Cell_Label.setFont(new Font("MV Boli",Font.BOLD,15));
                Language_label.setFont(new Font("MV Boli",Font.BOLD,15));
            }else if (Language_box.getSelectedIndex()==4) {
                // spanish
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(1);
                DifficultyBox.removeItemAt(2);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.addItem("ni??os");
                DifficultyBox.addItem("medio");
                DifficultyBox.addItem("Duro");
                DifficultyBox.addItem("leyendas");
                DifficultyBox.addItem("modo de Dios");
                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.addItem("B??squeda primero en amplitud(BFS)");
                algoBox.addItem("Primera b??squeda en profundidad(DFS)");

                algoBox_Label.setText("Algoritmos de b??squeda de caminos:");
                Speed_Label.setText("Velocidad:");
                Maze_CheckBox.setText("Genera al instante: ");
                Start_Solving_Button.setText("comienzo");
                Cell_Label.setText("Dificultad:");
                Language_label.setText("Idioma:");
                Reset_Button.setText("Restablecer laberinto");
                reMaze_Button.setText("laberinto de nuevo");
                Start_Button.setText("comienzo");
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                algoBox.setFont(new Font("MV Boli",Font.BOLD,13));
                algoBox_Label.setFont(new Font("MV Boli",Font.BOLD,13));
                Speed_Label.setFont(new Font("MV Boli",Font.BOLD,18));
                Maze_CheckBox.setFont(new Font("MV Boli",Font.BOLD,15));
                Start_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                reMaze_Button.setFont(new Font("MV Boli",Font.BOLD,13));
                Reset_Button.setFont(new Font("MV Boli",Font.BOLD,12));
                Start_Solving_Button.setFont(new Font("MV Boli",Font.BOLD,15));
                DifficultyBox.setFont(new Font("MV Boli",Font.BOLD,15));
                Cell_Label.setFont(new Font("MV Boli",Font.BOLD,15));
                Language_label.setFont(new Font("MV Boli",Font.BOLD,15));
            }else if (Language_box.getSelectedIndex()==5) {
                // russian
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(1);
                DifficultyBox.removeItemAt(2);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.addItem("????????");
                DifficultyBox.addItem("??????????????");
                DifficultyBox.addItem("??????????????");
                DifficultyBox.addItem("??????????????");
                DifficultyBox.addItem("?????????? ????????");
                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.addItem("?????????? ?? ????????????(BFS)");
                algoBox.addItem("?????????? ?? ??????????????(DFS)");

                algoBox_Label.setText("?????????????????? ???????????? ????????:");
                Speed_Label.setText("????????????????:");
                Maze_CheckBox.setText("?????????????????????????? ??????????????????:");
                Start_Solving_Button.setText("????????????????");
                Cell_Label.setText("??????????????????: ");
                Language_label.setText("????????: ");
                Reset_Button.setText("???????????????? ????????????????");
                reMaze_Button.setText("???????????????? ??????????");
                Start_Button.setText("????????????????");
                DifficultyBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                algoBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                algoBox_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,13));
                Speed_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,18));
                Maze_CheckBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,12));
                Start_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                reMaze_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Reset_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,13));
                Start_Solving_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                DifficultyBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Cell_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Language_label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
            }else if (Language_box.getSelectedIndex()==6) {
                // ??????????
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(1);
                DifficultyBox.removeItemAt(2);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.removeItemAt(0);
                DifficultyBox.addItem("???????? ?????? ????");
                DifficultyBox.addItem("??????????");
                DifficultyBox.addItem("??????");
                DifficultyBox.addItem("???????? ???????????? ????");
                DifficultyBox.addItem("?????? ????????????");
                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.removeItemAt(0);
                algoBox.addItem("????????");
                algoBox.addItem("???????????? ?????? ??????(BFS)");
                algoBox.addItem("???????????? ???????? ??????(DFS)");

                algoBox_Label.setText("???????????????? ?????? ?????? ????????:");
                Speed_Label.setText("????????: ");
                Maze_CheckBox.setText("?????????? ??????: ");
                Start_Solving_Button.setText("????????");
                Cell_Label.setText("????????: ");
                Language_label.setText("????????: ");
                Reset_Button.setText("?????????? ???????? ??????");
                reMaze_Button.setText("???????? ???????? ?????? ");
                Start_Button.setText("????????");
                DifficultyBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                algoBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                algoBox_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,13));
                Speed_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,18));
                Maze_CheckBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,12));
                Start_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                reMaze_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Reset_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,13));
                Start_Solving_Button.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                DifficultyBox.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Cell_Label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
                Language_label.setFont(new Font("TRUETYPE_FONT",Font.BOLD,15));
            }}


        if(e.getSource()==reMaze_Button) {
            flag = true;
            Maze_CheckBox.setEnabled(true);
            reMaze_Button.setEnabled(false);
            Speed_Slider.setEnabled(true);
            initMaze();
            reset();
            if (Language_box.getSelectedIndex()==0){
                Start_Button.setText("Make-Maze");
            }else if(Language_box.getSelectedIndex()==1){
                Start_Button.setText("faire un labyrinthe");
            }
            else if (Language_box.getSelectedIndex()==3){
                Start_Button.setText("???????????????");}
            else if (Language_box.getSelectedIndex()==2){
                Start_Button.setText("Labyrinth machen");
            } else if (Language_box.getSelectedIndex()==4){
                Start_Button.setText("hacer laberinto");
            }else if (Language_box.getSelectedIndex()==5){
                Start_Button.setText("?????????????? ????????????????");
            }else if (Language_box.getSelectedIndex()==6){
                Start_Button.setText("?????????? ??????");
            }
        }




        if (e.getSource()==Start_Button) {
            DifficultyBox.setEnabled(false);
            running *= -1;
            if(running == 1){
                if (Language_box.getSelectedIndex()==3){
                    Start_Button.setText("????????????");}
                else if (Language_box.getSelectedIndex()==0){
                    Start_Button.setText("Pause");
                }else if (Language_box.getSelectedIndex()==1){
                    Start_Button.setText("Pause");
                }else if (Language_box.getSelectedIndex()==2){
                    Start_Button.setText("Pause");
                } else if (Language_box.getSelectedIndex()==4){
                    Start_Button.setText("Pausa");
                }else if (Language_box.getSelectedIndex()==5){
                    Start_Button.setText("??????????");
                }else if (Language_box.getSelectedIndex()==6){
                    Start_Button.setText("????????");
                }
            }
            else{
                if (Language_box.getSelectedIndex()==3){
                    Start_Button.setText("???????????????");}
                if (Language_box.getSelectedIndex()==0){
                    Start_Button.setText("Make-Maze");
                } else if(Language_box.getSelectedIndex()==1){
                    Start_Button.setText("faire un labyrinthe");
                }else if (Language_box.getSelectedIndex()==2){
                    Start_Button.setText("Labyrinth machen");
                } else if (Language_box.getSelectedIndex()==4){
                    Start_Button.setText("hacer laberinto");
                }else if (Language_box.getSelectedIndex()==5){
                    Start_Button.setText("?????????????? ????????????????");
                } else if (Language_box.getSelectedIndex()==6){
                    Start_Button.setText("?????????? ??????");
                }
            }
            reMaze_Button.setEnabled(true);
        }




        if(e.getSource()==algoBox) {
            mode = algoBox.getSelectedIndex();
        }





        if(e.getSource() == Start_Solving_Button) {
            Maze_CheckBox.setSelected(false);
            Maze_CheckBox.setEnabled(false);
            Speed_Slider.setEnabled(false);
            algoBox.setEnabled(false);
            if(mode==0){
                tm.setDelay(150);
            }



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


        if(e.getSource()==DifficultyBox) {
            if(DifficultyBox.getSelectedIndex()==0) {
                Maze_CheckBox.setEnabled(true);
                if (Language_box.getSelectedIndex()==3) {
                    Cell_Label.setText("????????? " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("???????????????");
                }else if (Language_box.getSelectedIndex()==0) {
                    Cell_Label.setText("Difficulty: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Make-Maze");
                }else if(Language_box.getSelectedIndex()==1){
                    Cell_Label.setText("Difficult??: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("faire un labyrinthe");
                }else if (Language_box.getSelectedIndex()==2) {
                    Cell_Label.setText("Schwierigkeit: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Labyrinth machen");
                }else if (Language_box.getSelectedIndex()==4) {
                    Cell_Label.setText("Dificultad: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("hacer laberinto");
                }else if (Language_box.getSelectedIndex()==5) {
                    Cell_Label.setText("??????????????????:" + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????????? ????????????????");
                }else if (Language_box.getSelectedIndex()==6) {
                    Cell_Label.setText("????????: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????? ??????");
                }

                Cell_Size = 100;

                flag = true;
                initMaze();
                reset();
            }
            else if(DifficultyBox.getSelectedIndex()==1) {
                Maze_CheckBox.setEnabled(true);
                if (Language_box.getSelectedIndex()==3) {
                    Cell_Label.setText("????????? " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("???????????????");
                }if (Language_box.getSelectedIndex()==0) {
                    Cell_Label.setText("Difficulty: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Make-Maze");
                }else if(Language_box.getSelectedIndex()==1){
                    Cell_Label.setText("Difficult??: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("faire un labyrinthe");
                }else if (Language_box.getSelectedIndex()==2) {
                    Cell_Label.setText("Schwierigkeit: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Labyrinth machen");
                }else if (Language_box.getSelectedIndex()==4) {
                    Cell_Label.setText("Dificultad: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("hacer laberinto");
                }else if (Language_box.getSelectedIndex()==5) {
                    Cell_Label.setText("??????????????????:" + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????????? ????????????????");
                }else if (Language_box.getSelectedIndex()==6) {
                    Cell_Label.setText("????????: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????? ??????");
                }
                Cell_Size = 70;
                flag = true;
                initMaze();
                reset();
            }else if(DifficultyBox.getSelectedIndex()==2) {
                Maze_CheckBox.setEnabled(true);
                if (Language_box.getSelectedIndex()==3) {
                    Cell_Label.setText("????????? " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("???????????????");
                }if (Language_box.getSelectedIndex()==0) {
                    Cell_Label.setText("Difficulty: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Make-Maze");
                }else if(Language_box.getSelectedIndex()==1){
                    Cell_Label.setText("Difficult??: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("faire un labyrinthe");
                }else if (Language_box.getSelectedIndex()==2) {
                    Cell_Label.setText("Schwierigkeit: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Labyrinth machen");
                }else if (Language_box.getSelectedIndex()==4) {
                    Cell_Label.setText("Dificultad: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("hacer laberinto");
                }else if (Language_box.getSelectedIndex()==5) {
                    Cell_Label.setText("??????????????????:" + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????????? ????????????????");
                }else if (Language_box.getSelectedIndex()==6) {
                    Cell_Label.setText("????????: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????? ??????");
                }
                Cell_Size = 50;
                flag = true;
                initMaze();
                reset();
            }
            else if(DifficultyBox.getSelectedIndex()==3) {
                Maze_CheckBox.setEnabled(true);
                if (Language_box.getSelectedIndex()==3) {
                    Cell_Label.setText("????????? " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("???????????????");
                }if (Language_box.getSelectedIndex()==0) {
                    Cell_Label.setText("Difficulty: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Make-Maze");
                }else if(Language_box.getSelectedIndex()==1){
                    Cell_Label.setText("Difficult??: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("faire un labyrinthe");
                }else if (Language_box.getSelectedIndex()==2) {
                    Cell_Label.setText("Schwierigkeit: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Labyrinth machen");
                }else if (Language_box.getSelectedIndex()==4) {
                    Cell_Label.setText("Dificultad: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("hacer laberinto");
                }else if (Language_box.getSelectedIndex()==5) {
                    Cell_Label.setText("??????????????????:" + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????????? ????????????????");
                }else if (Language_box.getSelectedIndex()==6) {
                    Cell_Label.setText("????????: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????? ??????");
                }
                Cell_Size = 20;
                flag = true;
                initMaze();
                reset();
            }else if(DifficultyBox.getSelectedIndex()==4) {
                Maze_CheckBox.setEnabled(true);
                if (Language_box.getSelectedIndex()==3) {
                    Cell_Label.setText("????????? " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("???????????????");
                }if (Language_box.getSelectedIndex()==0) {
                    Cell_Label.setText("Difficulty: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Make-Maze");
                }else if(Language_box.getSelectedIndex()==1){
                    Cell_Label.setText("Difficult??: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("faire un labyrinthe");
                }else if (Language_box.getSelectedIndex()==2) {
                    Cell_Label.setText("Schwierigkeit: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("Labyrinth machen");
                }else if (Language_box.getSelectedIndex()==4) {
                    Cell_Label.setText("Dificultad: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("hacer laberinto");
                }else if (Language_box.getSelectedIndex()==5) {
                    Cell_Label.setText("??????????????????:" + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????????? ????????????????");
                }else if (Language_box.getSelectedIndex()==6) {
                    Cell_Label.setText("????????: " + DifficultyBox.getSelectedItem());
                    Start_Button.setText("?????????? ??????");
                }
                Cell_Size = 5;
                flag = true;
                initMaze();
                reset();
            }
        }
    }


    @Override
    public void stateChanged(ChangeEvent e) {







        if(e.getSource()==Speed_Slider) {
            if (Language_box.getSelectedIndex()==0) {
                Speed_Label.setText("Speed: " + Speed_Slider.getValue());
            }else if (Language_box.getSelectedIndex()==1) {
                Speed_Label.setText("La rapidit??: " + Speed_Slider.getValue());
            }else if (Language_box.getSelectedIndex()==3) {
                Speed_Label.setText("??????????????? " + Speed_Slider.getValue());
            }else if (Language_box.getSelectedIndex()==2) {
                Speed_Label.setText("Geschwindigkeit: " + Speed_Slider.getValue());
            }else if (Language_box.getSelectedIndex()==4) {
                Speed_Label.setText("Velocidad: " + Speed_Slider.getValue());
            }else if (Language_box.getSelectedIndex()==5) {
                Speed_Label.setText("????????????????: " + Speed_Slider.getValue());
            }else if (Language_box.getSelectedIndex()==6) {
                Speed_Label.setText("????????: " + Speed_Slider.getValue());
            }

            delay = Slowest_Delay - (Speed_Slider.getValue()-1)* ((Slowest_Delay-5)/5) ;
            tm.setDelay(delay);
            System.out.println(delay);
        }





    }





}
