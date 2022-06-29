package AliHube.com;

import java.util.ArrayList;
import java.util.Random;

public class cells {

    public int row,r,col,c,Cell_Size;
    public boolean[] Walls = {true,true,true,true};
    public ArrayList<cells> Next;
    public cells Parent;
    public boolean Visited=false;
    private int Weight = 1;
    private int Font_Weight;
    private int Stroke_Size;
    public boolean Visited_Path = false;


    cells(int row,int col,int Cell_Size){
        this.row = row;
        this.col = col;
        r = row*Cell_Size;
        c = col*Cell_Size;
        this.Cell_Size = Cell_Size;
        Next = new ArrayList<cells>();
        Parent = null;
        Font_Weight = (int)(10+0.3*(Cell_Size-20));
        Stroke_Size = (int)((5.0/9 * ((Cell_Size/10)-1) )+1);

    }



    public void Reset_Cell() {
        Visited = false;
        Visited_Path = false;
        Parent = null;
        Next = new ArrayList<>();
    }

    public int Get_Weight() {
        return Weight;
    }

    public void Set_Weight() {
        this.Weight = new Random().nextInt(9)+1;
    }

}
