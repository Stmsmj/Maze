import java.awt.*;
import java.util.*;

public class myMaze{
    private final int Maze_Size;
    private int Cell_Size;
    private final int w ;
    private cells[][] grids;
    private cells current;
    private cells Next;
    private Stack<cells> Visited_Stack;
    private boolean running = false;
    private int Count_Visited=0;
    public boolean finish = false;
    private ArrayList<cells> Paths_From_A_to_B;

    private cells Start,End;
    private Queue<cells> Visited_Queue;
    private Stack<cells> VisitedS;

    myMaze(int mazeSize,int cellSize){
        Maze_Size = mazeSize;
        Cell_Size = cellSize;
        w = (int)(Maze_Size/Cell_Size);
        init();
    }

    private void init() {
        grids = new cells[w][w];
        for (int row=0;row<w;row++) {
            for (int col =0;col<w;col++) {
                grids[row][col] = new cells(row,col,Cell_Size);
            }
        }
        current = grids[new Random().nextInt(w)][new Random().nextInt(w)];
        current.Visited = true;
        Visited_Stack = new Stack<cells>();
        running = true;

    }
    public void resetMaze(){
        for (int row=0;row<w;row++) {
            for (int col =0;col<w;col++) {
                grids[row][col].Reset_Cell();
            }
        }
        finish = false;
    }

    public void drawMaze(Graphics g) {
        for(int i = 0;i<w;i++) {
            for(int j = 0;j<w;j++) {
                grids[i][j].Draw_Box(g, new Color(100,50,200,190));
                grids[i][j].Draw_Cell(g);
            }
        }
    }
    public void mazeAlgorithm(Graphics g) {
        if(running) {
            if(Count_Visited<=w*w)
                current.Draw_Box(g,new Color(0,255,0,100));
            update();
            System.out.println(Count_Visited);
        }
    }
    public void drawMazeInstantly() {
        mazeAlgorithm();
        running = false;
    }

    public void drawPathFinder(Graphics g,int mode) {
        if(mode == 0)
            mazeFinderBFS(g);
        else if(mode == 1)
            mazeFinderDFS(g);

        for(cells x: Paths_From_A_to_B) {
            x.drawPath(g, Color.RED);
        }

        Start.Draw_Box(g,new Color(0, 0, 250));
        End.Draw_Box(g,new Color(0, 250, 0));


        for(int i = 0;i<w;i++) {
            for(int j = 0;j<w;j++) {
                grids[i][j].Draw_Cell(g);
            }
        }

    }
    public boolean checkFinished(){
        if (running)
            return false;
        return true;
    }

    public void mazeAlgorithm() {
        Stack<cells> VisitedList = new Stack<>();
        current.Visited = true;
        VisitedList.push(current);
        while(!VisitedList.isEmpty()) {
            current = VisitedList.pop();
            if (hasNeighbor(current)) {
                VisitedList.push(current);
                Next = getOneRandomNeighbor(current);
                wallBreaker(current, Next);
                Next.Visited = true;
                VisitedList.push(Next);
            }
        }
    }



    public void update() {
        if (running == false)
            return;

        Next = getOneRandomNeighbor(current);
        if(Next != null) {
            Visited_Stack.push(Next);
            Count_Visited++;
            Next.Visited = true;
            wallBreaker(current, Next);
            current = Next;
        }
        else {
            while(!hasNeighbor(current)) {
                if(!Visited_Stack.empty())
                    current = Visited_Stack.pop();
                else {
                    running = false;
                    Count_Visited++;
                    return;
                }
            }
            update();
        }
    }

    public void mazeFinderBFS(Graphics g) {

        Next = getOneNeighbor(current);
        AtoB(current,Next);
        if(Next == End) {
            System.out.println("Finished");
            finish = true;
            Path_Start_End();
            return;
        }

        if(Next != End && Next != null) {
            Visited_Queue.offer(Next);
            Next.Visited_Path = true;
            Next.Visited = true;
            Next.drawPath(g, Color.ORANGE);
        }else {
            if(!Visited_Queue.isEmpty())
                current = Visited_Queue.poll();
            else {
                return;
            }
        }
    }


    public void mazeFinderDFS(Graphics g) {

        Next = getOneNeighbor(current);
        AtoB(current,Next);
        if(Next == End) {
            System.out.println("Finished");
            finish = true;
            Path_Start_End();
            return;
        }

        if(Next != End && Next != null) {
            VisitedS.push(Next);
            Next.Visited_Path = true;
            Next.Visited = true;
            Next.drawPath(g, Color.ORANGE);
        }else {
            if(!VisitedS.isEmpty()) {
                current = VisitedS.pop();
            }
            else {
                return;
            }
        }
    }


