package com.mycompany.mazewelcome;

//importing needed modules
import java.awt.*;
import java.util.*;

//creating myMaze class
public class myMaze
{

    //initializing the variabels
    public boolean finish = false;
    private final int Maze_Size;
    private int Cell_Size;
    private final int number_of_cells ;
    private cells[][] grids;
    private cells current;
    private cells Next;
    private Stack<cells> Visited_Stack;
    private boolean running = false;
    private int Count_Visited=0;
    private ArrayList<cells> Paths_From_A_to_B;
    private cells Start,End;
    private Queue<cells> Visited_Queue;
    private Stack<cells> VisitedS;
    private int myKeyCode;

    //the class constructor
    //gets maze size and cell size and finds number of cells
    myMaze(int mazeSize,int cellSize)
    {
        Maze_Size = mazeSize;
        Cell_Size = cellSize;
        number_of_cells = (int)(Maze_Size/Cell_Size);
        init();
    }

    //initializing the maze
    private void init() 
    {
        grids = new cells[number_of_cells][number_of_cells];

        //add cell object on entire grid
        for (int row=0;row<number_of_cells;row++)
         {
            for (int col =0;col<number_of_cells;col++) 
            {
                grids[row][col] = new cells(row,col,Cell_Size);
            }
        }

        //randomly chooseng one cell as start point
        current = grids[new Random().nextInt(number_of_cells)][new Random().nextInt(number_of_cells)];
        current.Visited = true;

        //initilizing the visited stack
        Visited_Stack = new Stack<cells>();

        running = true;

    }

    //resets the maze by resetting the all cells
    //using Reset_cell method , implemented in cells.java
    public void resetMaze()
    {
        for (int row = 0; row < number_of_cells;row++) 
        {
            for (int col =0;col<number_of_cells;col++)
            {
                grids[row][col].Reset_Cell();
            }
        }
        finish = false;
    }

    //this methode draws entire maze
    // using Draw_box method implemented in cells.java
    public void drawMaze(Graphics g) 
    {
        for(int i = 0;i<number_of_cells;i++) 
        {
            for(int j = 0;j<number_of_cells;j++) 
            {
                grids[i][j].Draw_Box(g, new Color(110,206,200,250));
                grids[i][j].Draw_Cell(g);
            }
        }
    }

    //this method checks the runnig mode is on
    //and then paints the current cell and updates
    public void mazeAlgorithm(Graphics g) 
    {
        if(running) 
        {
            if(Count_Visited <= number_of_cells*number_of_cells)
                current.Draw_Box(g,new Color(0,255,0,100));
            update();

        }
    }

    //this methods by setting the running = false
    //draws maze without showing the process
    public void drawMazeInstantly() 
    {
        mazeAlgorithm();
        running = false;
    }

    //this methode checks game mode and then
    //passes graphic object g to neede method
    public void drawPathFinder(Graphics g,int mode)
    {
        if(mode == 0)
            play(g);
        else if(mode == 1)
            mazeFinderBFS(g);
        else if(mode==2){
            mazeFinderDFS(g);
        }

        //draws path with white line
        for(cells c: Paths_From_A_to_B) 
        {
            c.drawPath(g, Color.WHITE);
        }

        //setting color for start ans end cell
        //green for the start cell and red for the end cell
        Start.Draw_Box(g,new Color(0, 250, 0));
        End.Draw_Box(g,new Color(250, 0, 0));


        //draws entire grid for starting the solve
        for(int i = 0 ; i < number_of_cells;i++) 
        {
            for(int j = 0 ; j < number_of_cells ; j++) 
            {
                grids[i][j].Draw_Cell(g);
            }
        }

    }

    //checks creating the maze finished or not
    public boolean checkFinished()
    {
        if (running)
            return false;
        return true;
    }


    //algorithm for creating the maze
    /*
    this algorithm uses a stack for saving visited cells
    and while our stack is not empty randomly chooses
    one neighbor cell and breaks the wall between them 
    using wallbreaker method implemented in cells.java
    */

    public void mazeAlgorithm() 
    {
        Stack <cells> VisitedList = new Stack<>();
        current.Visited = true;
        VisitedList.push(current);

        while(!VisitedList.isEmpty()) 
        {
            current = VisitedList.pop();

            if (hasNeighbor(current)) 
            {
                VisitedList.push(current);
                Next = getOneRandomNeighbor(current);
                wallBreaker(current, Next);
                Next.Visited = true;
                VisitedList.push(Next);
            }
        }
    }


