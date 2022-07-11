
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
public class cells {
    // باید بدم به نگارشی کل اینو بدون تغییر
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
        c = col*Cell_Size +400;
        this.Cell_Size = Cell_Size;
        Next = new ArrayList<cells>();
        Parent = null;
        Font_Weight = (int)(10+0.3*(Cell_Size-20));
        Stroke_Size = (int)((5.0/9 * ((Cell_Size/10)-1) )+1);
    }
    public void Draw_Cell(Graphics g) {
        Graphics2D G2D = (Graphics2D) g;

        G2D.setColor(new Color(128,128,128));
        G2D.setStroke(new BasicStroke(Stroke_Size));

        if(Walls[0] == true)
            G2D.drawLine(c, r, c+Cell_Size, r);
        if(Walls[1] == true)
            G2D.drawLine(c+Cell_Size, r, c+Cell_Size, r+Cell_Size);
        if(Walls[2] == true)
            G2D.drawLine(c+Cell_Size, r+Cell_Size, c, r+Cell_Size);
        if(Walls[3] == true)
            G2D.drawLine(c, r+Cell_Size, c, r);

        G2D.setStroke(new BasicStroke(1));
    }

    public void drawWeight(Graphics g) {
        g.setFont(new Font("",Font.BOLD,Font_Weight));
        g.drawString(""+Weight, (int)(c+Cell_Size/2)-(int)(Font_Weight/3), (int)(r+Cell_Size/2)+(int)(Font_Weight/3));
    }

    public void Draw_Box(Graphics g,Color color) {
        Graphics2D G2D = (Graphics2D) g;
        if(this.Visited == true) {
            G2D.setColor(color);
            G2D.fillRect(c, r, Cell_Size, Cell_Size);
        }
    }

    public void drawPath(Graphics g,Color color) {
        Graphics2D G2D = (Graphics2D) g;
        G2D.setColor(color);
        if(Parent!=null) {
            if(this.Visited_Path == true) {
                G2D.fillRect(c+(int)(Cell_Size/3), r+(int)(Cell_Size/3), (int)(Cell_Size/3), (int)(Cell_Size/3));
            }
            if(row - 1 == Parent.row) {
                G2D.fillRect(c+(int)(Cell_Size/3), r, (int)(Cell_Size/3), (int)(Cell_Size/3));
                G2D.fillRect(Parent.c+(int)(Cell_Size/3), Parent.r+((int)(Cell_Size/3)*2), (int)(Cell_Size/3), (int)(Cell_Size/3));
            }

            if(col + 1 == Parent.col) {
                G2D.fillRect(c+((int)(Cell_Size/3)*2), r+(int)(Cell_Size/3), (int)(Cell_Size/3), (int)(Cell_Size/3));
                G2D.fillRect(Parent.c, Parent.r+(int)(Cell_Size/3), (int)(Cell_Size/3), (int)(Cell_Size/3));
            }

            if(row + 1 == Parent.row) {
                G2D.fillRect(c+(int)(Cell_Size/3), r+((int)(Cell_Size/3)*2), (int)(Cell_Size/3), (int)(Cell_Size/3));
                G2D.fillRect(Parent.c+(int)(Cell_Size/3), Parent.r, (int)(Cell_Size/3), (int)(Cell_Size/3));
            }

            if(col - 1 == Parent.col) {
                G2D.fillRect(c, r+(int)(Cell_Size/3), (int)(Cell_Size/3), (int)(Cell_Size/3));
                G2D.fillRect(Parent.c+((int)(Cell_Size/3)*2), Parent.r+(int)(Cell_Size/3), (int)(Cell_Size/3), (int)(Cell_Size/3));
            }
        }
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