    private void Path_Start_End() {
        Paths_From_A_to_B.add(End);
        cells tempParent = End.Parent;
        while(tempParent != Start) {
            Paths_From_A_to_B.add(tempParent);
            tempParent = tempParent.Parent;
        }
    }


    private void AtoB(cells A,cells B) {
        if(B!=null) {
            B.Parent = A;
        }
        if(A!=null)
            A.Next.add(B);
    }


    public void initStartAndEnd() {


        Start = grids[new Random().nextInt(w)][new Random().nextInt(w)];
        End = grids[new Random().nextInt(w)][new Random().nextInt(w)];

        if (Start == End){
            End = grids[new Random().nextInt(w)][new Random().nextInt(w)];
        }

        for(int i = 0;i<w;i++) {
            for(int j = 0;j<w;j++) {
                grids[i][j].Visited = false;
            }
        }


        Start.Visited = true;
        End.Visited = true;
        Visited_Queue = new LinkedList<cells>();
        VisitedS = new Stack<cells>();
        Paths_From_A_to_B = new ArrayList<cells>();
        current = Start;
        Next = current;

    }



    public cells getOneNeighbor(cells currentCell){
        ArrayList<cells> neighbors = new ArrayList<>();

        if (currentCell.row-1 >= 0 && grids[currentCell.row-1][currentCell.col].Visited_Path == false && currentCell.Walls[0] == false) {
            neighbors.add(grids[currentCell.row-1][currentCell.col]);
        }

        if (currentCell.col+1 < w && grids[currentCell.row][currentCell.col+1].Visited_Path == false && currentCell.Walls[1] == false) {
            neighbors.add(grids[currentCell.row][currentCell.col+1]);
        }

        if (currentCell.row + 1 < w && grids[currentCell.row+1][currentCell.col].Visited_Path == false && currentCell.Walls[2] == false) {
            neighbors.add(grids[currentCell.row+1][currentCell.col]);
        }

        if (currentCell.col-1 >= 0 && grids[currentCell.row][currentCell.col-1].Visited_Path == false && currentCell.Walls[3] == false) {
            neighbors.add(grids[currentCell.row][currentCell.col-1]);
        }


        if (neighbors.isEmpty())
            return null;

        return  neighbors.get(0);
    }

    public cells getOneRandomNeighbor(cells currentCell) {
        ArrayList<cells> neighbors = new ArrayList<>();

        if (currentCell.row-1 >= 0 && grids[currentCell.row-1][currentCell.col].Visited == false) {
            neighbors.add(grids[currentCell.row-1][currentCell.col]);
        }

        if (currentCell.col+1 < w && grids[currentCell.row][currentCell.col+1].Visited == false) {
            neighbors.add(grids[currentCell.row][currentCell.col+1]);
        }

        if (currentCell.row + 1 < w && grids[currentCell.row+1][currentCell.col].Visited == false) {
            neighbors.add(grids[currentCell.row+1][currentCell.col]);
        }

        if (currentCell.col-1 >= 0 && grids[currentCell.row][currentCell.col-1].Visited == false) {
            neighbors.add(grids[currentCell.row][currentCell.col-1]);
        }


        if (neighbors.isEmpty())
            return null;

        return  neighbors.get(new Random().nextInt(neighbors.size()));

    }
    public boolean hasNeighbor(cells currentCell) {
        if (getOneRandomNeighbor(currentCell)!=null)
            return true;
        return false;
    }
    public void wallBreaker(cells cellA,cells cellB) {
        if (cellA.row == cellB.row+1) {
            cellA.Walls[0] = false;
            cellB.Walls[2] = false;
            return;
        }

        if (cellA.row == cellB.row-1) {
            cellA.Walls[2] = false;
            cellB.Walls[0] = false;
            return;
        }

        if (cellA.col == cellB.col+1) {
            cellA.Walls[3] = false;
            cellB.Walls[1] = false;
            return;
        }

        if (cellA.col == cellB.col-1) {
            cellA.Walls[1] = false;
            cellB.Walls[3] = false;
            return;
        }



    }

    public int getCellSize() {
        return Cell_Size;
    }

    public void setCellSize(int cellSize) {
        Cell_Size = cellSize;
    }


}