    /*
    a recursion method that picks up a random neghbor
    and breaks wall between them
    till there is new neighbor this will repeat
    when there is not new neighbor it pop a cell from stack
    and checks possible neighbors to back to the first if block
    if there is not any negobor at all running becomes false
    and recursion flow will stop
    */
    public void update() 
    {
        if (running == false)
            return;

        Next = getOneRandomNeighbor(current);

        if(Next != null) 
        {
            Visited_Stack.push(Next);
            Count_Visited++;
            Next.Visited = true;
            wallBreaker(current, Next);
            current = Next;
        }
        else 
        {
            while(!hasNeighbor(current)) 
            {
                if(!Visited_Stack.empty())
                    current = Visited_Stack.pop();

                else 
                {
                    running = false;
                    Count_Visited++;
                    return;
                }
            }

            update();
        }
    }

    
    //the famous BFS algorithm for finding the path start to end

    public void mazeFinderBFS(Graphics g) 
    {
        Next = getOneNeighbor(current);
        AtoB_parent_child_setter(current,Next);
        if(Next == End) 
        {
            finish = true;
            Path_Start_End();
            return;
        }

        if(Next != End && Next != null) 
        {
            Visited_Queue.offer(Next);
            Next.Visited_Path = true;
            Next.Visited = true;
            Next.drawPath(g, Color.ORANGE);
        }

        else 
        {
            if(!Visited_Queue.isEmpty())
                current = Visited_Queue.poll();

            else 
            {
                return;
            }
        }
    }



    /*
     play mode method
     this method respnsible for playing mode and 
     gets Ascii code of pressed key
     and passes it to move method to move the current cell
     and updates some variables

     more datails in method comments
    */
    public void play(Graphics g) 
    {
        //gets the Ascii code and move cell
        myKeyCode=myFrame.getKeyCode();
        Next = move(myKeyCode,current);

        //if next cell is not null setts the parents and child to the cells
        if(Next!=null)
        {
            if(!Next.Visited_Path)
            {
                AtoB_parent_child_setter(current, Next);
            }
        }

        //if next cell is end cell finishes the play and sets the final path
        if(Next == End) 
        {
            finish = true;
            Path_Start_End();
            return;
        }

        //adding next cell to our explored path with these specified conditions
        if(Next != End && Next != null) 
        {
            if(!Paths_From_A_to_B.contains(Next) && Next!=Start && current!=Start)
            {
				Paths_From_A_to_B.add(current);
				Paths_From_A_to_B.add(Next);
			}

            else
            {
                Paths_From_A_to_B.remove(current);
                Paths_From_A_to_B.remove(Next);
            }

            Next.drawPath(g, Color.ORANGE);
            Next.Visited_Path = true;

            current = Next;
            current.Draw_Box(g,new Color(120,180,180));

        }

    //the famous DFS algorithm for finding the path start to end
    }
    public void mazeFinderDFS(Graphics g) 
    {
        Next = getOneNeighbor(current);
        AtoB_parent_child_setter(current,Next);

        if(Next == End) 
        {
            finish = true;
            Path_Start_End();
            return;
        }

        if(Next != End && Next != null) 
        {
            VisitedS.push(Next);
            Next.Visited_Path = true;
            Next.Visited = true;
            Next.drawPath(g, Color.ORANGE);
        }

        else 
        {
            if(!VisitedS.isEmpty()) 
            {
                current = VisitedS.pop();
            }

            else 
            {
                return;
            }
        }
    }



    /*
    adds end cell to the final explored path
    and then we use a while loop to set the all cell parents
    till we reach the start cell to specifying answer path
    */

    private void Path_Start_End() 
    {
        Paths_From_A_to_B.add(End);
        cells tempParent = End.Parent;
        while(tempParent != Start) 
        {
            Paths_From_A_to_B.add(tempParent);
            tempParent = tempParent.Parent;
        }
    }


    //specifies parent of B and the next cell (child) of A
    private void AtoB_parent_child_setter(cells A,cells B) 
    {
        if(B!=null) 
        {
            B.Parent = A;
        }
        
        if(A!=null)
            A.Next.add(B);
    }



    /*
    this method randomly chooses start and end cells 
    checks and prevent the wrong sitiuations
    */

