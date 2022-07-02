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
	private Stack<cells> visitedS;
	
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
		current = grids[new Random().NextInt(w)][new Random().NextInt(w)];
		current.visited = true;
		Visited_Stack = new Stack<cells>();
		running = true;
		
	}
	public void resetMaze(){
		for (int row=0;row<w;row++) {
			for (int col =0;col<w;col++) {
				grids[row][col].resetCell();
			}
		}
		finish = false;
	}
	
	public void drawMaze(Graphics g) {
		for(int i = 0;i<w;i++) {
			for(int j = 0;j<w;j++) {
				grids[i][j].drawBox(g, new Color(100,50,200,190));
				grids[i][j].drawCell(g);
			}
		}
	}
	public void mazeAlgorithm(Graphics g) {
		if(running) {
			if(Count_Visited<=w*w)
				current.drawBox(g,new Color(0,255,0,100));
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
		
		Start.drawBox(g,new Color(0, 0, 250));
		End.drawBox(g,new Color(0, 250, 0));
	
		
		for(int i = 0;i<w;i++) {
			for(int j = 0;j<w;j++) {
				grids[i][j].drawCell(g);
			}
		}	

	}
	public boolean checkFinished(){
		if (running)
			return false;
		return true;
	}
	
	public void mazeAlgorithm() {
		Stack<cells> visitedList = new Stack<>();
		current.visited = true;
		visitedList.push(current);
		while(!visitedList.isEmpty()) {
			current = visitedList.pop();
			if (hasNeighbor(current)) {
				visitedList.push(current);
				Next = getOneRandomNeighbor(current);
				wallBreaker(current, Next);
				Next.visited = true;
				visitedList.push(Next);	
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
			Next.visited = true;
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
			Next.visitedPath = true;
			Next.visited = true;
			Next.drawPath(g, Color.ORANGE);
		}else {
			if(!Visited_Queue.isEmpty())
				current = Visited_Queue.poll();
			else {
				return;
			}
		}
	}

}