    public void initStartAndEnd() 
    {

        Start = grids[new Random().nextInt(number_of_cells)][new Random().nextInt(number_of_cells)];
        End = grids[new Random().nextInt(number_of_cells)][new Random().nextInt(number_of_cells)];

        if (Start == End)
        {
            End = grids[new Random().nextInt(number_of_cells)][new Random().nextInt(number_of_cells)];
        }

        for(int i = 0 ; i < number_of_cells ; i++) 
        {
            for(int j = 0 ; j < number_of_cells ; j++) 
            {
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

    /*
    this method gets an Ascii code as keyCode and the current cell
    moves the current cell by changing the cell with given keyCode
    and returns new current cell
    */
    public cells move(int keyCode,cells currentCell)
    {
        cells myCell = null;

        if(keyCode==87)
        {
            if (currentCell.row - 1 >= 0 &&  currentCell.Walls[0] == false) 
            {
                myCell = grids[currentCell.row-1][currentCell.col];
            }
        }

        else if(keyCode==68)
        {
            if (currentCell.col + 1 < number_of_cells && currentCell.Walls[1] == false) 
            {
                myCell = grids[currentCell.row][currentCell.col+1];
            }
        }

        else if(keyCode==83)
        {
            if (currentCell.row + 1 < number_of_cells && currentCell.Walls[2] == false) 
            {
                myCell = grids[currentCell.row+1][currentCell.col];
            }
        }

        else if(keyCode==65)
        {
            if (currentCell.col - 1 >= 0 && currentCell.Walls[3] == false) 
            {
                myCell = grids[currentCell.row][currentCell.col-1];
            }
        }

        return  myCell;

    }


    /*
    this method gets the current cell 
    and checks all possible neighbors and adds
    valid neighbors to the neighbors array
    if neighbors array is not empty 
    returns the first naighbor in the array
    */
    public cells getOneNeighbor(cells currentCell)
    {
        ArrayList<cells> neighbors = new ArrayList<>();

        if (currentCell.row - 1 >= 0 && grids[currentCell.row-1][currentCell.col].Visited_Path == false && currentCell.Walls[0] == false)
        {
            neighbors.add(grids[currentCell.row-1][currentCell.col]);
        }

        if (currentCell.col+1 < number_of_cells && grids[currentCell.row][currentCell.col+1].Visited_Path == false && currentCell.Walls[1] == false) 
        {
            neighbors.add(grids[currentCell.row][currentCell.col+1]);
        }

        if (currentCell.row + 1 < number_of_cells && grids[currentCell.row+1][currentCell.col].Visited_Path == false && currentCell.Walls[2] == false) 
        {
            neighbors.add(grids[currentCell.row+1][currentCell.col]);
        }

        if (currentCell.col-1 >= 0 && grids[currentCell.row][currentCell.col-1].Visited_Path == false && currentCell.Walls[3] == false) 
        {
            neighbors.add(grids[currentCell.row][currentCell.col-1]);
        }

        if (neighbors.isEmpty())
            return null;

        return  neighbors.get(0);
    }


    /*
    Almost the  same with getOneNeighbor method
    the difference is that it returns a random valid neighbor
    */
    public cells getOneRandomNeighbor(cells currentCell) 
    {
        ArrayList<cells> neighbors = new ArrayList<>();
        if (currentCell.row-1 >= 0 && grids[currentCell.row-1][currentCell.col].Visited == false) 
        {
            neighbors.add(grids[currentCell.row-1][currentCell.col]);
        }

        if (currentCell.col+1 < number_of_cells && grids[currentCell.row][currentCell.col+1].Visited == false) 
        {
            neighbors.add(grids[currentCell.row][currentCell.col+1]);
        }

        if (currentCell.row + 1 < number_of_cells && grids[currentCell.row+1][currentCell.col].Visited == false) 
        {
            neighbors.add(grids[currentCell.row+1][currentCell.col]);
        }

        if (currentCell.col-1 >= 0 && grids[currentCell.row][currentCell.col-1].Visited == false) 
        {
            neighbors.add(grids[currentCell.row][currentCell.col-1]);
        }

        if (neighbors.isEmpty())
            return null;

        return  neighbors.get(new Random().nextInt(neighbors.size()));
    }

    /*
    this method gets the current cell 
    and checks is there any neighbors or not
    if there is , returns true
    else returns false
    */
    public boolean hasNeighbor(cells currentCell) 
    {
        if (getOneRandomNeighbor(currentCell)!=null)
            return true;
        return false;
    }

    /*
    this method gets two cells 
    and removes the wall between those two cell
    */
    public void wallBreaker(cells cellA,cells cellB) 
    {
        if (cellA.row == cellB.row+1) 
        {
            cellA.Walls[0] = false;
            cellB.Walls[2] = false;
            return;
        }

        if (cellA.row == cellB.row-1) 
        {
            cellA.Walls[2] = false;
            cellB.Walls[0] = false;
            return;
        }

        if (cellA.col == cellB.col+1) 
        {
            cellA.Walls[3] = false;
            cellB.Walls[1] = false;
            return;
        }

        if (cellA.col == cellB.col-1) 
        {
            cellA.Walls[1] = false;
            cellB.Walls[3] = false;
            return;
        }



    }

    //a getter method that returnss the size of cell :-|
    public int getCellSize() 
    {
        return Cell_Size;
    }

    //a setter that gets a cell size and assign it to cell_size variable
    public void setCellSize(int cellSize) 
    {
        Cell_Size = cellSize + 400;
    }


}